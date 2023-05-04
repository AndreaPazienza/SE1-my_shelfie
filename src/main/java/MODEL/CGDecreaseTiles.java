package MODEL;
import VIEW.ColorPrint;

//Five columns of increasing or decreasing height.
// Starting from the first column on the left or on the right, each next column must be made of exactly one more tile.
// Tiles can be of any type.
public class CGDecreaseTiles extends CommonGoalAbs {

    public CGDecreaseTiles(int players){
        super(players);
    }

    public void control(Player player) {

        if (!playerAchived[playing]) {
            int i = 1, j = 0;
            Color nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
            ;
            boolean checker = true;
            if (!nextDiagonal.equals(Color.GREY)) {
                do {
                    i++;
                    j++;
                    nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
                    if (nextDiagonal.equals(Color.GREY)) {
                        checker = false;
                    }

                } while (checker && i < 5);
            } else checker = false;

            if (!checker) {
                checker = true;
                i = 5;
                j = 0;
                nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
                if (!nextDiagonal.equals(Color.GREY)) {
                    do {
                        i--;
                        j++;
                        nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
                        if (nextDiagonal.equals(Color.GREY)) {
                            checker = false;
                        }

                    } while (checker && i > 1);
                } else checker = false;
            }

            if (checker) givePoints(player);
        }
    }

    @Override
    public void show() {
        System.out.println("Cinque colonne di altezza crescente o decrescente: a partire dalla prima colonna a sinistra o a destra, ogni colonna successiva deve essere formata da una tessera in più. Le tessere possono essere di qualsiasi tipo.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        for(int i = 0; i < PersonalShelf.N_COLUMN; i++){
            example.getSingleSlot(5,i).setColor(Color.BLUE);
        }
        for(int j = 0; j < PersonalShelf.N_COLUMN-1; j++){
            example.getSingleSlot(4,j).setColor(Color.GREEN);
        }
        for(int k = 0; k < PersonalShelf.N_COLUMN-2; k++){
            example.getSingleSlot(3,k).setColor(Color.PINK);
        }
        example.getSingleSlot(2,1).setColor(Color.WHITE);
        example.getSingleSlot(2,0).setColor(Color.YELLOW);
        example.getSingleSlot(1,0).setColor(Color.LBLUE);

        System.out.print("\t");
        for (int k = 0; k < PersonalShelf.N_COLUMN; k++) {
            System.out.print("\t " + k + " \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if ((!example.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !example.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(example.getSingleSlot(i, j).getColor()) + "[ ]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "   " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=================================================================================\n");
        System.out.print("\n");
        System.out.print("\n");
    }

}
