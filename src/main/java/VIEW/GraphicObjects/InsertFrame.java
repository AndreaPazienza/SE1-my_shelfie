package VIEW.GraphicObjects;

import MODEL.Dashboard;
import MODEL.GameView;
import MODEL.PersonalShelf;

import javax.swing.*;
import java.awt.*;

public class InsertFrame extends TurnFrame {

    private JLabel backgroudDashLabel, backgroundInsertLabel, backgroundPGoalLabel, backgroundCGoal1Label, backgroundCGoal2Label;

    private ImageIcon backgroundDash, backgroundInsert, backgroundPGoal, backgroundCGoal1, backgroundCGoal2;

    private JPanel shelfWithB;

    public InsertFrame(GameView gameView){
        super();

        setLayout(new BorderLayout());
        //creazione sfondo
        this.backgroundDash = new ImageIcon("src/main/GraphicResources/boards/livingroom.png");
        this.backgroudDashLabel = new JLabel(backgroundDash);
        this.backgroundInsert = new ImageIcon("src/main/GraphicResources/boards/bookshelf.png");
        this.backgroundInsertLabel = new JLabel(backgroundInsert);
        this.backgroundPGoal = new ImageIcon();
        this.backgroundPGoalLabel = new JLabel(backgroundPGoal);

        this.backgroundCGoal1Label = new JLabel(backgroundCGoal1);

        this.backgroundCGoal2Label = new JLabel(backgroundCGoal2);

        //aggiunta della dashboard alla schermata
        JPanel board = new JPanel(new GridLayout(Dashboard.getSide(), Dashboard.getSide()));
        TileButton[][] tiles = new TileButton[Dashboard.getSide()][Dashboard.getSide()];

        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                tiles[i][j] = new TileButton(new JButton(),i,j);
                tiles[i][j].getButton().setPreferredSize(new Dimension(50, 50));
                board.add(tiles[i][j].getButton());
            }
        }
        super.createDashoard(gameView,tiles);
        backgroudDashLabel.setLayout(new GridBagLayout());
        backgroudDashLabel.add(board);
        add(backgroudDashLabel, BorderLayout.WEST);

        //aggiunta della shelf alla schermata
        JPanel shelf = new JPanel(new GridLayout(PersonalShelf.N_ROWS, PersonalShelf.N_COLUMN));
        JLabel[][] shelfHole = new JLabel[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
        JPanel columnButtons = new JPanel(new FlowLayout());
        this.shelfWithB = new JPanel();
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
        super.createShelf(gameView, shelfHole);
        backgroundInsertLabel.setLayout(new GridBagLayout());
        backgroundInsertLabel.add(shelf);
        shelfWithB.add(backgroundInsertLabel);
        add(shelfWithB, BorderLayout.CENTER);

        //aggiunta panel contenente gli obiettivi sotto
        JPanel goals = new JPanel(new FlowLayout());
        goals.add(backgroundCGoal1Label);
        goals.add(backgroundPGoalLabel);
        goals.add(backgroundCGoal2Label);
        add(goals, BorderLayout.SOUTH);

        //creazione log in cui arrivano i messaggi
        JTextPane log = new JTextPane();
        add(log, BorderLayout.EAST);
        //immagine generica sopra
        JLabel gameImage = new JLabel(new ImageIcon("src/main/GraphicResources/Publisher material/Title 2000x618px.png"));
        add(gameImage, BorderLayout.NORTH);
    }

    public JLabel getBackgroundInsertLabel() {
        return backgroundInsertLabel;
    }

    public JPanel getShelfWithB(){
        return shelfWithB;
    }

}
