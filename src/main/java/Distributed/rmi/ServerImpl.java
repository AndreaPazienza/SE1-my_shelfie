package Distributed.rmi;

import CONTROLLER.GameController;
import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Distributed.crashPreGame;
import Distributed.crashThread;
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

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class ServerImpl extends UnicastRemoteObject implements ServerRMIInterface, GameEventListener {

    private GameController controller;
    private Game model;
    private final ArrayList<ClientRMIInterface> logged = new ArrayList<>();
    private String[] dudesCrashed;
    private String[] dudesInGame;
    private final Timer timerCrash = new Timer();
    private final Timer timerTurn = new Timer();
    private boolean firstPlayerEnrolled = false;

    public ServerImpl() throws RemoteException {
        super();
    }

    public ServerImpl(int port) throws RemoteException {
        super(port);
    }

    public ServerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    //Metodo remoto usato dal client per registrarsi al model
    @Override
    public void register(ClientRMIInterface client) throws RemoteException, SameNicknameException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {

        System.out.println("Ricevuto un tentativo di connessione");
        if (!firstPlayerEnrolled) {
            model = new Game(client.startGame());
            controller = new GameController(model);
            this.model.addGameEventListener(this);
            model.signPlayer(client.getNickname());
            this.logged.add(client);
            firstPlayerEnrolled = true;
        } else {
            //Ramo try-catch per verificare un crash in fase di preparazione (forza la chiusura)
            try {
                this.ping();
            } catch (RemoteException e) {
                notifyCrashPregame();
                client.subscriptionCancelled();
            }

            if (model.getCurrentState().equals(GameState.LOGIN)) {
                //Ogni client che vuole accedere se la partita è iniziata riceverà questo errore
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
            } else if (checkReEntering(client.getNickname())) {
                //Questo else-if mette in condizione il server di accettare una riconessione di un client
                backInGame(client.getNickname(), dudesCrashed, dudesInGame);
                logged.add(client);
                checkTimeoutGame();
                System.out.println("Un client è rientrato in partita, buona fortuna! ");

            } else {

                client.notifyGameStarted();
            }
        }
    }

    //Primo metodo di controllo del client, controlla che sia uno di quelli crashati e lo rimette in partita
    private void backInGame(String name, String[] crash, String[] enrolled) {
        int i = 0;
        //Manca controllo della posizione corretta, si fa con array presente in model
        for (i = 0; i < enrolled.length; i++) {
            if (enrolled[i] == null) {
                //Potrebbe metterlo in un ordine diverso nel caso sia più di un client a crashare
                enrolled[i] = name;
            }
        }

        i = 0;
        while (!crash[i].equals(name)) {
            i++;
        }
        crash[i] = null;

    }

    //Il Ping() controlla che ci siano sempre tutti i client
    //metodo remoto: usato dal client quando un utente ha selezionato delle coordinate
    @Override
    public void updateServerSelection(ClientRMIInterface client, SlotChoice[] SC) throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException { //throws NotAdjacentSlotsException, NotCatchableException {
        //pingClient();
        try {
            this.controller.checkSelect(SC);
            System.out.println("La selezione è andata a buon fine ");
        } catch (NotCatchableException e) {
            throw new NotCatchableException(e.getMessage());
        } catch (NotAdjacentSlotsException e) {
            throw new NotAdjacentSlotsException(e.getMessage());
        }

    }

    //metodo remoto: usato dal client quando un utente ha scelto se riordinare le tessere
    @Override
    public void updateServerReorder(ClientRMIInterface client, OrderChoice C) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        this.controller.checkOrder(C);
    }

    //metodo remoto: usato dal client quando un utente ha la colonna dove inserire
    @Override
    public void updateServerInsert(ClientRMIInterface client, int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        try {
            this.controller.checkInsert(column);
            //Alzato
            System.out.println("Inserimento corretto \n Passo al prossimo giocatore \n");
            turnUpdate();
        } catch (NotEnoughSpaceChoiceException e) {
            throw new NotEnoughSpaceChoiceException(e.getMessage());
        }
        //Da qui
    }

    @Override
    public void updateServerChoices(ClientRMIInterface client, int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        try {
            this.controller.checkSpaceChoices(number);
        } catch (NotEnoughSpaceChoiceException e) {
            for (ClientRMIInterface clients : logged) {
                if (controller.getOnStage().equals(clients.getNickname())) {
                    clients.errorChoices(e.getMessage());
                    throw new RuntimeException();
                } else {
                    String message = model.playerOnStage().nickname + " ha fatto un errore nella selezione! Sta rifacendo la sua scelta!\n)";
                    clients.errorNotifyInsert(message);
                }
            }
        }
    }

    //Viene aggiunto il Listener al gioco
    @Override
    public void addGameEventListener(GameEventListener listener) {
        System.out.println("Client registrato con successo! ");
    }

    //Notifica al client l'avvenuta creazione (quindi inizio) del gioco, mandando la situazione della board
    @Override
    public void gameStateChanged() throws RemoteException {
        int i=0;
        for(ClientRMIInterface client : logged){
            client.updateClientFirst(new GameView(i, model));
            i++;
        }
    }

    //Notifica al client la nuova view dopo che un client ha finito il proprio turno, con la PersonalShelf
    @Override
    public void turnIsOver() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for (ClientRMIInterface client : logged) {
            if (controller.getOnStage().equals(client.getNickname())) {
                client.updateClientPlaying(new GameView(model));
                client.endTurn();
            }else{
                client.updateClientRound(new GameView(model));
            }
        }
        this.newTurn();
    }

    //Notifica al client che abbiamo iniziato la partita
    @Override
    public void readyToStart() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        pingClient();
        for(ClientRMIInterface client : logged){
            if(controller.getOnStage().equals(client.getNickname())) {
                client.startTurn();
            }else{
                client.onWait();
            }
        }
    }

    @Override
    public void notifyEndGame() throws RemoteException {
        controller.completeShelf();
        notifyCompleted();
    }

    @Override
    public void notifyGameFinished() throws RemoteException {
        winnerInterface(controller.endGame());
    }

    @Override
    public void notifySkipTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        if(playingCrashedPlayer(controller.getOnStage())){
            controller.skipTurn();
        }
    }

    @Override
    public void notifyLastError() throws RemoteException {
        System.err.println("Ho generato un errore ");
        for (ClientRMIInterface client : logged) {
            if (controller.getOnStage().equals(client.getNickname())) {
                client.updateClientError(new GameView(model, -1));
            }
        }
    }


    //Rispetto a tutti i client iscritti manda la notifica di "via libera" al client di turno
    public void newTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        pingClient();
     if(!playingCrashedPlayer(controller.getOnStage())) {
            for (ClientRMIInterface client : logged) {
                if (controller.getOnStage().equals(client.getNickname())) {
                    client.startTurn();
                    startTurnTimer();
                } else {
                    client.onWait();
                }
            }
       }else{
            System.err.println("Non ho trovato nessun player ");
            this.turnUpdate();
        }
    }

    private boolean playingCrashedPlayer(String onStage) {
        for(int i=0; i < dudesCrashed.length; i++){
            if(onStage.equals(dudesCrashed[i])){
                return true;
            }
        }
        return false;
    }

    //Notifica di aver aggiunto un nuovo player alla partita
    public void subscription() throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.newPlayerAdded();
        }
    }
    public void notifyCompleted() throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.notifyCompleted();
        }
    }
    public void winnerInterface(String s) throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.winnerInterface(s);
        }
    }

    //Una volta giunto al numero giusto di giocatori fa partire la partita
    public void startGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
     dudesInGame = new String[model.getNplayers()];
     playingClients(dudesInGame, logged);
     dudesCrashed = new String[model.getNplayers()-1];
      controller.startGame();}


    public void turnUpdate() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        try{pingClient();
            System.out.println("Nessun client down ");
            controller.turnUpdate();
            }catch (RuntimeException e) {
            if (playingCrashedPlayer(controller.getOnStage())) {
                controller.skipTurn();
                System.err.println("C'è stato un crash di un client, ora giocherà " + controller.getOnStage());
            }
        }
    }

   private void playingClients(String[] nicknames, ArrayList<ClientRMIInterface> subscribed) throws RemoteException {
        for(int i=0; i< model.getNplayers(); i++){
            nicknames[i] = subscribed.get(i).getNickname();
        }
    }
    private void saveCrash(String[] Crash, String formerPlayer){
        for(int i=0; i < dudesCrashed.length; i++){
            if(Crash[i]==null){
                Crash[i]=formerPlayer;
                return;
            }
        }
    }
    private String findCrashedClient(ArrayList<ClientRMIInterface> subscribed){
        for(int i=0; i< model.getNplayers(); i++){
          try{subscribed.get(i).ping();
             } catch (RemoteException e) {
               String nameCrash = dudesInGame[i];
               saveCrash(dudesCrashed, nameCrash);
               dudesInGame[i]=null;
               return nameCrash;
           }
        }
        return "nessuno";
    }
    private boolean checkReEntering(String nick){
        for(int i = 0; i < dudesCrashed.length ; i++){
            if(nick.equals(dudesCrashed[i])){
                return true;
            }
        }
        return false;
    }

    public void ping() throws RemoteException{
        for(ClientRMIInterface client : logged){
            try{client.ping();}
            catch (RemoteException e){
               throw new RemoteException(" Un client è crashato.");
            }
        }
    }
    public void pingClient() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        try{ ping(); }catch (RemoteException e){
            notifyForcedCrash();
            whosHere(dudesCrashed);
            whosHere(dudesInGame);
            swapCrashed();
            checkTimeoutGame();
            if(logged.size()!=1 && logged.size()!=0){
            turnUpdate();}
            throw new RuntimeException("Ping error"); }
    }
    public void checkTimeoutGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {

       if(logged.size()==1){
           startTimer();
           notifyWaitingForReconnection();
        }else{
           System.err.println("Ho cancellato il timer");
           timerCrash.cancel();
           newTurn();
       }

    }
    private void startTimer(){
        System.out.println("Ho avviato il timer per annullare la partita ");
        TimerTask waitPlayers = new TimerTask() {
            @Override
            public void run() {
                System.err.println("Il timer è Scaduto");
                try {
                    notifyNoMorePlayers();
                } catch (RemoteException e) {
                    System.err.println("Qualcosa è successo anche all'unico connesso");
                }
            }
        };

        timerCrash.schedule(waitPlayers, 8000);
    }


    private void startTurnTimer(){
        System.out.println("Con il nuovo turno ho avviato il timer, il client ha 1min per fare la mossa");
        TimerTask turnPlayer = new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("Il client sta ancora decidendo la sua mossa ");
                    notifyPlayerNotResponding();

                } catch (NotEnoughSpaceChoiceException | NotAdjacentSlotsException | NotCatchableException |
                         RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        //Due minuti di timer
        timerTurn.schedule(turnPlayer, 50000);
    }

    private void notifyPlayerNotResponding() throws NotEnoughSpaceChoiceException, RemoteException, NotAdjacentSlotsException, NotCatchableException {
        try{pingClient();}catch (RemoteException | RuntimeException e){
            System.err.println("Client not responding ");
            controller.skipTurn();
            System.err.println("C'è stato un crash di un client, ora giocherà " + controller.getOnStage());}
    }

    private void notifyNoMorePlayers() throws RemoteException {
        for(ClientRMIInterface client : logged){
           client.errorEndGameNoMorePlayers();
        }

    }

    private void notifyWaitingForReconnection() throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.errorMissingPlayers();
        }
    }

    private void whosHere(String[] toPrint){
        for(int i=0; i<toPrint.length; i++){
            System.out.print(toPrint[i] + "\t");
        }
        System.out.println("-");
    }

    private void swapCrashed() {
        for(int i=0; i < dudesInGame.length; i++){
            if(dudesInGame[i]==null){
                logged.remove(i);
                System.out.println("Ho rimosso il client crashato, ora abbiamo ancora: "+ logged.size()+" Giocanti");
                if(logged.size()==1){
                    System.err.println("Ho  notato che c'è solo un client dalla swap! ");
                    //startTimer();
                } if(logged.size()==0){
                    System.err.println("Tutti i giocatori sono crashati! La partita è terminata!");
                    model.setGameOn(false);
                    System.exit(0);
                }
                break;
            }
        }

    }

    public void notifyForcedCrash() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for(ClientRMIInterface client : logged){
            crashThread notifyUser = new crashThread(client);
            try{notifyUser.run();}catch (RuntimeException e){System.err.println("-- Client ko: " + findCrashedClient(logged)) ;}
        }
        System.out.println("Ho notificato tutti i client! Aggiorno al prossimo turno ");
    }

    public void notifyCrashPregame() throws RemoteException{
        for(ClientRMIInterface client : logged){
            crashPreGame notifyUser = new crashPreGame(client);
            try{notifyUser.start();} catch (RuntimeException e){System.err.println("-- Client ko --");}
        }
    }


}






