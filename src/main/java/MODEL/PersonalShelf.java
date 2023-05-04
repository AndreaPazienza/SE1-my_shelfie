package MODEL;


import java.io.Serializable;

public class PersonalShelf implements Serializable {

    private Slot[][] shelf;
    private boolean itsFull;
    private int maxChoices;
    public static final int N_ROWS = 6;
    public static final int N_COLUMN = 5;

    public boolean isItsFull() {
        return itsFull;
    }

    public int calculatePoints(){ //Actual method of point calculation: here the method that counts the adjacencies is called and then the one that actually assigns the points.
        int tPoints = 0;
        int adiacentSlot = 0;
        boolean[][] visited = new boolean[N_ROWS][N_COLUMN]; //Boolean matrix (one-to-one correspondence with the shelf) to mark the tiles to visit.

        for(int i = 0; i < N_ROWS; i++){
            for(int j = 0; j < N_COLUMN; j++){
                if(!this.shelf[i][j].getColor().equals(Color.GREY) && !visited[i][j]){
                    adiacentSlot = checkAdjacentSlot(visited, i, j);
                    if(adiacentSlot >= 3) {
                        int points = calculatePointsForAdiacentSlot(adiacentSlot);
                        tPoints += points;
                    }
                }
            }
        }
        return tPoints;
    }

    public Slot getSingleSlot(int x, int y){
        return shelf[x][y];
    }

    public int checkAdjacentSlot(boolean[][] visited, int x, int y){ //counts the effective adjacencies
        visited[x][y] = true; //I mark the tile I have visited
        int count = 1;
        Color color = this.shelf[x][y].getColor(); //I save the color of the tile I have to check
//I check the adjacent tiles: if they have the same color and are not marked, I increment count.

        if(x > 0 && this.shelf[x-1][y] != null && color != Color.GREY && color == this.shelf[x-1][y].getColor()&&!visited[x-1][y]) { //controllo cella sopra e sotto della shelf
            count += checkAdjacentSlot(visited, x-1, y);
        }
        if(x < N_ROWS-1 && this.shelf[x+1][y] != null && color != Color.GREY && color == this.shelf[x+1][y].getColor()&&!visited[x+1][y]){
            count += checkAdjacentSlot(visited, x+1, y);
        }
        if(y > 0 && this.shelf[x][y-1] != null && color != Color.GREY && color == this.shelf[x][y-1].getColor()&&!visited[x][y-1]){ //controllo cella a sx e a dx della shelf
            count += checkAdjacentSlot(visited, x, y-1);
        }
        if(y < N_COLUMN-1 && this.shelf[x][y+1] != null && color != Color.GREY && color == this.shelf[x][y+1].getColor()&&!visited[x][y+1]){
            count += checkAdjacentSlot(visited, x, y+1);
        }

        return count;
    }

    private int calculatePointsForAdiacentSlot(int adiacentSlot ){
        switch(adiacentSlot){
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 5;
            default:
                return 8;
        }
    }

    public void insert(Slot[] slots, int column){      //Here an array of already sorted slots (OrderSlot) is given as input and the player is asked for the column where to insert the slots.
        int realLenght = 0;
        for(int i = 0;i < slots.length;i++){
            if(!(slots[i].getColor().Equals(Color.GREY))){
                realLenght++;       //realLenght serves because an array of 3 slots will be given as input
            }                            //will not be always full;
        }
        int i = N_ROWS-1;
        while (!(shelf[i][column].getColor().Equals(Color.GREY))) {   //find the first empty position in the column chosen by the user
                i--;
                }
        for (int j = 0; j < realLenght; j++) {    //insert
            this.shelf[i][column].setColor(slots[j].getColor());
            this.shelf[i][column].setType(slots[j].getType());
            i--;
        }
        /*notify*/
       // notifyObservers(this.shelf);
    }

    public void checkLastLine(){
        for(int i = 0; i < N_COLUMN; i++){
            if(this.shelf[0][i].getColor().equals(Color.GREY)){
                this.itsFull = false;
                return;
            }
        }
        this.itsFull = true;
    }
    public PersonalShelf(){
        this.shelf = new Slot[N_ROWS][N_COLUMN];
        for(int i = 0; i < N_ROWS; i++){
            for(int j = 0; j < N_COLUMN; j++){
             this.shelf[i][j]  = new Slot(Color.GREY);
            }
        }
        this.itsFull = false;
    }

    public void setMaxChoices(){
        int max = 0;
        for(int i = 0; i < N_COLUMN; i++) {
            int count = 0;
            boolean exit = false;

            for(int j = 0;j < N_ROWS && exit==false; j++){
                if (this.shelf[j][i].getColor().equals(Color.GREY)){
                    count++;
                }
                else{
                    exit = true;
                }
            }
            if (count>maxChoices){
                this.maxChoices=count;
            }
        }
    }

    public int getMaxChoices(){
        return this.maxChoices;
    }
}
