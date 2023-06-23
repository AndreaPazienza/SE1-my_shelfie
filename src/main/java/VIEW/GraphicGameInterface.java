package VIEW;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import Listeners.OrderListener;
import Listeners.viewListeners;
import MODEL.Color;
import MODEL.Dashboard;
import MODEL.GameView;
import MODEL.PersonalGoal;
import MODEL.PersonalShelf;
import VIEW.GraphicObjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GraphicGameInterface implements Runnable, viewListeners, UserInterface{

    private final List<viewListeners> listeners = new ArrayList<>();
    private JFrame mainFrame;

    private WaitingRoom waitingRoom = new WaitingRoom();

    private JPanel goals = new JPanel(new FlowLayout());
    private GameView lastGameview;
    public String firstRun(){
        NickChoice nickChoice = new NickChoice();
        nickChoice.setPreferredSize(new Dimension(100,100));
        JOptionPane.showMessageDialog(mainFrame, nickChoice, "Inserire il Nickname: ", JOptionPane.PLAIN_MESSAGE);
        /*mainFrame.getContentPane().add(nickChoice);
        mainFrame.revalidate();
        mainFrame.pack();*/
        String nick = nickChoice.getNick();
        if(nick.isBlank()) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Il nickname inserito è vuoto o formato solo da spazi! Sceglierne un'altro!",
                            "ERRORE!",
                            JOptionPane.WARNING_MESSAGE);
                    //mainFrame.remove(nickChoice);
                    return firstRun();
                }
        //mainFrame.remove(nickChoice);
        return nick;
    }


    public void waitingRoom(int enrolledPlayers, int nPlayers) throws IOException {
        JFrame tmp = mainFrame;
        if(mainFrame.getContentPane().getComponentCount() == 0) {
            setMainFrame(waitingRoom);
            waitingRoom.getEnrolledPlayer().setMaximum(nPlayers);
            waitingRoom.getEnrolledPlayer().setValue(enrolledPlayers);
            waitingRoom.getEnrolledPlayer().setString("Si sono iscritti "+waitingRoom.getEnrolledPlayer().getValue()+" su "+waitingRoom.getEnrolledPlayer().getMaximum());
        } else {
            waitingRoom.getEnrolledPlayer().setValue(enrolledPlayers);
            waitingRoom.getEnrolledPlayer().setString("Si sono iscritti "+waitingRoom.getEnrolledPlayer().getValue()+" su "+waitingRoom.getEnrolledPlayer().getMaximum());
        }
        if(enrolledPlayers == nPlayers){
            waitingRoom = null;
            setMainFrame(new JFrame());
            mainFrame.setLayout(new BorderLayout());
            mainFrame.add(goals, BorderLayout.SOUTH);
        }
        //mainFrame.pack();
        //waitingRoom.getEnrolledPlayer().setMaximum(nPlayers);
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
        mainFrame.remove(nPlayersChoice);
        return nPlayersChoice.getChoice()+2;
    }

    public void playing() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {
        playerMoveSelection();
        playerInsert();
    }

    public void playerMoveSelection() throws NotEnoughSpaceChoiceException, RemoteException, NotAdjacentSlotsException, NotCatchableException {
        //SelectionFrame selectionFrame = new SelectionFrame(lastGameview);
        //mainFrame = selectionFrame;
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
        mainFrame.add(nTiles, BorderLayout.NORTH);
        ActionListener a = e -> {
            String selectedOption = e.getActionCommand();
            for(int i = 0; i < options.length; i++){
                if(options[i].equals(selectedOption)){
                    nChoices[0] = i+1;
                    mainFrame.remove(nTiles);
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
        mainFrame.add(selection, BorderLayout.NORTH);
        //EFFETTIVA SCELTA: IMPLEMENTAZIONE DEL ACTIONLISTENER CHE CREA LA SLOTCHOICE E LA INSERISCE NELL'ARRAY
        ActionListener choice = e -> {
            TileButton button = (TileButton)e.getSource();
            slotChoices[k[0]] = new SlotChoice(button.getX(), button.getY());
            slot[k[0]].getLabel().setIcon(button.getButton().getIcon());
            k[0]++;
            if(k[0] == nChoices[0]){
                didSelection[0]=true;
            }
        };
        //for(com)
        for(Component component : mainFrame.getComponents()){
            if(component instanceof TileButton){
                ((TileButton) component).getButton().addActionListener(choice);
            }
        }
        //una volta selezionate tutte le tessere, non voglio aggiornamenti dalla dashboard
        if(didSelection[0]){
            for(Component component : mainFrame.getComponents()){
                if(component instanceof TileButton){
                    ((TileButton) component).getButton().removeActionListener(choice);
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

    public void playerInsert() throws RemoteException, NotCatchableException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException{
        /*InsertFrame insertFrame = new InsertFrame(lastGameview);
        mainFrame = insertFrame;*/
        ActionListener a = e -> {
            InsertButton insertChoice = (InsertButton) e.getSource();
            try {
                notifyInsert(insertChoice.getIndex());
                //mainFrame.remove(insertFrame);
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
        for(Component component : mainFrame.getComponents()){
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
    public void notifyOneMoreTime() throws SameNicknameException, IOException {
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

    public void onWait(){
        NotPlayingPlayer onWait = new NotPlayingPlayer(lastGameview);
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

    }

    public void notifyAlmostOver(){}
    public void displayWin(){
    }

    public void displayDashboard(GameView gameView){
        TileButton[][] tiles = new TileButton[Dashboard.getSide()][Dashboard.getSide()];
        ImageIcon backgroundDash = new ImageIcon("src/main/GraphicResources/boards/livingroom.png");
        JLabel backgroudDashLabel = new JLabel(backgroundDash);

        JPanel board = new JPanel(new GridLayout(Dashboard.getSide(), Dashboard.getSide()));
        tiles = new TileButton[Dashboard.getSide()][Dashboard.getSide()];

        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                tiles[i][j] = new TileButton(new JButton(),i,j);
                tiles[i][j].getButton().setPreferredSize(new Dimension(50, 50));
                board.add(tiles[i][j].getButton());
            }
        }
        createDashoard(gameView,tiles);
        backgroudDashLabel.setLayout(new GridBagLayout());
        backgroudDashLabel.add(board);
        mainFrame.add(backgroudDashLabel, BorderLayout.CENTER);
    }

    public void displayPersonalShelf(GameView gameView){
        ImageIcon backgroundInsert = new ImageIcon("src/main/GraphicResources/boards/bookshelf.png");
        JLabel backgroundInsertLabel = new JLabel(backgroundInsert);

        JPanel shelf = new JPanel(new GridLayout(PersonalShelf.N_ROWS, PersonalShelf.N_COLUMN));
        JLabel[][] shelfHole = new JLabel[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
        JPanel columnButtons = new JPanel(new FlowLayout());
        JPanel shelfWithB = new JPanel();
        shelfWithB.setLayout(new BoxLayout(shelfWithB, BoxLayout.Y_AXIS));

        for(int i = 0; i < PersonalShelf.N_COLUMN; i++){
            InsertButton colButton = new InsertButton();
            colButton.setIndex(i);
            columnButtons.add(colButton);
        }

        shelfWithB.add(columnButtons);

        for(int i = 0; i < PersonalShelf.N_ROWS; i++){
            for(int j = 0; j < PersonalShelf.N_COLUMN;j++){
                shelfHole[i][j] = new JLabel();
                shelfHole[i][j].setPreferredSize(new Dimension(50,50));
                shelf.add(shelfHole[i][j]);
            }
        }
        createShelf(gameView, shelfHole);
        backgroundInsertLabel.setLayout(new GridBagLayout());
        backgroundInsertLabel.add(shelf);
        shelfWithB.add(backgroundInsertLabel);
        mainFrame.add(shelfWithB, BorderLayout.WEST);
    }

    public void displayCommonGoal(GameView gameView){
        ImageIcon backgroundCGoal1 = gameView.getCommonGoal1().getImage();
        JLabel backgroundCGoal1Label = new JLabel(backgroundCGoal1);
        ImageIcon backgroundCGoal2 = gameView.getCommonGoal2().getImage();
        JLabel backgroundCGoal2Label = new JLabel(backgroundCGoal2);
        goals.add(backgroundCGoal1Label);
        goals.add(backgroundCGoal2Label);
    }

    public void displayPersonalGoal(GameView gameView){
        ImageIcon backgroundPGoal = gameView.getPgoal().getImage();
        JLabel backgroundPGoalLabel = new JLabel(backgroundPGoal);
        goals.add(backgroundPGoalLabel);
    }

    public void createDashoard(GameView gameView, TileButton[][] buttons) {
        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                puttingTiles(gameView, buttons, i, j);
            }
        }
    }

    private void puttingTiles(GameView gameView, TileButton[][] tiles, int i, int j) {
        if (!gameView.getTable().getSingleSlot(i, j).getColor().Equals(MODEL.Color.BLACK) &&
                !gameView.getTable().getSingleSlot(i, j).getColor().Equals(Color.GREY)) {
            if (gameView.getTable().getSingleSlot(i, j).getType().equals(MODEL.Type.TYPE1)) {
                switch (gameView.getTable().getSingleSlot(i, j).getColor()) {
                    case PINK -> {
                        ImageIcon pink = new ImageIcon("src/main/GraphicResources/item tiles/Piante1.1.png");
                        tiles[i][j].getButton().setIcon(pink);
                    }
                    case GREEN -> {
                        ImageIcon green = new ImageIcon("src/main/GraphicResources/item tiles/Gatti1.1.png");
                        tiles[i][j].getButton().setIcon(green);
                    }
                    case YELLOW -> {
                        ImageIcon yellow = new ImageIcon("src/main/GraphicResources/item tiles/Giochi1.1.png");
                        tiles[i][j].getButton().setIcon(yellow);
                    }
                    case WHITE -> {
                        ImageIcon white = new ImageIcon("src/main/GraphicResources/item tiles/Libri1.1.png");
                        tiles[i][j].getButton().setIcon(white);
                    }
                    case LBLUE -> {
                        ImageIcon lBlue = new ImageIcon("src/main/GraphicResources/item tiles/Trofei1.1.png");
                        tiles[i][j].getButton().setIcon(lBlue);
                    }
                    case BLUE -> {
                        ImageIcon blue = new ImageIcon("src/main/GraphicResources/item tiles/Cornici1.1.png");
                        tiles[i][j].getButton().setIcon(blue);
                    }
                }
            }
        }
        if (gameView.getTable().getSingleSlot(i, j).getType().equals(MODEL.Type.TYPE2)) {
            switch (gameView.getTable().getSingleSlot(i, j).getColor()) {
                case PINK -> {
                    ImageIcon pink = new ImageIcon("src/main/GraphicResources/item tiles/Piante1.2.png");
                    tiles[i][j].getButton().setIcon(pink);
                }
                case GREEN -> {
                    ImageIcon green = new ImageIcon("src/main/GraphicResources/item tiles/Gatti1.2.png");
                    tiles[i][j].getButton().setIcon(green);
                }
                case YELLOW -> {
                    ImageIcon yellow = new ImageIcon("src/main/GraphicResources/item tiles/Giochi1.2.png");
                    tiles[i][j].getButton().setIcon(yellow);
                }
                case WHITE -> {
                    ImageIcon white = new ImageIcon("src/main/GraphicResources/item tiles/Libri1.2.png");
                    tiles[i][j].getButton().setIcon(white);
                }
                case LBLUE -> {
                    ImageIcon lBlue = new ImageIcon("src/main/GraphicResources/item tiles/Trofei1.2.png");
                    tiles[i][j].getButton().setIcon(lBlue);
                }
                case BLUE -> {
                    ImageIcon blue = new ImageIcon("src/main/GraphicResources/item tiles/Cornici1.2.png");
                    tiles[i][j].getButton().setIcon(blue);
                }
            }
        }
        if (gameView.getTable().getSingleSlot(i, j).getType().equals(MODEL.Type.TYPE3)) {
            switch (gameView.getTable().getSingleSlot(i, j).getColor()) {
                case PINK -> {
                    ImageIcon pink = new ImageIcon("src/main/GraphicResources/item tiles/Piante1.3.png");
                    tiles[i][j].getButton().setIcon(pink);
                }
                case GREEN -> {
                    ImageIcon green = new ImageIcon("src/main/GraphicResources/item tiles/Gatti1.3.png");
                    tiles[i][j].getButton().setIcon(green);
                }
                case YELLOW -> {
                    ImageIcon yellow = new ImageIcon("src/main/GraphicResources/item tiles/Giochi1.3.png");
                    tiles[i][j].getButton().setIcon(yellow);
                }
                case WHITE -> {
                    ImageIcon white = new ImageIcon("src/main/GraphicResources/item tiles/Libri1.3.png");
                    tiles[i][j].getButton().setIcon(white);
                }
                case LBLUE -> {
                    ImageIcon lBlue = new ImageIcon("src/main/GraphicResources/item tiles/Trofei1.3.png");
                    tiles[i][j].getButton().setIcon(lBlue);
                }
                case BLUE -> {
                    ImageIcon blue = new ImageIcon("src/main/GraphicResources/item tiles/Cornici1.3.png");
                    tiles[i][j].getButton().setIcon(blue);
                }
            }
        }
    }

    public void createShelf(GameView gameView, JLabel[][] shelfHole) {
        for(int i = 0; i < PersonalShelf.N_ROWS; i++){
            for(int j = 0; j < PersonalShelf.N_COLUMN; j++){
                puttingTiles(gameView, shelfHole, i, j);
            }
        }
    }

    private void puttingTiles(GameView gameView, JLabel[][] tiles, int i, int j) {
        if (!gameView.getShelf().getSingleSlot(i, j).getColor().Equals(MODEL.Color.BLACK) &&
                !gameView.getShelf().getSingleSlot(i, j).getColor().Equals(MODEL.Color.GREY)) {
            if (gameView.getShelf().getSingleSlot(i, j).getType().equals(MODEL.Type.TYPE1)) {
                switch (gameView.getShelf().getSingleSlot(i, j).getColor()) {
                    case PINK -> {
                        ImageIcon pink = new ImageIcon("src/main/GraphicResources/item tiles/Piante1.1.png");
                        tiles[i][j].setIcon(pink);
                    }
                    case GREEN -> {
                        ImageIcon green = new ImageIcon("src/main/GraphicResources/item tiles/Gatti1.1.png");
                        tiles[i][j].setIcon(green);
                    }
                    case YELLOW -> {
                        ImageIcon yellow = new ImageIcon("src/main/GraphicResources/item tiles/Giochi1.1.png");
                        tiles[i][j].setIcon(yellow);
                    }
                    case WHITE -> {
                        ImageIcon white = new ImageIcon("src/main/GraphicResources/item tiles/Libri1.1.png");
                        tiles[i][j].setIcon(white);
                    }
                    case LBLUE -> {
                        ImageIcon lBlue = new ImageIcon("src/main/GraphicResources/item tiles/Trofei1.1.png");
                        tiles[i][j].setIcon(lBlue);
                    }
                    case BLUE -> {
                        ImageIcon blue = new ImageIcon("src/main/GraphicResources/item tiles/Cornici1.1.png");
                        tiles[i][j].setIcon(blue);
                    }
                }
            }
        }
        if (gameView.getShelf().getSingleSlot(i, j).getType().equals(MODEL.Type.TYPE2)) {
            switch (gameView.getShelf().getSingleSlot(i, j).getColor()) {
                case PINK -> {
                    ImageIcon pink = new ImageIcon("src/main/GraphicResources/item tiles/Piante1.2.png");
                    tiles[i][j].setIcon(pink);
                }
                case GREEN -> {
                    ImageIcon green = new ImageIcon("src/main/GraphicResources/item tiles/Gatti1.2.png");
                    tiles[i][j].setIcon(green);
                }
                case YELLOW -> {
                    ImageIcon yellow = new ImageIcon("src/main/GraphicResources/item tiles/Giochi1.2.png");
                    tiles[i][j].setIcon(yellow);
                }
                case WHITE -> {
                    ImageIcon white = new ImageIcon("src/main/GraphicResources/item tiles/Libri1.2.png");
                    tiles[i][j].setIcon(white);
                }
                case LBLUE -> {
                    ImageIcon lBlue = new ImageIcon("src/main/GraphicResources/item tiles/Trofei1.2.png");
                    tiles[i][j].setIcon(lBlue);
                }
                case BLUE -> {
                    ImageIcon blue = new ImageIcon("src/main/GraphicResources/item tiles/Cornici1.2.png");
                    tiles[i][j].setIcon(blue);
                }
            }
        }
        if (gameView.getShelf().getSingleSlot(i, j).getType().equals(MODEL.Type.TYPE3)) {
            switch (gameView.getShelf().getSingleSlot(i, j).getColor()) {
                case PINK -> {
                    ImageIcon pink = new ImageIcon("src/main/GraphicResources/item tiles/Piante1.3.png");
                    tiles[i][j].setIcon(pink);
                }
                case GREEN -> {
                    ImageIcon green = new ImageIcon("src/main/GraphicResources/item tiles/Gatti1.3.png");
                    tiles[i][j].setIcon(green);
                }
                case YELLOW -> {
                    ImageIcon yellow = new ImageIcon("src/main/GraphicResources/item tiles/Giochi1.3.png");
                    tiles[i][j].setIcon(yellow);
                }
                case WHITE -> {
                    ImageIcon white = new ImageIcon("src/main/GraphicResources/item tiles/Libri1.3.png");
                    tiles[i][j].setIcon(white);
                }
                case LBLUE -> {
                    ImageIcon lBlue = new ImageIcon("src/main/GraphicResources/item tiles/Trofei1.3.png");
                    tiles[i][j].setIcon(lBlue);
                }
                case BLUE -> {
                    ImageIcon blue = new ImageIcon("src/main/GraphicResources/item tiles/Cornici1.3.png");
                    tiles[i][j].setIcon(blue);
                }
            }
        }
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}


