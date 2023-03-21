import java.util.Scanner;

public class PersonalShelf {
    private slot[][] shelf;
    private boolean itsFull;

    public boolean isItsFull() {
        return itsFull;
    }

    private void insert(slot[] slots){      //qui viene datto un arra di slot già ordinato (OrderSlot) in ingresso quindi viene chiesto al giocatore la colonna dove inserire le slot
        boolean flag = false;
        int colonna = 0;
        int lunghezzaReale = 0;
        for(int i = 0;i < (slots.length-1);i++){
            if(slots[i].getColor().notEquals(GREY)){
                lunghezzaReale++;        //lunghezzaReale serve perchè in ingressi sarà dato un array di 3 slot
            }                            //che non sempre sarà pieno;
        }
        do {   //il do while esterno serve per verificare se la colonna scelta dal giocatore ha abbastanza spazi
            do {
                System.out.println("Seleziona la colonna in cui inserire: ");//questo do while serve se il giocatore inserisce un input che non esiste
                Scanner scanner = new Scanner(System.in);
                colonna = scanner.nextInt();
                if(colonna > 5 || colonna < 0 ){
                    System.out.println("La colonna che hai scelto non esiste!");
                }
            } while(colonna > 5);
            int i = 6;
            while (shelf[i][colonna].getColor().notEquals(GREY)) {   //trovo la prima posizione vuota della colonna scelta dall'utente
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
        } while (flag == false);
        return;
    }

    public void checkLastLine(){
        for(int i = 0; i < 5; i++){
            if(this.shelf[0][i].getColor().equals(GREY)){ //come sono colorate le slot "vuote" della shelf?
                this.itsFull = false;
                return;
            }
        }
        this.itsFull = true;
        return;
    }
}
