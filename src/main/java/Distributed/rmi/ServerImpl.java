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
    private ClientRMIInterface[] effectiveLogged;
    private String[] dudesCrashed ;
    private String[] dudesInGame ;
    private Timer timerCrash = new Timer();
    private final Timer timerTurn = new Timer();

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
        //Primo controllo alla prima richiesta di connessione, nel caso in cui sia la prima volta
        //Viene creato il nuovo model + controller
        if (logged.size()==0) {
            model = new Game(client.startGame());
            controller = new GameController(model);
            this.model.addGameEventListener(this);
            model.signPlayer(client.getNickname());
            this.logged.add(client);
            //Se il primo è già iscritto si passa a questa parte del codice in cui vengono distinti tre casi principali
        } else {
            if(!model.isGameOn() && model.getCurrentState().equals(GameState.LOGIN)){
            //Ramo try-catch per verificare un crash in fase di preparazione (forza la chiusura), controlla che ogni client iscritto
            //Sia ancora attivo, in caso manda la partita in chiusura.
            try {
                this.pingInPreGame();
            } catch (RemoteException e) {
                notifyCrashPregame();
                client.subscriptionCancelled();
            }
            //Tentativo d'iscrizione quando il game non è anora partito, accetta tutti i client a patto che non abbiano lo stesso nick
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
                //Nel caso in cui non siamo nello stato di LOGIN viene controllato se il tentativo viene da uno dei client crashati precedentemente
                //Altrimenti andrà a notificare tale client che la partita è già inziata, non permettendo un ingresso
            }else if (checkReEntering(client.getNickname())) {
                //Questo else-if mette in condizione il server di accettare una riconessione di un client
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


    //Primo metodo di controllo del client, controlla che sia uno di quelli crashati e lo rimette in partita
    private void backInGame(ClientRMIInterface client) throws RemoteException {
        //Manca controllo della posizione corretta, si fa con array presente in model
        int reenteringPosition = model.positionInGame(client.getNickname());
        dudesInGame[reenteringPosition] = client.getNickname();
        effectiveLogged[reenteringPosition]=client;
    }

    //Metodo remoto: usato dal client quando un utente ha selezionato delle coordinate
    @Override
    public void updateServerSelection(ClientRMIInterface client, SlotChoice[] SC) throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException { //throws NotAdjacentSlotsException, NotCatchableException {
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
            System.out.println("Inserimento corretto, Passo al prossimo giocatore \n");
            this.turnUpdate();
        } catch (NotEnoughSpaceChoiceException e) {
            throw new NotEnoughSpaceChoiceException(e.getMessage());
        }
    }

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

    //Viene aggiunto il Listener al gioco
    @Override
    public void addGameEventListener(GameEventListener listener) {
        System.out.println("Client registrato con successo! ");
    }

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

    //Notifica al client la nuova view dopo che un client ha finito il proprio turno, con la PersonalShelf
    @Override
    public void turnIsOver() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        pingGameOn();
        for (ClientRMIInterface client : effectiveLogged) {
            if (client != null) {
                if (controller.getOnStage().equals(client.getNickname())) {
                    client.updateClientPlaying(new GameView(model));
                    client.endTurn();
                } else {
                    client.updateClientRound(new GameView(model));
                }
            }else{
                System.err.println("Discarding event for fallen client");
            }
        }
        this.newTurn();
    }

    //Notifica al client che abbiamo iniziato la partita
    @Override
    public void readyToStart() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        this.newTurn();
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
            System.err.println("Il giocatore selezionato non è valido, passo al prossimo");
            controller.skipTurn();
        }else{
            this.newTurn();
        }
    }

    //Se il modello ha notificato un errore, verrà segnalato al client di turno.
    @Override
    public void notifyLastError() throws RemoteException {
        System.err.println("Ho generato un errore ");
        for (ClientRMIInterface client : effectiveLogged) {
           if(client!=null) {
               if (controller.getOnStage().equals(client.getNickname())) {
                   client.updateClientError(new GameView(model, -1));
               }
           }
        }
    }


    //Rispetto a tutti i client iscritti manda la notifica di "via libera" al client di turno.
    //Ad ogni nuovo turno si va a verificare che tutti gli utenti iscritti non siano andati in crash.
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


    //Metodo usato nel caso di rientro di una persona nel game, va a controllare il nick faccia parte dei crashati
    private boolean playingCrashedPlayer(String onStage) {
        for (int i = 0; i < dudesCrashed.length; i++) {
            if (onStage.equals(dudesCrashed[i])) {
                return true;
            }
        }
      return false;
    }

    //Notifica di aver aggiunto un nuovo player alla partita
    public void subscription() throws RemoteException {
        for (ClientRMIInterface client : logged) {
            client.newPlayerAdded();
        }
    }

    public void notifyCompleted() throws RemoteException {
        for (ClientRMIInterface client : effectiveLogged) {
            if(client!=null) {
                client.notifyCompleted();
            }
        }
    }

    public void winnerInterface(String s) throws RemoteException {
        for (ClientRMIInterface client : effectiveLogged) {
            if(client!=null) {
                client.winnerInterface(s);
            }
        }
    }

    //Una volta giunto al numero giusto di giocatori fa partire la partita, salvando in modo finale i nick e il numero massimo
    //Di disconnessioni ammissinbili
    private void startGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        dudesInGame = new String[model.getNplayers()];
        dudesCrashed = new String[model.getNplayers()];
        effectiveLogged = logged.toArray(new ClientRMIInterface[model.getNplayers()]);
        playingClients(dudesInGame, logged);
        controller.startGame();
    }

    //A ogni turn update viene controllato che nessuno sia crashato, che non sia il player che doveva giocare come prossimo
    private void turnUpdate() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        controller.turnUpdate();
    }

    //Passaggio dalla struttura dinamica a una struttura statica
    private void playingClients(String[] nicknames, ArrayList<ClientRMIInterface> subscribed) throws RemoteException {
        for (int i = 0; i < model.getNplayers(); i++) {
            nicknames[i]  = subscribed.get(i).getNickname();
        }
    }
    //metodo per il rientro
    private boolean checkReEntering(String nick) {
        for (int i = 0; i < dudesCrashed.length; i++) {
            if (nick.equals(dudesCrashed[i])) {
                dudesCrashed[i]=null;
                return true;
            }
        }
        return false;
    }


    //Controlla che ci siano sempre tutti i client chimando un metodo di "check" all'interno del client
    private void pingInPreGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        //Viene fatto un ciclo per contattare tutti i client
        for(int i = 0; i < logged.size(); i++){
           try {
               logged.get(i).ping();
           }catch (RemoteException e){
               System.err.println("Crash in preGame :( ");
               System.exit(0);
           }
        }

    }
    private void pingGameOn() throws NotEnoughSpaceChoiceException, RemoteException, NotAdjacentSlotsException, NotCatchableException {
        boolean crash = false;
        boolean inTurn = false;
        //Viene fatto un ciclo per contattare tutti i client
        for(int i = 0; i < effectiveLogged.length; i++){
            try {if(effectiveLogged[i]!=null){
                effectiveLogged[i].ping();}
            }catch (RemoteException e){
                if(controller.getOnStage().equals(dudesInGame[i])){
                    inTurn = true;
                }
                //Nel caso in cui ci sia una remoteException nella data posizione viene preso il corrispettivo all'interno
                //Del array statico che riporta tutti i nomi dei giocatori della partita
                System.out.println("Il giocatore " + dudesInGame[i] + " non risponde");
                //A questo punto viene preso e salvato nella struttura dati speculare che altrimenti sarebbe vuota
                saveCrash(i, dudesInGame[i]);
                //La posizione viene resa nulla.
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

    private void crashRoutine() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        //Finito con tutti i client si va a vedere quali sono crashati e levati dalla lista raggiungibile del server
        System.out.println("Aggiornamento a seguito del crash.. ");
        swapCrash();
        //Stampa per avere la sicurezza che vengano levati tutti i gioocatori.
        System.out.println("I giocatori ancora attivi sono: ");
        whosHere(dudesInGame);
        System.out.println("I giocatori crashati sono: ");
        whosHere(dudesCrashed);
        notifyForcedCrash();
        //Ora si procede a operare sul turno in base a quanti client sono crashati
        checkTimeoutGame();
    }
    //Sfruttando il fatto che ci siano solo strutture statiche viene aggiornato in tutte la posizione corretta in cui il client ha failato
    //In modo da avere tale posizione nulla e comportarsi di conseguenza
    //Una copia è sempre tenuta nella struttura di "backup" che si assicura che i client iscritti alla partita rimangano (come nomi)
    //i medesimi
    private void swapCrash(){
    //Devo riaggiornare la nuova posizione, facendo un sort dei valori nulli in cima, simulando il comportamento di una lista
        for(int i = 0; i < dudesCrashed.length; i++){
            if(dudesCrashed[i]!=null){
                effectiveLogged[i]=null;
                dudesInGame[i]=null;
            }
        }
      System.out.println("Swap completata, giocatori eliminati ");
    }
    //Generico metodo per stabilire le posizioni non nulle (dunque effettive) di un Array.
    private int realLength(Object[] o){
        int realLength=0;
        for(int i = 0; i < o.length; i++){
            if(o[i]!=null){
                realLength++;
            }
        }
        return realLength;
    }

    //Quando un giocatore crasha viene salvato in un array che permette di tener traccia dei nomi degli stessi
    private void saveCrash(int position, String formerPlayer) {
        dudesCrashed[position]=formerPlayer;
    }
    //Stampa il contenuto di un generico array
    private void whosHere(String[] toPrint) {
        for (Object s : toPrint) {
            System.out.print(s + "\t");
        }
        System.out.println("-");
    }

    //Nel caso in cui il giocatore era rimasto solo viene messo il gioco in pausa, questa funzione fa riprendere il gioco
    private void restartGameAfterCrash() throws RemoteException{
       //Manca riprendere il gioco
        System.err.println("Timer di fine partita annullato ");
        timerCrash.cancel();
        timerCrash = new Timer();
        controller.switchGameState();
    }

    //Nel caso in cui sia rimasto un solo client si andrà ad attivare un timer per aspettare le riconnessioni
    private void checkTimeoutGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        if (realLength(effectiveLogged) == 1) {
            startTimer();
            notifyWaitingForReconnection();
            //Manca di riprendere il game
            controller.switchGameState();
        }
    }


    //Timer di timerout alla partita
    private void startTimer() {

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
      timerCrash.schedule(waitPlayers, 8000); //metterci 30000
    }

    //Avvia un timer per andare a controllare che i giocatori siano raggiungibili, se è quello di turno allo skippo.
    private void startTurnTimer() {
        System.out.println("Ho avviato il timer per controllare lo stato dei client ");
        TimerTask turnPlayer = new TimerTask() {
            @Override
            public void run() {
                    System.out.println("Il client sta ancora decidendo la sua mossa ");
                try {
                    pingGameOn();
                    startTurnTimer();
                } catch (RemoteException | NotEnoughSpaceChoiceException | NotAdjacentSlotsException |
                         NotCatchableException e) {
                    System.out.println("Il giocante è down ");
                }
            }
        };
        //Due minuti di timer
        timerTurn.schedule(turnPlayer, 60000);

    }
    public void notifyForcedCrash() throws RemoteException {
        for (ClientRMIInterface client : effectiveLogged) {
            if(client!=null) {
                client.errorCrash();
            }
        }
    }
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
    //Quando non ci sono abbastanza giocatori
    private void notifyNoMorePlayers() throws RemoteException {
        for(ClientRMIInterface client : effectiveLogged){
            if(client!=null) {
                client.errorEndGameNoMorePlayers();
            }
        }

    }
    //Notifica all'ultimo rimasto un'eventuale fine partita
    private void notifyWaitingForReconnection() throws RemoteException {
        for(ClientRMIInterface client : effectiveLogged){
            if(client!=null) {
                client.errorMissingPlayers();
            }else{
                System.err.println("Discarding event for fallen client");
            }
        }
    }

}






