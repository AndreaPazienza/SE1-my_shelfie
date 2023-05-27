package VIEW;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import Listeners.viewListeners;
import MODEL.Dashboard;
import MODEL.GameView;
import MODEL.Player;
import VIEW.GraphicObjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GraphicGameInterface implements Runnable, viewListeners {

    private final List<viewListeners> listeners = new ArrayList<>();
    private JFrame mainFrame;

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
        playerInsert();
    }

    private void playerMoveSelection(GameView gameView) {
        SelectionFrame selectionFrame = new SelectionFrame(gameView);
        mainFrame.add(selectionFrame);
        final int[] nChoices = {0};
        Object[] options = {1,2,3};
        final int[] k = {0};
        //CREO OPTIONPANE e SALVO LA SCELTA USANDO IL LISTENER, rimuovendolo una volta ottenuto nChoices
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
        };
        for(Component component : nTiles.getRootPane().getComponents()){
            if(component instanceof JButton){
                ((JButton) component).addActionListener(a);
            }
        }
        //CREO "L'ARRAY" DA RIEMPIRE CON LE TILES CON IL BOTTONE CONFERMA
        JLabel[] slot = new JLabel[nChoices[0]];
        SlotChoice[] slotChoices = new SlotChoice[nChoices[0]];
        JPanel selection = new JPanel(new FlowLayout());
        for(int i = 0; i < nChoices[0]; i++){
            slot[i] = new JLabel();
            selection.add(slot[i]);
        }
        selection.add(new JButton("CONFERMA LA TUA SCELTA!"));
        selectionFrame.add(selection, BorderLayout.NORTH);
        //EFFETTIVA SCELTA: IMPLEMENTAZIONE DEL ACTIONLISTENER CHE CREA LA SLOTCHOICE E LA INSERISCE NELL'ARRAY
        ActionListener choice = e -> {
            TileButton button = (TileButton)e.getSource();
            slotChoices[k[0]] = new SlotChoice(button.getX(), button.getY());
            slot[k[0]].setIcon(selectionFrame.getSingleButton(button.getX(), button.getY()).getButton().getIcon());
            k[0]++;
        };
        for(int i = 0; i < Dashboard.getSide();i++){
            for(int j = 0; j < Dashboard.getSide();j++){
                selectionFrame.getSingleButton(i,j).getButton().addActionListener(choice);
            }
        }
        //una volta selezionate tutte le tessere, non voglio aggiornamenti dalla dashboard
        if(k[0]==nChoices[0]){
            for(int i = 0; i < Dashboard.getSide();i++){
                for(int j = 0; j < Dashboard.getSide();j++){
                    selectionFrame.getSingleButton(i,j).getButton().removeActionListener(choice);
                }
            }
        }
        k[0] = 0;
        //GESTIONE DELL'ORDINAMENTO DRAG&DROP MOUSE LISTENER

        //BUTTON SUBMIT CHE ATTIVA LA NOTIFY
    }

    private void playerInsert() {
    }

    private void endGame(Player ranking[]) {
        EndGame endGame = new EndGame();
        endGame.setTexts(ranking);
        mainFrame.add(endGame);
        mainFrame.setVisible(true);
    }


    public GraphicGameInterface(){
        this.mainFrame = new JFrame("MY SHELFIE");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(true);
    }

    @Override
    public void addviewEventListener(viewListeners listener) {

    }

    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException, NotCatchableException, NotAdjacentSlotsException, NotEnoughSpaceChoiceException {

    }

    @Override
    public void notifyOrder(OrderChoice o) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {

    }

    @Override
    public void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {

    }

    @Override
    public void notifyOneMoreTime() throws SameNicknameException, RemoteException {

    }

    @Override
    public void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {

    }

    @Override
    public void run() {

    }
}
