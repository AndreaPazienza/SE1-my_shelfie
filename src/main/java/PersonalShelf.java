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

    private void insert(Slot[] slots){      //qui viene datto un arra di slot già ordinato (OrderSlot) in ingresso quindi viene chiesto al giocatore la colonna dove inserire le slot
        boolean flag = false;
        int colonna = 0;
        int lunghezzaReale = 0;
        for(int i = 0;i < (slots.length-1);i++){
            if(slots[i].getColor().notEquals(Color.GREY)){
                lunghezzaReale++;        //lunghezzaReale serve perchè in ingressi sarà dato un array di 3 slot
            }                            //che non sempre sarà pieno;
        }
        do {   //il do while esterno serve per verificare se la colonna scelta dal giocatore ha abbastanza spazi
            do {
                System.out.println("Seleziona la colonna in cui inserire: ");//questo do while serve se il giocatore inserisce un input che non esiste
                Scanner scanner = new Scanner(System.in);
                colonna = scanner.nextInt();
                if(colonna > N_COLUMN || colonna < 0 ){
                    System.out.println("La colonna che hai scelto non esiste!");
                }
            } while(colonna > N_COLUMN);
            int i = 6;
            while (shelf[i][colonna].getColor().notEquals(Color.GREY)) {   //trovo la prima posizione vuota della colonna scelta dall'utente
                i--;
            }
            if ((i+1) >= lunghezzaReale) {
                for (int j = 0; j < lunghezzaReale; j++) {    //inserimento effettivo
                    this.shelf[i][colonna] = slots[j];
                    i--;
                }
                flag = true;
            }
            else {
                System.out.println("La colonna inserita non riesce a contenerle tutte");
            }
        } while (!flag);
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
        Slot[][] shelf = new Slot[N_ROWS][N_COLUMN];
        for(int i = 0; i < N_ROWS; i++){
            for(int j = 0; j < N_COLUMN; j++){
              shelf[i][j]  = new Slot(Color.GREY);
            }
        }
    }
}
