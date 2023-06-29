package Distributed.rmi;

import CONTROLLER.GameController;
import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Distributed.crashPreGame;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import Listeners.GameEventListener;
import MODEL.Game;
import MODEL.GameState;
import MODEL.GameView;
import VIEW.OrderChoice;
import VIEW.SlotChoice;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class that represents a server that interacts with a remote client using RMI (Remote Method Invocation).
 */
public class ServerImpl extends UnicastRemoteObject implements ServerRMIInterface, GameEventListener {

    /**
     * The controller of the game associated to the server.
     */
    private GameController controller;

    /**
     * The model of the game associated to the server.
     */
    private Game model;

    /**
     * The clients connected to the server from the beginning of the game.
     */
    private final ArrayList<ClientRMIInterface> logged = new ArrayList<>();

    /**
     * The clients actually connected to the server.
     */
    private ClientRMIInterface[] effectiveLogged;

    /**
     * The nicknames of the players not currently in game because they crashed.
     */
    private String[] dudesCrashed ;

    /**
     * The nicknames of the players currently in game.
     */
    private String[] dudesInGame ;

    /**
     * The timer that manages that tries to find if a player crashed.
     */
    private Timer timerCrash = new Timer();

    /**
     * The timer that defines the end of the game in case of only one player left.
     */
    private final Timer timerTurn = new Timer();

    /**
     * Constructor inherited from the super class UnicastRemoteObject.
     *
     * @throws RemoteException if there are problems with the RMI connection.
     */
    public ServerImpl() throws RemoteException {
        super();
    }

   /**
    * Constructor for the ServerImpl class with a specified port.
    *
    * @param port The port for the RMI connection.
    * @throws RemoteException If there are problems with the RMI connection.
    */
    public ServerImpl(int port) throws RemoteException {
        super(port);
    }

   /**
    * Constructor for the ServerImpl class with a specified port and connection factories.
    *
    * @param port The port for the RMI connection.
    * @param csf The RMIClientSocketFactory object for the RMI connection.
    * @param ssf The RMIServerSocketFactory object for the RMI connection.
    * @throws RemoteException If there are problems with the RMI connection.
    */
    public ServerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    /**
     * Remote method used by the client in order to registry itself
     * to the server and, in this way, to the model.
     *
     * @param client The client that wants to registry itself.
     *
     * @throws IOException Exception thrown if an input or an output failed.
     * @throws SameNicknameException Exception thrown if the client tries to registry with an
     *                               already used nickname
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the
     *                                       selected tiles.
     * @throws ParseException Exception thrown if there are parsing problems during the reading of the
     *                        personal goals from their json file.
     */
    @Override
    public void register(ClientRMIInterface client) throws IOException, SameNicknameException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException, ParseException {

        System.out.println("Ricevuto un tentativo di connessione");
        if (logged.size()==0) {
            model = new Game(client.startGame());
            controller = new GameController(model);
            this.model.addGameEventListener(this);
            model.signPlayer(client.getNickname());
            this.logged.add(client);
            System.out.println("Primo client inserito correttamente ");
        } else {
            if(!model.isGameOn() && model.getCurrentState().equals(GameState.LOGIN)){
                try {
                    this.pingInPreGame();
                } catch (RemoteException e) {
                    notifyCrashPregame();
                    client.subscriptionCancelled();
                }
                if (controller.checkNick(client.getNickname())) {
                    model.signPlayer(client.getNickname());
                    this.logged.add(client);
                    System.out.println("Il giocatore " + client.getNickname() + " è stato correttamente iscritto ");
                    subscription();
                    if (model.isGameOn()) {
                        startGame();
                    }
                } else {
                    throw new SameNicknameException("Il nickname è già preso!! \n");
                }
            }else if (checkReEntering(client.getNickname())) {
                backInGame(client);
                if (realLength(effectiveLogged)==2) {
                    restartGameAfterCrash();
                    resumeGame();
                }
                System.out.println("Un client è rientrato in partita, buona fortuna! ");

            }else {
                client.notifyGameStarted();
            }
        }
    }


