package VIEW.GraphicObjects;

import MODEL.Color;
import MODEL.Dashboard;
import MODEL.GameView;
import MODEL.PersonalShelf;

import javax.swing.*;

public class TurnFrame extends JFrame{

    /*public TurnFrame(){
        super("STAI GIOCANDO! FAI LE TUE MOSSE!");
    }


    public void createDashoard(GameView gameView, TileButton[][] buttons) {
        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                puttingTiles(gameView, buttons, i, j);
            }
        }
    }

    private void puttingTiles(GameView gameView, TileButton[][] tiles, int i, int j) {
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
     */
}
