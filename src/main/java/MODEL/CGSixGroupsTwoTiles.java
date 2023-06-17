package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

import javax.swing.*;
//Six groups each containing at least
//2 tiles of the same type (not necessarily in the depicted shape).
//The tiles of one group can be different from those of another group.



public class CGSixGroupsTwoTiles extends CommonGoalAbs{

    public CGSixGroupsTwoTiles(int players){
        super(players);
        //ImageIcon image = new ImageIcon("src/main/GraphicResources/common goal cards/4.jpg");
        //super.setImage(image);
    }
    @Override
    public void control(Player player) {

        boolean[][] visited = new boolean[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
        int count=0;


        for(int i=0; i<PersonalShelf.N_ROWS; i++){
            for(int j=0; j<PersonalShelf.N_COLUMN; j++) {
                if (!visited[i][j]) {
                    Color checkingColor = player.getShelf().getSingleSlot(i, j).getColor();
                    if (!checkingColor.Equals(Color.GREY)) {
                        if (i + 1 < PersonalShelf.N_ROWS && player.getShelf().getSingleSlot(i + 1, j).getColor().equals(checkingColor)) {
                            count++;
                            visited[i + 1][j] = true;
                        }
                        if (j + 1 < PersonalShelf.N_COLUMN && player.getShelf().getSingleSlot(i, j + 1).getColor().equals(checkingColor)) {
                            count++;
                            visited[i][j + 1] = true;
                        }
                    }
                }
                visited[i][j]=true;
            }
        }
       if(count >= 6){
           givePoints(player);
       }
    }

    @Override
    public void show() {
        System.out.println("Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo (non necessariamente come mostrato in figura). Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.YELLOW);
        example.getSingleSlot(0,1).setColor(Color.YELLOW);
        example.getSingleSlot(1,0).setColor(Color.LBLUE);
        example.getSingleSlot(1,1).setColor(Color.LBLUE);
        example.getSingleSlot(2,0).setColor(Color.PINK);
        example.getSingleSlot(2,1).setColor(Color.PINK);
        example.getSingleSlot(3,0).setColor(Color.GREEN);
        example.getSingleSlot(3,1).setColor(Color.GREEN);
        example.getSingleSlot(4,0).setColor(Color.BLUE);
        example.getSingleSlot(4,1).setColor(Color.BLUE);
        example.getSingleSlot(5,0).setColor(Color.WHITE);
        example.getSingleSlot(5,1).setColor(Color.WHITE);

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
}
