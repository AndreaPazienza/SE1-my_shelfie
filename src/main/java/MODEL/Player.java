package MODEL;

/**
 * Class that represents the player enrolled to the game.
 */
public class Player {

    /**
     * The nickname chosen by the player.
     */
    public String nickname;

    /**
     * The player's turn order relative to the others.
     */
    private int orderInTurn;

    /**
     * The amount of points currently earned by the player.
     */
    private int score;

    /**
     * The personal shelf associated to the player.
     */
    private PersonalShelf shelf;

    /**
     * The personal goal associated to the player.
     */
    private PersonalGoal pgoal;

    /**
     * Retrieves the player's turn order relative to the others.
     *
     * @return The player's turn order relative to the others.
     */
    public int getOrderInTurn() {
        return orderInTurn;
    }

    /**
     * Retrieves the amount of points currently earned by the player.
     *
     * @return The amount of points currently earned by the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the nickname chosen by the player.
     *
     * @return The nickname chosen by the player.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Retrieves the personal shelf associated to the player.
     *
     * @return The personal shelf associated to the player.
     */
    public PersonalShelf getShelf() {
        return shelf;
    }

    /**
     * Sets the player's turn order relative to the others.
     *
     * @param order The player's turn order relative to the others.
     */
    public void setOrderInTurn(int order) {
        this.orderInTurn = order;
    }

    /**
     * Sets the amount of points currently earned by the player.
     *
     * @param points The amount of points currently earned by the player.
     */
    public void setScore(int points) {
        this.score = points;
    }

    /**
     * Sets the personal goal associated to the player.
     *
     * @param pgoal The personal goal associated to the player.
     */
    public void setPgoal(PersonalGoal pgoal) {
        this.pgoal = pgoal;
    }

    /**
     * Sets the personal shelf associated to the player.
     *
     * @param shelf The personal shelf associated to the player.
     */
    public void setShelf(PersonalShelf shelf) {
        this.shelf = shelf;
    }

    /**
     * Retrieves the slot that corresponds with the input coordinates from the dashboard.
     *
     * @param dashboard The dashboard from which to make the selection.
     * @param x The index of the dashboard's slot's row to select.
     * @param y The index of the dashboard's slot's column to select.
     * @return The selected slot.
     */
    public Slot selectCard (Dashboard dashboard, int x, int y) {
        Slot selectedCard = new Slot(dashboard.getSingleSlot(x,y).getColor());
        selectedCard.setType(dashboard.getSingleSlot(x,y).getType());
        dashboard.getSingleSlot(x,y).setGrey();
        dashboard.getSingleSlot(x,y).setCatchable(false);
        return selectedCard;
    }

    /**
     * Reorders the three cards selected in case of three slots selection according
     *
     * @param selectedCards The three slots selected by the player.
     * @param p The position in the starting array of the first tile in the reordered array.
     * @param s The position in the starting array of the second tile in the reordered array.
     * @param t The position in the starting array of the third tile in the reordered array.
     */
    public void orderCards(Slot[] selectedCards, int p, int s, int t) { //p is the position in the starting array of the first tile in the reordered array, s is the second, t the third
        Slot[] tmp = new Slot[3];
        tmp[0] = selectedCards[p-1];
        tmp[1] = selectedCards[s-1];
        tmp[2] = selectedCards[t-1];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = tmp[i];
        }
    }

    /**
     * Switches the two cards selected in case of two slots selection.
     *
     * @param selectedCards The two slots selected by the player.
     */
    public void orderCards(Slot[] selectedCards){ //method that sorts if I have chosen only two tiles
        Slot tmp = selectedCards[1];
        selectedCards[1] = selectedCards[0];
        selectedCards[0] = tmp;
    }

    /**
     * Sums a number of points to the current player score.
     *
     * @param p The number of points to sum.
     */
    public void sumPoints(int p){
        int points = this.getScore()+p;
        this.setScore(points);
    }

    /**
     * Calculate the points earned by the player.
     */
    public void checkScore(){
        int pgoalPoints = pgoal.assignPoint(shelf);
        int sgoalsPoints = this.getScore();
        int nearbyPoints = this.shelf.calculatePoints();
        int points = pgoalPoints+sgoalsPoints+nearbyPoints;
        this.setScore(points);
    }

    /**
     * Retrieves the personal goal assigned to the player.
     *
     * @return The personal goal assigned to the player.
     */
    public PersonalGoal getPgoal() {
        return pgoal;
    }

    /**
     * Constructor for Player class.
     * @param nick The nickname chosen by the player.
     */
    public Player(String nick){
        this.nickname = nick;
        this.score = 0;
        this.shelf = new PersonalShelf();
        this.pgoal = null;
        this.orderInTurn = 0;
    }

}
