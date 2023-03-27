public class CGSixGroupsTwoTiles extends CommonGoalAbs{

    @Override
    public void control(PersonalShelf shelf) {

        boolean[][] visited = new boolean[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
        int count=0;


        for(int i=0; i<PersonalShelf.N_ROWS; i++){
            for(int j=0; j<PersonalShelf.N_COLUMN; j++) {
                if (!visited[i][j]) {
                    Color checkingColor = shelf.getSingleSlot(i, j).getColor();
                    if (shelf.getSingleSlot(i + 1, j).getColor().equals(checkingColor) && i + 1 < PersonalShelf.N_ROWS) {
                        count++;
                        visited[i + 1][j] = true;
                    }
                    if (shelf.getSingleSlot(i, j + 1).getColor().equals(checkingColor) && j + 1 < PersonalShelf.N_COLUMN) {
                        count++;
                        visited[i][j + 1] = true;
                    }
                }
                visited[i][j]=true;
            }
        }
       if(count >= 6){
           givePoints(playerPlying);
       }
    }
}
