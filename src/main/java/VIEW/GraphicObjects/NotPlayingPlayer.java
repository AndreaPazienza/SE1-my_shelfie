package VIEW.GraphicObjects;

import MODEL.Dashboard;
import MODEL.GameView;
import MODEL.PersonalShelf;

import javax.swing.*;
import java.awt.*;

public class NotPlayingPlayer extends JFrame {
    /*

    private JLabel backgroudDashLabel, backgroundInsertLabel, backgroundPGoalLabel, backgroundCGoal1Label, backgroundCGoal2Label;
    private ImageIcon backgroundDash, backgroundInsert, backgroundPGoal, backgroundCGoal1, backgroundCGoal2;

    public NotPlayingPlayer(GameView gameView){
        super();
        TileButton[][] tiles;

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
        tiles = new TileButton[Dashboard.getSide()][Dashboard.getSide()];

        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                tiles[i][j] = new TileButton(new JButton(),i,j);
                tiles[i][j].getButton().setPreferredSize(new Dimension(50, 50));
                board.add(tiles[i][j].getButton());
            }
        }
        createDashboard(gameView,tiles);
        backgroudDashLabel.setLayout(new GridBagLayout());
        backgroudDashLabel.add(board);
        add(backgroudDashLabel, BorderLayout.WEST);

        //aggiunta della shelf alla schermata
        JPanel shelf = new JPanel(new GridLayout(PersonalShelf.N_ROWS, PersonalShelf.N_COLUMN));
        JLabel[][] shelfHole = new JLabel[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];

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
        add(backgroundInsertLabel, BorderLayout.CENTER);

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

    private void createShelf(GameView gameView, JLabel[][] shelfHole) {
        for(int i = 0; i < PersonalShelf.N_ROWS; i++){
            for(int j = 0; j < PersonalShelf.N_COLUMN; j++){
                puttingTiles(gameView, shelfHole, i, j);
            }
        }
    }

    public void createDashboard(GameView gameView, TileButton[][] buttons) {
        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                puttingTiles(gameView, buttons, i, j);
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

    public void puttingTiles(GameView gameView, TileButton[][] tiles, int i, int j) {
        if (!gameView.getTable().getSingleSlot(i, j).getColor().Equals(MODEL.Color.BLACK) &&
                !gameView.getTable().getSingleSlot(i, j).getColor().Equals(MODEL.Color.GREY)) {
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
     */
}
