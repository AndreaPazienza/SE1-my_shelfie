

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

    public Slot selectCard (Dashboard dashboard, int x, int y) {
        Slot selectedCard = new Slot(dashboard.getSingleSlot(x,y).getColor());
        Slot slot = new Slot(Color.GREY);
        if (!dashboard.getSingleSlot(x,y).isCatchable()) {  //selezione vera e propria
           return slot;
            } else{
                dashboard.getSingleSlot(x,y).setGrey();
            }
        return selectedCard;
    }

    public void orderCards(Slot[] selectedCards, int p, int s, int t) { //p è la posizione nell'array di partenza della prima tessera dell'array riordinato
                                                                        //s è la seconda, t la terza
        Slot[] tmp = new Slot[3];
        tmp[0] = selectedCards[p-1];
        tmp[1] = selectedCards[s-1];
        tmp[2] = selectedCards[t-1];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = tmp[i];
        }
    }

    public void orderCards(Slot[] selectedCards){ //metodo che riordina nel caso in cui ho scelto solo due tessere
        Slot tmp = selectedCards[1];
        selectedCards[1] = selectedCards[0];
        selectedCards[0] = tmp;
    }

    public void sumPoints(int p){
        int points = this.getScore()+p;
        this.setScore(points);
    }

    public int checkScore(){
        int pgoalPoints = pgoal.assignPoint(shelf);
        int sgoalsPoints = this.getScore(); //i punti degli sharedgoals sono già dentro score (metodo give points)
        int nearbyPoints = this.shelf.calculatePoints();
        int points = pgoalPoints+sgoalsPoints+nearbyPoints; //idea: calcolo i 3 singoli punteggi e li sommo insieme
        return points;
    }

    public Player(String nick){
        this.nickname = nick;
        this.score = 0;
        this.shelf = new PersonalShelf();
        this.pgoal = PersonalGoalDeck.extractionPGoal();
        this.orderInTurn = 0;
        this.chair = false;
    }
}
