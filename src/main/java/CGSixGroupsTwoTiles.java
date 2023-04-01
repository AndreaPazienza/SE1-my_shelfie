public class CGSixGroupsTwoTiles extends CommonGoalAbs{

    public CGSixGroupsTwoTiles(int players){
        super(players);
    }
    @Override
    public void control(Player player) {

        boolean[][] visited = new boolean[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
        int count=0;


        for(int i=0; i<PersonalShelf.N_ROWS; i++){
            for(int j=0; j<PersonalShelf.N_COLUMN; j++) {
                if (!visited[i][j]) {
                    Color checkingColor = player.getShelf().getSingleSlot(i, j).getColor();
                    if (player.getShelf().getSingleSlot(i + 1, j).getColor().equals(checkingColor) && i + 1 < PersonalShelf.N_ROWS) {
                        count++;
                        visited[i + 1][j] = true;
                    }
                    if (player.getShelf().getSingleSlot(i, j + 1).getColor().equals(checkingColor) && j + 1 < PersonalShelf.N_COLUMN) {
                        count++;
                        visited[i][j + 1] = true;
                    }
                }
                visited[i][j]=true;
            }
        }
       if(count >= 6){
           givePoints(player);
       }
    }
}