    /**
     * First client's control method: it controls if it is one client crashed before then it puts the client
     * back into the game.
     *
     * @param client The client that want to return in game.
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    private void backInGame(ClientRMIInterface client) throws RemoteException {

        int reenteringPosition = model.positionInGame(client.getNickname());
        dudesInGame[reenteringPosition] = client.getNickname();
        effectiveLogged[reenteringPosition]=client;
    }

    /**
     * Remote method used by the client when the user choose some coordinates.
     *
     * @param client The client that choose the tiles.
     * @param SC Array that contains the coordinates of the tiles selected by the client.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     */
    @Override
    public void updateServerSelection(ClientRMIInterface client, SlotChoice[] SC) throws RemoteException, NotAdjacentSlotsException, NotCatchableException {
        try {
            this.controller.checkSelect(SC);
            System.out.println("La selezione è andata a buon fine ");
        } catch (NotCatchableException e) {
            throw new NotCatchableException(e.getMessage());
        } catch (NotAdjacentSlotsException e) {
            throw new NotAdjacentSlotsException(e.getMessage());
        }

    }

    /**
     * Remote method used by the client when a user has chosen if order the tiles.
     *
     * @param client Client that has chosen if reorder the tiles.
     * @param C Array that contains the order chosen by the user for the tiles.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    @Override
    public void updateServerReorder(ClientRMIInterface client, OrderChoice C) throws RemoteException {
        this.controller.checkOrder(C);
    }

    /**
     * Remote method used by the client when a user has chosen the column where he decided to insert the tiles.
     *
     * @param client Client that has chosen the column.
     * @param column Column where the client wants to insert the tiles.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the
     *                                       selected tiles.
     */
    @Override
    public void updateServerInsert(ClientRMIInterface client, int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        try {
            this.controller.checkInsert(column);
            System.out.println("Inserimento corretto, Passo al prossimo giocatore \n");
            this.turnUpdate();
        } catch (NotEnoughSpaceChoiceException e) {
            throw new NotEnoughSpaceChoiceException(e.getMessage());
        }
    }

    /**
     * Remote method used by the client to update the server about the number of choices that the user wants
     * to do in the turn.
     *
     * @param client Client that wants to update.
     * @param number The number of choices that the user wants to do.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the
     *                                       selected tiles.
     */
    @Override
    public void updateServerChoices(ClientRMIInterface client, int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        try {
            this.controller.checkSpaceChoices(number);
        } catch (NotEnoughSpaceChoiceException e) {
            for (ClientRMIInterface clients : logged) {
                if (controller.getOnStage().equals(clients.getNickname())) {
                    throw new RuntimeException();
                }
            }
        }
    }

    /**
     * Method that adds listener to the game.
     *
     * @param listener Listener that will be added.
     */
    @Override
    public void addGameEventListener(GameEventListener listener) {
        System.out.println("Client registrato con successo! ");
    }

