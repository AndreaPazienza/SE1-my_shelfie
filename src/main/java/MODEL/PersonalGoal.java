package MODEL;

import java.io.Serializable;
import java.util.Observable;

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
    public void setPGoal3(){
        goal[0] = new Target(Color.BLUE, 1, 0);
        goal[1] = new Target(Color.YELLOW,1, 3);
        goal[2] = new Target(Color.PINK, 2,2);
        goal[3] = new Target(Color.GREEN, 3, 1);
        goal[4] = new Target(Color.LBLUE,3,4);
        goal[5] = new Target(Color.WHITE, 5, 0);
    }
    public void setPGoal4(){
        goal[0] = new Target(Color.YELLOW,0,4);
        goal[1] = new Target(Color.LBLUE, 2,0);
        goal[2] = new Target(Color.BLUE,2,2);
        goal[3] = new Target(Color.PINK,3,3);
        goal[4] = new Target(Color.WHITE,4,1);
        goal[5] = new Target(Color.GREEN,4,2);
    }
    public void setPGoal5(){
        goal[0] = new Target(Color.LBLUE,1,1);
        goal[1] = new Target(Color.BLUE,3,1);
        goal[2] = new Target(Color.WHITE,3,2);
        goal[3] = new Target(Color.PINK,4,4);
        goal[4] = new Target(Color.YELLOW,5,0);
        goal[5] = new Target(Color.GREEN, 5,3);
    }

    public void setPGoal6(){
        goal[0] = new Target(Color.LBLUE,0,2);
        goal[1] = new Target(Color.GREEN, 0, 4);
        goal[2] = new Target(Color.WHITE,2,3);
        goal[3] = new Target(Color.YELLOW, 4,1);
        goal[4] = new Target(Color.BLUE, 4,3);
        goal[5] = new Target(Color.PINK, 5,0);
    }
    public void setPGoal7(){
        goal[0] = new Target(Color.GREEN,0,0);
        goal[1] = new Target(Color.BLUE,1,3);
        goal[2] = new Target(Color.PINK, 2,1);
        goal[3] = new Target(Color.LBLUE, 3,0);
        goal[4] = new Target(Color.YELLOW,4,4);
        goal[5] = new Target(Color.WHITE, 5,2);
    }
    public void setPGoal8(){
        goal[0] = new Target(Color.BLUE, 0,4);
        goal[1] = new Target(Color.GREEN,1,1);
        goal[2] = new Target(Color.LBLUE,2,2);
        goal[3] = new Target(Color.PINK,3,0);
        goal[4] = new Target(Color.WHITE, 4,3);
        goal[5] = new Target(Color.YELLOW, 5,3);
    }
    public void setPGoal9(){
        goal[0] = new Target(Color.YELLOW,0,2);
        goal[1] = new Target(Color.GREEN,2,2);
        goal[2] = new Target(Color.WHITE,3,4);
        goal[3] = new Target(Color.LBLUE,4,1);
        goal[4] = new Target(Color.PINK,4,4);
        goal[5] = new Target(Color.BLUE,5,0);
    }
    public void setPGoal10(){
        goal[0] = new Target(Color.LBLUE,0,4);
        goal[1] = new Target(Color.YELLOW,1,1);
        goal[2] = new Target(Color.WHITE,2,0);
        goal[3] = new Target(Color.GREEN,3,3);
        goal[4] = new Target(Color.BLUE, 4,1);
        goal[5] = new Target(Color.PINK, 5,3);
    }
    public void setPGoal11(){
        goal[0] = new Target(Color.PINK,0,2);
        goal[1] = new Target(Color.WHITE,1,1);
        goal[2] = new Target(Color.YELLOW,2,0);
        goal[3] = new Target(Color.BLUE,3,2);
        goal[4] = new Target(Color.GREEN, 4,4);
        goal[5] = new Target(Color.LBLUE,5,3);
    }
    public void setPGoal12(){
        goal[0] = new Target(Color.WHITE, 0,2);
        goal[1] = new Target(Color.PINK,1,1);
        goal[2] = new Target(Color.BLUE, 2,2);
        goal[3] = new Target(Color.LBLUE,3,3);
        goal[4] = new Target(Color.YELLOW,4,4);
        goal[5] = new Target(Color.GREEN, 5,0);
    }
}
