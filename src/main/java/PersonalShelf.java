import java.util.Scanner;

public class PersonalShelf {
    private Slot[][] shelf;
    private boolean itsFull;
    public static final int N_ROWS = 6;
    public static final int N_COLUMN = 5;

    public static int getnColumn() {
        return N_COLUMN;
    }

    public static int getnRows(){
        return N_ROWS;
    }

    public boolean isItsFull() {
        return itsFull;
    }

    public int calculatePoints(){
        int tPoints = 0;
        int adiacentSlot = 0;
        boolean[][] visited = new boolean[N_ROWS][N_COLUMN]; //matrice di boolean (corrispondenza biunivoca con la shelf) per markare le tessere da visitare
        for(int i = 0; i < N_ROWS; i++){
            for(int j = 0; i < N_COLUMN; i++){
                if(this.shelf[i][j] != null && !visited[i][j]){
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

    public int checkAdjacentSlot(boolean[][] visited, int x, int y){
        visited[x][y] = true; //marko la tessera che ho visitato
        int count = 1;
        Color color = this.shelf[x][y].getColor(); //mi salvo il colore della tessera che devo controllare
        //controllo le tessere adiacenti: se hanno lo stesso colore e non sono markate, incremento count
        if(x > 0 && this.shelf[x-1][y] != null && color == this.shelf[x-1][y].getColor()&&!visited[x-1][y]) { //controllo cella sopra e sotto della shelf
            count += checkAdjacentSlot(visited, x-1, y);
        }
        if(x < N_ROWS-1 && this.shelf[x+1][y] != null && color == this.shelf[x+1][y].getColor()&&!visited[x+1][y]){
            count += checkAdjacentSlot(visited, x+1, y);
        }
        if(y > 0 && this.shelf[x][y-1] != null && color == this.shelf[x][y-1].getColor()&&!visited[x][y-1]){ //controllo cella a sx e a dx della shelf
            count += checkAdjacentSlot(visited, x, y-1);
        }
        if(y < N_COLUMN && this.shelf[x][y+1] != null && color == this.shelf[x][y+1].getColor()&&!visited[x][y+1]){
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

    private void insert(Slot[] slots, int column){      //qui viene datto un arra di slot già ordinato (OrderSlot) in ingresso quindi viene chiesto al giocatore la colonna dove inserire le slot
        int lunghezzaReale = 0;
        for(int i = 0;i < (slots.length-1);i++){
            if(slots[i].getColor().notEquals(Color.GREY)){
                lunghezzaReale++;        //lunghezzaReale serve perchè in ingressi sarà dato un array di 3 slot
            }                            //che non sempre sarà pieno;
        }
        int i = N_ROWS-1;
        while (shelf[i][column].getColor().notEquals(Color.GREY)) {   //trovo la prima posizione vuota della colonna scelta dall'utente
                i--;
                }
        for (int j = 0; j < lunghezzaReale; j++) {    //inserimento effettivo
            this.shelf[i][column] = slots[j];
            i--;
        }
        return;
    }

    public void checkLastLine(){
        for(int i = 0; i < N_COLUMN; i++){
            if(this.shelf[0][i].getColor().equals(Color.GREY)){ //come sono colorate le slot "vuote" della shelf?
                this.itsFull = false;
                return;
            }
        }
        this.itsFull = true;
        return;
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
}