    /**
     * Method that notifies to the clients the creation and the start of the game, sending the first gameview.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    //Notifica al client l'avvenuta creazione (quindi inizio) del gioco, mandando la situazione della board
    @Override
    public void gameStateChanged() throws RemoteException {
        int i = 0;
        for (ClientRMIInterface client : effectiveLogged) {
            if(client!=null) {
                client.updateClientFirst(new GameView(i, model));
                i++;
            }
        }
    }

    /**
     * Method that notifies to the clients the new view after that a client ended its turn, sending also the
     * personal shelf.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the
     *                                       selected tiles.
     */
    @Override
    public void turnIsOver() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        pingGameOn();
        for (ClientRMIInterface client : effectiveLogged) {
            if (client != null) {
                if (controller.getOnStage().equals(client.getNickname())) {
                    client.updateClientPlaying(new GameView(model));
                    //    client.endTurn();
                } else {
                    client.updateClientRound(new GameView(model));
                }
            }else{
                System.err.println("Discarding event");
            }
        }
        this.newTurn();
    }

    /**
     * Method that notifies the clients about the start of the game.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the
     *                                       selected tiles.
     */
    @Override
    public void readyToStart() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        this.newTurn();
    }

    /**
     * Method that adds the point to the first to complete the shelf and
     * notifies the clients about the fact that the next turn will be the last.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    @Override
    public void notifyEndGame() throws RemoteException {
        controller.completeShelf();
        notifyCompleted();
    }

    /**
     * Method that notifies the clients about the end of the game.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */

    @Override
    public void notifyGameFinished() throws RemoteException {
        winnerInterface(controller.endGame());
    }

    /**
     * Method that notifies the skip turn of a crashed player.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the
     *                                       selected tiles.
     */
    @Override
    public void notifySkipTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        if(playingCrashedPlayer(controller.getOnStage())){
            System.err.println("Il giocatore selezionato non è valido, passo al prossimo");
            controller.skipTurn();
        }else{
            this.newTurn();
        }
    }

    /**
     * Method that if the model has notified an error, notify it to the client who is playing.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    @Override
    public void notifyLastError() throws RemoteException {
        for (ClientRMIInterface client : effectiveLogged) {
            if(client!=null) {
                if (controller.getOnStage().equals(client.getNickname())) {
                    client.updateClientError(new GameView(model, -1));
                }
            }
        }
    }

    /**
     * Method that force the end of the turn if there is a crash during it.
     *
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    @Override
    public void notifyForcedTurnEnding() throws NotEnoughSpaceChoiceException, RemoteException {
        controller.checkInsert(-1);
    }


    /**
     * Method that notifies the clients in game the new turn and permits to play to the user that is on turn.
     * When there is a new turn, it verifies if all the clients enrolled to the game are not crashed.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    public void newTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        System.out.println("Chiamata a nuovo turno, puntando al giocatore: "+controller.getOnStage());
        if(model.isGameOn()) {
            if (!playingCrashedPlayer(controller.getOnStage())) {
                for (ClientRMIInterface client : effectiveLogged) {
                    if (client != null) {
                        if (controller.getOnStage().equals(client.getNickname())) {
                            client.startTurn();
                            startTurnTimer();
                        } else {
                            client.onWait();
                        }
                    }
                }
            } else {
                controller.skipTurn();
            }
        }
    }

    /**
     *  Method that resume the game flow if, after that was remained only one player, someone of the one crashed before return in game.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    public void resumeGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        System.out.println("Riprendo il gioco puntando al giocatore: "+controller.getOnStage());
        gameStateChanged();
        for (ClientRMIInterface client : effectiveLogged) {
            if (client != null) {
                if (controller.getOnStage().equals(client.getNickname())) {
                    client.startTurn();
                    startTurnTimer();
                } else {
                    client.onWait();
                }
            }
        }
    }


    /**
     * Method used if a user tries to return in game. It controls if the nick is equals to one of the crashed player's nick.
     *
     * @param onStage Nick to control.
     * @return Boolean that says if the user can return in game.
     */
    private boolean playingCrashedPlayer(String onStage) {
        for (int i = 0; i < dudesCrashed.length; i++) {
            if (onStage.equals(dudesCrashed[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that notifies that a player joined the game.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    public void subscription() throws RemoteException {
        for (ClientRMIInterface client : logged) {
            client.newPlayerAdded();
        }
    }

    /**
     * Method that notifies the clients about the fact that the next turn will be the last.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    public void notifyCompleted() throws RemoteException {
        for (ClientRMIInterface client : effectiveLogged) {
            if(client!=null) {
                client.notifyCompleted();
            }
        }
    }

    /**
     * Method that notifies to the clients a string that contains info about the winner player.
     *
     * @param s String that contains info about the winner player.
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    public void winnerInterface(String s) throws RemoteException {
        for (ClientRMIInterface client : effectiveLogged) {
            if(client!=null) {
                client.winnerInterface(s);
            }
        }
    }


    /**
     * Method that start the game if all the players joined, saving their nickname and the max number of disconnection.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    private void startGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        dudesInGame = new String[model.getNplayers()];
        dudesCrashed = new String[model.getNplayers()];
        effectiveLogged = logged.toArray(new ClientRMIInterface[model.getNplayers()]);
        playingClients(dudesInGame, logged);
        controller.startGame();
    }

    /**
     * Method that controls that nobody has crashed every turn, and that if someone crashed, that one
     * was not the playing player.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    private void turnUpdate() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        controller.turnUpdate();
    }

    /**
     * Method that pass to a dynamic structure from a static one.
     *
     * @param nicknames Nicknames of the players.
     * @param subscribed List of the enrolled players.
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    private void playingClients(String[] nicknames, ArrayList<ClientRMIInterface> subscribed) throws RemoteException {
        for (int i = 0; i < model.getNplayers(); i++) {
            nicknames[i]  = subscribed.get(i).getNickname();
        }
    }

    /**
     * Method for the return in game of a player who has crashed.
     *
     * @param nick Nickname of the player that wants to return in game.
     * @return Boolean that says if the player can return or not.
     */
    private boolean checkReEntering(String nick) {
        for (int i = 0; i < dudesCrashed.length; i++) {
            if (nick.equals(dudesCrashed[i])) {
                dudesCrashed[i]=null;
                return true;
            }
        }
        return false;
    }


    /**
     * Method that checks if all the client are reachable, using a check method of the client, during thepregame phase.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    private void pingInPreGame() throws RemoteException {
        for(int i = 0; i < logged.size(); i++){
            try {
                logged.get(i).ping();
            }catch (RemoteException e){
                System.err.println("Crash in preGame :( . Il server si chiuderà. ");
                System.exit(0);
            }
        }

    }

    /**
     * Method that checks if all the client are reachable, using a check method of the client, during the game phase.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    private void pingGameOn() throws NotEnoughSpaceChoiceException, RemoteException, NotAdjacentSlotsException, NotCatchableException {
        boolean crash = false;
        boolean inTurn = false;
        for(int i = 0; i < effectiveLogged.length; i++){
            try {if(effectiveLogged[i]!=null){
                effectiveLogged[i].ping();}
            }catch (RemoteException e){
                if(controller.getOnStage().equals(dudesInGame[i])){
                    inTurn = true;
                }
                System.out.println("Il giocatore " + dudesInGame[i] + " non risponde");
                saveCrash(i, dudesInGame[i]);
                crash=true;
            }
        }
        if(crash){
            crashRoutine();
            if(inTurn){
                controller.skipTurn();
            }
        }

    }

    /**
     * Method for the management of a player's crash, prints who's in game and who's crashed, and then works on the turn on the basis of how many client crashed.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    private void crashRoutine() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        System.out.println("Aggiornamento a seguito del crash.. ");
        swapCrash();
        System.out.println("I giocatori ancora attivi sono: ");
        whosHere(dudesInGame);
        System.out.println("I giocatori crashati sono: ");
        whosHere(dudesCrashed);
        notifyForcedCrash();
        checkTimeoutGame();
    }

    /**
     * Method that, using the fact that there are only static structure, updates in all
     * the structures the correct position of a players that crashed; that position is made equals to null
     * and a copy of it is put in a backup structure that ensures that the clients enrolled to the game
     * remain the same during all the game.
     */
    private void swapCrash(){
        for(int i = 0; i < dudesCrashed.length; i++){
            if(dudesCrashed[i]!=null){
                effectiveLogged[i]=null;
                dudesInGame[i]=null;
            }
        }
    }

    /**
     * Generic method that gives the effective length of an array.
     *
     * @param o Array to be analyzed to return its effective length.
     * @return The effective length of an array.
     */
    private int realLength(Object[] o){
        int realLength=0;
        for(int i = 0; i < o.length; i++){
            if(o[i]!=null){
                realLength++;
            }
        }
        return realLength;
    }

    /**
     * Method that when a player crashes save him in a structure that permits to keep track of his name.
     *
     * @param position Position of the crashed player in the array.
     * @param formerPlayer Name of the crashed player.
     */

    private void saveCrash(int position, String formerPlayer) {
        dudesCrashed[position]=formerPlayer;
    }

    /**
     * Method that prints a generic string in order to understand if a client is in game or not.
     *
     * @param toPrint Strings to be printed.
     */
    private void whosHere(String[] toPrint) {
        for (Object s : toPrint) {
            System.out.print(s + "\t");
        }
        System.out.println("-");
    }

    /**
     * Method that restart the game after that was remained only one player, someone of the one crashed before.
     * return in game.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    private void restartGameAfterCrash() throws RemoteException{
        System.err.println("Timer di fine partita annullato ");
        timerCrash.cancel();
        timerCrash = new Timer();
        controller.switchGameState();
    }

    /**
     * Method that in case that only one player remained in game after crashes start a timer to wait for reconnections.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    private void checkTimeoutGame() throws RemoteException {
        if (realLength(effectiveLogged) == 1) {
            startTimer();
            notifyWaitingForReconnection();
            controller.switchGameState();
        }
    }


    /**
     * Method that contains the timer of timeout for the game.
     */
    private void startTimer() {

        System.out.println("Ho avviato il timer per annullare la partita, tra 30s verrà annullato ");
        TimerTask waitPlayers = new TimerTask() {
            @Override
            public void run() {
                System.err.println("Il timer è Scaduto");
                try {
                    notifyNoMorePlayers();
                    System.out.print("Procedo con la chiusura del server. ");
                    System.exit(-1);
                } catch (RemoteException e) {
                    System.err.println("Qualcosa è successo anche all'unico connesso, chiusura del server");
                    System.exit(-1);
                }
            }
        };
        timerCrash.schedule(waitPlayers, 30000);
    }

    /**
     * Method that start a timer for the moves. If the timer ends, it controls if the playing player is reachable.
     */
    private void startTurnTimer() {
        System.out.println("Ho avviato il timer (30s) per controllare lo stato dei client ");
        TimerTask turnPlayer = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Il client sta ancora decidendo la sua mossa ");
                try {
                    pingGameOn();
                    startTurnTimer();
                } catch (RemoteException | NotEnoughSpaceChoiceException | NotAdjacentSlotsException |
                         NotCatchableException e) {
                    System.out.println("Il giocante è andato down ");
                }
            }
        };
        timerTurn.schedule(turnPlayer, 30000);

    }

    /**
     *Method that notify a crash during the game.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    public void notifyForcedCrash() throws RemoteException {
        for (ClientRMIInterface client : effectiveLogged) {
            if(client!=null) {
                client.errorCrash();
            }
        }
    }

    /**
     *Method that notify a crash during the pregame.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    public void notifyCrashPregame() throws RemoteException{
        for(ClientRMIInterface client : effectiveLogged){
            if(client!=null) {
                crashPreGame notifyUser = new crashPreGame(client);
                try {
                    notifyUser.start();
                } catch (RuntimeException e) {
                    System.err.println("-- Client ko --");
                }
            }
        }
    }

    /**
     * Method called if there are not enough player for the game.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    private void notifyNoMorePlayers() throws RemoteException {
        for(ClientRMIInterface client : effectiveLogged){
            if(client!=null) {
                client.errorEndGameNoMorePlayers();
            }
        }

    }

    /**
     * Method that notify to the last player that all the others are crashed.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     */
    private void notifyWaitingForReconnection() throws RemoteException {
        for(ClientRMIInterface client : effectiveLogged) {
            if (client != null) {
                client.errorMissingPlayers();
            } else {
                System.err.println("Discarding event ");
            }
        }
    }
}