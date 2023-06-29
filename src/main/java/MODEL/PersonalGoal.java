package MODEL;

import java.io.Serializable;

/**
 * Clas that represents a personal goal card that a player can achieve.
 */
public class PersonalGoal implements Serializable {

    /**
     * The set of slots to fill to pursue a personal goal.
     */
    private Target[] goal;

    /**
     * The number of slots to fill to pursue a personal goal.
     */
    private static final int N_GOALS = 6;

    /**
     * Retrieves the number of points earned based on how many slots of the personal goal have been filled.
     *
     * @param shelf The shelf of the player.
     * @return The points earned.
     */
    public int assignPoint(PersonalShelf shelf) {
        int counter = 0;
        int points = 0;
        for (int i = 0; i < N_GOALS; i++) {
            int x = goal[i].getPosX();
            int y = goal[i].getPosY();
            Color col = goal[i].getTile();
            if (col.Equals(shelf.getSingleSlot(x,y).getColor())) {
                counter++;
            }
        }
        //The switch is used to assign points
        switch (counter) {
            case 0:
                return 0;
            case 1:
                points += 1;
                return points;
            case 2:
                points += 2;
                return points;
            case 3:
                points += 4;
                return points;
            case 4:
                points += 6;
                return points;
            case 5:
                points += 9;
                return points;
            case 6:
                points += 12;
                return points;
        }
        return points;
    }

    /**
     * Retrieves one of the slots to fill from the set according to the his index.
     *
     * @param i The index of the single slot.
     * @return The single slot to fill.
     */
    public Target getSingleTarget(int i){
        return goal[i];
    }

    /**
     * Retrieves the set of slots to fill to pursue a personal goal.
     *
     * @return The set of slots to fill to pursue a personal goal.
     */
    public Target[] getGoal() {
        return goal;
    }

    /**
     * Constructor for PersonalGoal class.
     */
    public PersonalGoal() {
        this.goal = new Target[N_GOALS];
    }

    /**
     * Adds a target to the set that compose the personal goal.
     *
     * @param target The target to add on the set.
     */
    public void addTarget(Target target) {
        // Cerca il primo slot libero nell'array di target
        boolean ok = false;
        for (int i = 0; i < N_GOALS && !ok; i++) {
            if (goal[i] == null) {
                goal[i] = target;
                ok = true;
            }
        }
    }

    //Pre-built instance of personal goal used to test
    public void setPGoal1(){
        goal[0] = new Target(Color.PINK, 0, 0);
        goal[1] = new Target(Color.BLUE, 0, 2);
        goal[2] = new Target(Color.GREEN, 1,4);
        goal[3] = new Target(Color.WHITE, 2, 3);
        goal[4] = new Target(Color.YELLOW, 3,1);
        goal[5] = new Target(Color.LBLUE, 5,2);
    }

    //Pre-built instance of personal goal used to test
    public void setPGoal2(){
        goal[0] = new Target(Color.PINK, 1, 1);
        goal[1] = new Target(Color.GREEN, 2, 0);
        goal[2] = new Target(Color.YELLOW, 2,2);
        goal[3] = new Target(Color.WHITE,3,4);
        goal[4] = new Target(Color.LBLUE, 4,3);
        goal[5] = new Target(Color.BLUE, 5,4);
    }
}
