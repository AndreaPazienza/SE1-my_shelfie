package MODEL;

import java.io.Serializable;

public class PersonalGoal implements Serializable {
    private Target[] goal;
    private static final int N_GOALS = 6;

    public int assignPoint(PersonalShelf shelf) {
        int counter = 0;
        int points = 0;
        for (int i = 0; i < N_GOALS; i++) {            //in the for loop I make the comparisons; the counter keeps track of the number.
            int x = goal[i].getPosX();           //of past comparisons
            int y = goal[i].getPosY();
            Color col = goal[i].getTile();
            if (col.Equals(shelf.getSingleSlot(x,y).getColor())) {
                counter++;
            }
        }
        switch (counter) {           //The switch is used to assign points; being a void, it updates directly.
            case 0:                  //score player attribute
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
    public Target getSingleTarget(int i){
        return goal[i];
    }

    public Target[] getGoal() {
        return goal;
    }

    public PersonalGoal() {
        this.goal = new Target[N_GOALS];
    }

    public void addTarget(Target target) {
        boolean found = false;
        for (int i = 0; i < N_GOALS && !found; i++) {
            if (goal[i] == null) {
                goal[i] =target;
                found = true;
            }
        }
    }

    public void setPGoal1(){
        goal[0] = new Target(Color.PINK, 0, 0);
        goal[1] = new Target(Color.BLUE, 0, 2);
        goal[2] = new Target(Color.GREEN, 1,4);
        goal[3] = new Target(Color.WHITE, 2, 3);
        goal[4] = new Target(Color.YELLOW, 3,1);
        goal[5] = new Target(Color.LBLUE, 5,2);
    }
    public void setPGoal2(){
        goal[0] = new Target(Color.PINK, 1, 1);
        goal[1] = new Target(Color.GREEN, 2, 0);
        goal[2] = new Target(Color.YELLOW, 2,2);
        goal[3] = new Target(Color.WHITE,3,4);
        goal[4] = new Target(Color.LBLUE, 4,3);
        goal[5] = new Target(Color.BLUE, 5,4);
    }
}
