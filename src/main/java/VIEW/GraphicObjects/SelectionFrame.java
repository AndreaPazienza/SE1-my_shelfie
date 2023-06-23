package VIEW.GraphicObjects;

import MODEL.Color;
import MODEL.Dashboard;
import MODEL.GameView;

import javax.swing.*;
import java.awt.*;

public class SelectionFrame extends TurnFrame{
    /*

    private TileButton[][] tiles;
    private JLabel backgroudDashLabel, backgroundInsertLabel, backgroundPGoalLabel, backgroundCGoal1Label, backgroundCGoal2Label;
    private ImageIcon backgroundDash, backgroundInsert, backgroundPGoal, backgroundCGoal1, backgroundCGoal2;;

    public SelectionFrame(GameView gameView) {
        super();

        setLayout(new BorderLayout());

        this.backgroundDash = new ImageIcon("src/main/GraphicResources/boards/livingroom.png");
        this.backgroudDashLabel = new JLabel(backgroundDash);

        JPanel board = new JPanel(new GridLayout(Dashboard.getSide(), Dashboard.getSide()));
        this.tiles = new TileButton[Dashboard.getSide()][Dashboard.getSide()];

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
        add(backgroudDashLabel, BorderLayout.CENTER);

        //AGGIUNTA PERSONAL E COMMON GOALS, AGGIUNTA SHELF, AGGIUNTA LOG CLIENT
    }

    public TileButton[][] getTiles() {
        return tiles;
    }

    public TileButton getSingleButton(int x, int y){
        return tiles[x][y];
    }
     */
}