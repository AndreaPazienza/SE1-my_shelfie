import java.util.Scanner;

public class Player {
    public String nickname;
    private int orderInTurn;
    private int score;
    private PersonalShelf shelf;
    private boolean chair;

    private PersonalGoal pgoal;

    public int getOrderInTurn() {
        return orderInTurn;
    }

    public int getScore() {
        return score;
    }

    public String getNickname() {
        return nickname;
    }

    public PersonalShelf getShelf() {
        return shelf;
    }

    public boolean isChair() {
        return chair;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

    public void setChair() {
        if(this.orderInTurn == 1) {     //per settare la seggiola, controllo order in turn:
            this.chair = true;          // se il player gioca per primo, setto a true.
        }
        else this.chair = false;
    }

    public void setOrderInTurn(int order) {
        this.orderInTurn = order;
    }

    public void setScore(int points) {
        this.score = points;
    }

    public void setShelf(PersonalShelf shelf) {
        this.shelf = shelf;
    }

    public slot[] selectCard (Dashboard dashboard) {
        slot[] selectedCards = new slot[3];
        int i = 0;
        int x = 0;
        int y = 0;
        String yes = "si";
        String no = "no";
        do {  //il dw più esterno serve a ripetere il tutto per ogni scelta
            do {  //questi do while servono a fare controllo delle cordinate in input
                System.out.println("Scegli la tessera da prendere (inserire le coordinate): x = ");
                Scanner scanner = new Scanner(System.in);
                x = scanner.nextInt();
                if (x > 6 || x < 0) {
                    System.out.println("La posizione scelta non esiste!");
                }
            } while (x > 6 || x < 0);
            do {
                System.out.println("y = ");
                Scanner scanner = new Scanner(System.in);
                y = scanner.nextInt();
                if (y < 0 || y > 5) {
                    System.out.println("La posizione scelta non esiste!");
                }
            } while (y < 0 || y > 5);
            if (dashboard[x][y].catchable) {  //selezione vera e propria
                selectedCards[i] = dashboard[x][y];
                System.out.println("Vuoi scegliere altre tessere? si o no?");
                Scanner scanner = new Scanner(System.in);
                String stringa = scanner.next();
                if(stringa.equals(yes)){
                    i++;                //se vuole ancora scegliere, incremento solo i
                } else if (stringa.equals(no)) {
                    i = 3;              //se non vuole più scegliere, assegno i ad un valore maggiore del flag 2
                } else {
                    System.out.println("Non ho capito il comando!");
                }
            } else {
                System.out.println("La tessera selezionata non è prendibile! ");
            }
        } while(i <= 2);
        return selectedCards;
    }

    public void orderCards(Slot[] selectedCards) {
        int i = 0;
        int j = 0;
        int realLength = 0;
        for(int k = 0;k < (selectedCards.length-1);k++){
            if(selectedCards[i].getColor().notEquals(GREY)){
                realLength++;        //lunghezzaReale serve perchè in ingressi sarà dato un array di 3 slot
            }                            //che non sempre sarà pieno;
        }
        Slot[] tmp = new Slot[3];       //variabile temporanea che serve per il riordino
        do{
            do{                     //questo do while mi serve a controllare l'input dell'utente: scova eventuali errori
                System.out.println("Quale tessera vuoi inserire?");
                Scanner scanner = new Scanner(System.in);
                i = scanner.nextInt();
                if(i < 1 || i > realLength){
                    System.out.println("La tessera scelta non esiste!");
                }
                if(selectedCards[i-1] == null){
                    System.out.println("La tessera scelta è già stata selezionata!");
                }
            } while(i < 1 || i > realLength || selectedCards[i-1] == null);
            tmp[j] = selectedCards[i-1];
            selectedCards[i-1] = null;
            j++;
        } while(realLength < j); //in questo do while più esterno creo un array temporaneo con le tessere ordinate come vuole il giocatore
        for(i = 0; i < realLength; i++){
            selectedCards[i] = tmp[i]; //copio tmp dentro selcards: ho l'array ordinato
        }
    }

    public int checkScore(){
        int points = 0;
        int pgoalPoints = pgoal.AssignPoint(shelf);
        int sgoal1Points =;
        int sgoal2Points =;
        int nearbyPoints = 0;

        points = pgoalPoints+sgoal1Points+sgoal2Points+nearbyPoints; //idea: calcolo i 3 singoli punteggi e li sommo insieme
        return points;
    }
}
