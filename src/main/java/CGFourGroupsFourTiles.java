public class CGFourGroupsFourTiles extends CommonGoalAbs {
    public CGFourGroupsFourTiles(int players){
        super(players);
    }
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
        if(x > 0 && shelf.getSingleSlot(x-1, y) != null && color == shelf.getSingleSlot(x-1,y).getColor()&&!visited[x-1][y]) { //controllo cella sopra e sotto della shelf
            count += checkAdjacentSlot(shelf, visited, x-1, y);
        }
        if(x < PersonalShelf.N_ROWS-1 && shelf.getSingleSlot(x+1, y) != null && color == shelf.getSingleSlot(x+1, y).getColor()&&!visited[x+1][y]){
            count += checkAdjacentSlot(shelf, visited, x+1, y);
        }
        if(y > 0 && shelf.getSingleSlot(x, y-1) != null && color == shelf.getSingleSlot(x, y-1).getColor()&&!visited[x][y-1]){ //controllo cella a sx e a dx della shelf
            count += checkAdjacentSlot(shelf, visited, x, y-1);
        }
        if(y < PersonalShelf.N_COLUMN && shelf.getSingleSlot(x, y+1) != null && color == shelf.getSingleSlot(x, y+1).getColor()&&!visited[x][y+1]){
            count += checkAdjacentSlot(shelf, visited, x, y+1);
        }
        return count;
    }
}
