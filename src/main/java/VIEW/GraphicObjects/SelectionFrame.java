package VIEW.GraphicObjects;

import MODEL.Color;
import MODEL.Dashboard;
import MODEL.GameView;

import javax.swing.*;
import java.awt.*;

public class SelectionFrame extends JFrame {

    private TileButton[][] tiles;
    private JLabel backgroudDashLabel;
    private ImageIcon backgroundDash;

    public SelectionFrame(GameView gameView) {
        super("STAI GIOCANDO! FAI LE TUE MOSSE!");

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
        createShelf(gameView,tiles);
        backgroudDashLabel.setLayout(new GridBagLayout());
        backgroudDashLabel.add(board);
        add(backgroudDashLabel, BorderLayout.CENTER);

        //AGGIUNTA PERSONAL E COMMON GOALS, AGGIUNTA SHELF, AGGIUNTA LOG CLIENT
    }

    public void createShelf(GameView gameView, TileButton[][] buttons) {
        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                puttingTiles(gameView, buttons, i, j);
            }
        }
    }

    public void puttingTiles(GameView gameView, TileButton[][] tiles, int i, int j) {
        if (!gameView.getTable().getSingleSlot(i, j).getColor().Equals(Color.BLACK) &&
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
    public TileButton[][] getTiles() {
        return tiles;
    }

    public TileButton getSingleButton(int x, int y){
        return tiles[x][y];
    }
}