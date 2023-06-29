package MODEL;

public class Player {
    public String nickname;
    private int orderInTurn;
    private int score;
    private PersonalShelf shelf;
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
    public void setOrderInTurn(int order) {
        this.orderInTurn = order;
    }

    public void setScore(int points) {
        this.score = points;
    }

    public void setPgoal(PersonalGoal pgoal) {
        this.pgoal = pgoal;
    }

    public void setShelf(PersonalShelf shelf) {
        this.shelf = shelf;
    }

    public Slot selectCard (Dashboard dashboard, int x, int y) {
        Slot selectedCard = new Slot(dashboard.getSingleSlot(x,y).getColor());
        selectedCard.setType(dashboard.getSingleSlot(x,y).getType());
        dashboard.getSingleSlot(x,y).setGrey();
        dashboard.getSingleSlot(x,y).setCatchable(false);
        return selectedCard;
    }

    public void orderCards(Slot[] selectedCards, int p, int s, int t) { //p is the position in the starting array of the first tile in the reordered array, s is the second, t the third
        Slot[] tmp = new Slot[3];
        tmp[0] = selectedCards[p-1];
        tmp[1] = selectedCards[s-1];
        tmp[2] = selectedCards[t-1];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = tmp[i];
        }
    }

    public void orderCards(Slot[] selectedCards){ //method that sorts if I have chosen only two tiles
        Slot tmp = selectedCards[1];
        selectedCards[1] = selectedCards[0];
        selectedCards[0] = tmp;
    }

    public void sumPoints(int p){
        int points = this.getScore()+p;
        this.setScore(points);
    }

    public void checkScore(){
        int pgoalPoints = pgoal.assignPoint(shelf);
        int sgoalsPoints = this.getScore(); //the sharedgoals points are already included in the score (give points method)
        int nearbyPoints = this.shelf.calculatePoints();
        int points = pgoalPoints+sgoalsPoints+nearbyPoints; //idea: calculate the 3 individual scores and add them together.
        this.setScore(points);
    }

    public PersonalGoal getPgoal() {
        return pgoal;
    }

    public Player(String nick){
        this.nickname = nick;
        this.score = 0;
        this.shelf = new PersonalShelf();
        this.pgoal = null;
        this.orderInTurn = 0;
    }

}
