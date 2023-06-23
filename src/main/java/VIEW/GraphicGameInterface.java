package VIEW;

import Listeners.viewListeners;

import java.util.ArrayList;
import java.util.List;


public class GraphicGameInterface/* UserInterface */{

    private final List<viewListeners> listeners = new ArrayList<>();
    /*private JFrame mainFrame;



    public String firstRun(){
        NickChoice nickChoice = new NickChoice();
        mainFrame.add(nickChoice);
        final String[] nick = {null};
        nickChoice.getConfirm().addActionListener(e -> {
            nick[0] = nickChoice.getNickField().getText();
            if(nick[0].isBlank()){
                JOptionPane.showMessageDialog(this.mainFrame,
                        "Il nickname inserito è vuoto o formato solo da spazi! Sceglierne un'altro!",
                        "ERRORE!",
                        JOptionPane.WARNING_MESSAGE);
                mainFrame.remove(nickChoice);
                firstRun();
            }
        });
        mainFrame.remove(nickChoice);
        return nick[0];
    }

    public void waitingRoom(int enrolledPlayers, int nPlayers) throws IOException {
        WaitingRoom wRoom = new WaitingRoom();
        mainFrame.add(wRoom);
        wRoom.getEnrolledPlayer().setMaximum(nPlayers);
        wRoom.getEnrolledPlayer().setValue(enrolledPlayers);
        wRoom.getEnrolledPlayer().setString("Si sono iscritti "+wRoom.getEnrolledPlayer().getValue()+" su "+wRoom.getEnrolledPlayer().getMaximum());
    }

    public int numberOfPlayers(){
        NumberOfPlayerChoice nPlayersChoice = new NumberOfPlayerChoice();
        mainFrame.add(nPlayersChoice);

        ActionListener a = e -> {
            String selectedOption = e.getActionCommand();
            int choice = -1;
            for(int i = 0; i < nPlayersChoice.getOptions().length; i++){
                if(nPlayersChoice.getOptions()[i].equals(selectedOption)){
                    choice = i;
                }
            }
            nPlayersChoice.setChoice(choice);
        };
        for(Component component : nPlayersChoice.getRootPane().getComponents()){
            if(component instanceof JButton){
                ((JButton) component).addActionListener(a);
            }
        }

        return nPlayersChoice.getChoice()+2;
    }

    public void playing(GameView gameView) throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {
        playerMoveSelection(gameView);
        playerInsert(gameView);
    }

    public void playerMoveSelection(GameView gameView) throws NotEnoughSpaceChoiceException, RemoteException, NotAdjacentSlotsException, NotCatchableException {
        SelectionFrame selectionFrame = new SelectionFrame(gameView);
        mainFrame.add(selectionFrame);
        final boolean[] didSelection = {false};
        final int[] nChoices = {0};
        Object[] options = {1,2,3};
        final int[] k = {0};
        final int[] pos1 = {1};
        final int[] pos2 = {2};
        final int[] pos3 = {3};
        //CREO OPTIONPANE per il numero di scelte e SALVO LA SCELTA USANDO IL LISTENER, rimuovendolo una volta ottenuto nChoices
        JOptionPane nTiles = new JOptionPane();
        nChoices[0] = nTiles.showOptionDialog(null,"Scegliere il numero di tessere da selezionare: ",
                "Inserimento Numero Scelte", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        selectionFrame.add(nTiles, BorderLayout.NORTH);
        ActionListener a = e -> {
            String selectedOption = e.getActionCommand();
            for(int i = 0; i < options.length; i++){
                if(options[i].equals(selectedOption)){
                    nChoices[0] = i+1;
                    selectionFrame.remove(nTiles);
                }
            }
            try {
                notifyChoices(nChoices[0]);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            } catch (NotEnoughSpaceChoiceException ex) {
                throw new RuntimeException(ex);
            } catch (NotAdjacentSlotsException ex) {
                throw new RuntimeException(ex);
            } catch (NotCatchableException ex) {
                throw new RuntimeException(ex);
            }
        };
        for(Component component : nTiles.getRootPane().getComponents()){
            if(component instanceof JButton){
                ((JButton) component).addActionListener(a);
            }
        }
        //CREO "L'ARRAY" DA RIEMPIRE CON LE TILES CON IL BOTTONE CONFERMA
        TilesToOrder[] slot = new TilesToOrder[nChoices[0]];
        SlotChoice[] slotChoices = new SlotChoice[nChoices[0]];
        JPanel selection = new JPanel(new FlowLayout());
        JButton confirm = new JButton("CONFERMA LA TUA SCELTA");
        for(int i = 0; i < nChoices[0]; i++){
            slot[i] = new TilesToOrder(i+1);
            selection.add(slot[i].getLabel());
        }
        selection.add(confirm);
        selectionFrame.add(selection, BorderLayout.NORTH);
        //EFFETTIVA SCELTA: IMPLEMENTAZIONE DEL ACTIONLISTENER CHE CREA LA SLOTCHOICE E LA INSERISCE NELL'ARRAY
        ActionListener choice = e -> {
            TileButton button = (TileButton)e.getSource();
            slotChoices[k[0]] = new SlotChoice(button.getX(), button.getY());
            slot[k[0]].getLabel().setIcon(selectionFrame.getSingleButton(button.getX(), button.getY()).getButton().getIcon());
            k[0]++;
            if(k[0] == nChoices[0]){
                didSelection[0]=true;
            }
        };
        for(int i = 0; i < Dashboard.getSide();i++){
            for(int j = 0; j < Dashboard.getSide();j++){
                selectionFrame.getSingleButton(i,j).getButton().addActionListener(choice);
            }
        }
        //una volta selezionate tutte le tessere, non voglio aggiornamenti dalla dashboard
        if(didSelection[0]){
            for(int i = 0; i < Dashboard.getSide();i++){
                for(int j = 0; j < Dashboard.getSide();j++){
                    selectionFrame.getSingleButton(i,j).getButton().removeActionListener(choice);
                }
            }
            k[0] = 0;
            notifySelectedCoordinates(slotChoices);
        }

        //GESTIONE DELL'ORDINAMENTO DRAG&DROP MOUSE LISTENER
        for(int i = 0; i < nChoices[0]; i++){
            slot[i].getLabel().addMouseListener(new OrderListener());
            slot[i].getLabel().addMouseMotionListener(new OrderListener());
            slot[i].getLabel().setTransferHandler(new TransferHandler("tile"));
        }
        //BUTTON SUBMIT CHE ATTIVA LA NOTIFY
        ActionListener confirmOrder = submit ->{
            if(nChoices[0] == 3) {
                if (slot[0].getPosition() != pos1[0]) {
                    pos1[0] = slot[0].getPosition();
                }
                if (slot[1].getPosition() != pos2[0]) {
                    pos2[0] = slot[1].getPosition();
                }
                if (slot[2].getPosition() != pos3[0]) {
                    pos3[0] = slot[2].getPosition();
                }
                OrderChoice order = new OrderChoice(pos1[0], pos2[0], pos3[0]);
                try {
                    notifyOrder(order);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                } catch (NotEnoughSpaceChoiceException e) {
                    throw new RuntimeException(e);
                } catch (NotAdjacentSlotsException e) {
                    throw new RuntimeException(e);
                } catch (NotCatchableException e) {
                    throw new RuntimeException(e);
                }
            }
            if(nChoices[0] == 2){
                if(slot[0].getPosition() != pos1[0]){
                    OrderChoice order = new OrderChoice(1,1,1);
                    try {
                        notifyOrder(order);
                        mainFrame.remove(selectionFrame);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    } catch (NotEnoughSpaceChoiceException e) {
                        throw new RuntimeException(e);
                    } catch (NotAdjacentSlotsException e) {
                        throw new RuntimeException(e);
                    } catch (NotCatchableException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
    }

    public void playerInsert(GameView gameView){
        InsertFrame insertFrame = new InsertFrame(gameView);
        mainFrame.add(insertFrame);
        ActionListener a = e -> {
            InsertButton insertChoice = (InsertButton) e.getSource();
            try {
                notifyInsert(insertChoice.getIndex());
                mainFrame.remove(insertFrame);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            } catch (NotEnoughSpaceChoiceException ex) {
                throw new RuntimeException(ex);
            } catch (NotAdjacentSlotsException ex) {
                throw new RuntimeException(ex);
            } catch (NotCatchableException ex) {
                throw new RuntimeException(ex);
            }
        };
        for(Component component : insertFrame.getShelfWithB().getRootPane().getComponents()){
            if(component instanceof InsertButton){
                ((InsertButton) component).addActionListener(a);
            }
        }
    }


    public GraphicGameInterface(){
        this.mainFrame = new JFrame("MY SHELFIE");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(true);
    }

    @Override
    public void addviewEventListener(viewListeners listener) {
        listeners.add(listener);
        JDialog info = new JDialog(mainFrame, "Creato bond client / view");
    }

    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException, NotCatchableException, NotAdjacentSlotsException, NotEnoughSpaceChoiceException {
        for( viewListeners listener : listeners  ) {
            listener.notifySelectedCoordinates(SC);
        }
    }

    @Override
    public void notifyOrder(OrderChoice o) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for( viewListeners listener : listeners  ) {
            try {
                listener.notifyOrder(o);
            } catch (RemoteException e) {
                System.out.println("ciao");
            } catch (NotEnoughSpaceChoiceException e) {
                throw new RuntimeException(e);
            } catch (NotAdjacentSlotsException e) {
                throw new RuntimeException(e);
            } catch (NotCatchableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for( viewListeners listener : listeners  ) {
            listener.notifyInsert(column);
        }
    }

    @Override
    public void notifyOneMoreTime() throws SameNicknameException, RemoteException {
        for( viewListeners listener : listeners  ) {
            listener.notifyOneMoreTime();
        }
    }

    @Override
    public void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for( viewListeners listener : listeners  ) {
            listener.notifyChoices(number);
        }
    }

    @Override
    public void run() {

    }

    public void startTurn(){
        JDialog info = new JDialog(mainFrame, "Inizio del nuovo turno!");
    }

    public void onWait(GameView gameView){
        NotPlayingPlayer onWait = new NotPlayingPlayer(gameView);
    }

    public void errorNotCatchable() {
        ErrorPane error = new ErrorPane("La tessera selezionata non è prendibile! Ripetere la selezione!");
        mainFrame.add(error);
        error.setVisible(true);
    }

    public void errorOneNotCatchable() {
        ErrorPane error = new ErrorPane("Una delle tessere selezionate non è prendibile! Ripetere la selezione!");
        mainFrame.add(error);
        error.setVisible(true);
    }

    public void errorNotAdjacent() {
        ErrorPane error = new ErrorPane("Le tessere selezionate non sono adiacenti! Ripetere la selezione!");
        mainFrame.add(error);
        error.setVisible(true);
    }

    public void errorSpaceChoicesError() {
        ErrorPane error = new ErrorPane("Non c'è abbastanza spazio nella shelf per così tante tessere!");
        mainFrame.add(error);
        error.setVisible(true);
    }

    public void errorInsert() {
        ErrorPane error = new ErrorPane("La colonna selezionata non ha abbastanza spazio per tutte le tessere! Scegline un'altra!");
        mainFrame.add(error);
        error.setVisible(true);
    }

    public void endgame(){
        JDialog info = new JDialog(mainFrame, "Il gioco è finito! ");
    }

    public void denyAcess() {
       JDialog info = new JDialog(mainFrame, "La partita è già iniziata, troppo tardi :/ ");
    }

    public void playerCrash() {
        JOptionPane.showMessageDialog(mainFrame, "E' crashato un player!", "CRASH", JOptionPane.WARNING_MESSAGE);
    }

    public void gameCancelled() {
        JDialog info = new JDialog(mainFrame, "E' crashato un player in pregame, chiusura della partita, scusate! ");}

    public void waitingForPlayers() {
        JOptionPane.showMessageDialog(mainFrame, "Non ci sono abbastanza giocatori per continuare, attesa riconnessione o fine partita in 10s ", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
        }

    public void errorNick(String message) throws SameNicknameException, RemoteException{
*/
    }


