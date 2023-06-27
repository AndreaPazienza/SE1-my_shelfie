package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

import javax.swing.*;
//Four groups each containing at least
//4 tiles of the same type (not necessarily in the depicted shape).
//The tiles of one group can be different from those of another group.




public class CGFourGroupsFourTiles extends CommonGoalAbs {
    public CGFourGroupsFourTiles(int players){
        super(players);
        //ImageIcon image = new ImageIcon("src/main/GraphicResources/common goal cards/3.jpg");
        //super.setImage(image);
    }

    /**
     * {@inheritDoc}
     *
     * @param player The player whose shelf has to be checked.
     */
    @Override
    public void control(Player player) {
        if(!commonGoalAchived()) {
            boolean[][] visited = new boolean[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
            int countAdjacent = 0, actualAdjacent = 0;

            for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
                for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                    countAdjacent = checkAdjacentSlot(player.getShelf(), visited, i, j);
                    if (countAdjacent >= 4) {
                        actualAdjacent += countAdjacent / 4;
                    }

                }
            }
            if (actualAdjacent >= 4) {
                givePoints(player);
            }
        }
    }

    private int checkAdjacentSlot(PersonalShelf shelf, boolean[][] visited, int x, int y){

        visited[x][y] = true; //marko la tessera che ho visitato
        int count = 1;

        Color color = shelf.getSingleSlot(x,y).getColor(); //mi salvo il colore della tessera che devo controllare
        //controllo le tessere adiacenti: se hanno lo stesso colore e non sono markate, incremento count
        if(x > 0 && shelf.getSingleSlot(x-1,y) != null && !shelf.getSingleSlot(x-1, y).getColor().Equals(Color.GREY) && color == shelf.getSingleSlot(x-1,y).getColor()&&!visited[x-1][y]) { //controllo cella sopra e sotto della shelf
            count += checkAdjacentSlot(shelf, visited, x-1, y);
        }
        if(x < PersonalShelf.N_ROWS-1 && shelf.getSingleSlot(x+1,y) != null && !shelf.getSingleSlot(x+1, y).getColor().Equals(Color.GREY) && color == shelf.getSingleSlot(x+1, y).getColor()&&!visited[x+1][y]){
            count += checkAdjacentSlot(shelf, visited, x+1, y);
        }
        if(y > 0 && shelf.getSingleSlot(x,y-1) != null && !shelf.getSingleSlot(x, y-1).getColor().Equals(Color.GREY) && color == shelf.getSingleSlot(x, y-1).getColor()&&!visited[x][y-1]){ //controllo cella a sx e a dx della shelf
            count += checkAdjacentSlot(shelf, visited, x, y-1);
        }
        if(y < PersonalShelf.N_COLUMN-1 && shelf.getSingleSlot(x,y+1) != null && !shelf.getSingleSlot(x, y+1).getColor().Equals(Color.GREY) && color == shelf.getSingleSlot(x, y+1).getColor()&&!visited[x][y+1]){
            count += checkAdjacentSlot(shelf, visited, x, y+1);
        }
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo (non necessariamente come mostrato in figura). Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.YELLOW);
        example.getSingleSlot(0,1).setColor(Color.YELLOW);
        example.getSingleSlot(0,2).setColor(Color.YELLOW);
        example.getSingleSlot(0,3).setColor(Color.YELLOW);
        example.getSingleSlot(5,0).setColor(Color.WHITE);
        example.getSingleSlot(5,1).setColor(Color.WHITE);
        example.getSingleSlot(4,0).setColor(Color.WHITE);
        example.getSingleSlot(4,1).setColor(Color.WHITE);
        example.getSingleSlot(3,2).setColor(Color.GREEN);
        example.getSingleSlot(3,3).setColor(Color.GREEN);
        example.getSingleSlot(3,4).setColor(Color.GREEN);
        example.getSingleSlot(4,2).setColor(Color.GREEN);
        example.getSingleSlot(2,1).setColor(Color.PINK);
        example.getSingleSlot(2,2).setColor(Color.PINK);
        example.getSingleSlot(2,3).setColor(Color.PINK);
        example.getSingleSlot(2,0).setColor(Color.PINK);

        System.out.print("\t");
        for (int k = 0; k < PersonalShelf.N_COLUMN; k++) {
            System.out.print("\t    " + k + "    \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if ((!example.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !example.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(example.getSingleSlot(i, j).getColor()) + "[" + Image.colorToImage(example.getSingleSlot(i, j).getColor()) +"]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "         " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=======================================================================================================================================================\n");
        System.out.print("\n");
        System.out.print("\n");
    }

    @Override
    public String description() {
        return "Ottieni quattro gruppi sepratati composti da 4 tessere dello stesso tipo ";
    }
}
