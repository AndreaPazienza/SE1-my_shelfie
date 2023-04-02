import java.util.Random;

public class PersonalGoal {
    private Target[] goal;
    private static final int N_GOALS = 6;

    public int assignPoint(PersonalShelf shelf) { //dubbio: devo passare shelf??
        int counter = 0;
        int points = 0;
        for (int i = 0; i < N_GOALS; i++) {            //nel for faccio i confronti; counter mi tiene traccia del numero
            int x = goal[i].getPosX();           //di confronti passati
            int y = goal[i].getPosY();
            Color col = goal[i].getTile();
            if (col.Equals(shelf.getSingleSlot(x,y).getColor())) {
                counter++;
            }
        }
        switch (counter) {           //lo switch mi serve ad assegnare i punteggi; essendo una void, aggiorno diretto
            case 0:                  //l'attributo Score del player;
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

    public PersonalGoal() {
        this.goal = new Target[N_GOALS];
        /*Random random = new Random();
        for(int i = 0; i < N_GOALS; i++){
            int x = random.nextInt(6);      //genero randomicamente ogni coordinata e colore dell'obbiettivo
            int y = random.nextInt(5);
            Color c = Color.randomColor();
            this.goal[i] = new Target(c, x, y);
        }*/
    }

    public void setPGoal1(){
        goal[0] = new Target(Color.PINK, 0, 0);
        goal[1] = new Target(Color.BLUE, 0, 2);
        goal[2] = new Target(Color.GREEN, 1,4);
        goal[3] = new Target(Color.WHITE, 2, 3);
        goal[4] = new Target(Color.YELLOW, 3,1);
        goal[5] = new Target(Color.LIGHTBLUE, 5,2);
    }
    public void setPGoal2(){
        goal[0] = new Target(Color.PINK, 1, 1);
        goal[1] = new Target(Color.GREEN, 2, 0);
        goal[2] = new Target(Color.YELLOW, 2,2);
        goal[3] = new Target(Color.WHITE,3,4);
        goal[4] = new Target(Color.LIGHTBLUE, 4,3);
        goal[5] = new Target(Color.BLUE, 5,4);
    }
    public void setPGoal3(){
        goal[0] = new Target(Color.BLUE, 1, 0);
        goal[1] = new Target(Color.YELLOW,1, 3);
        goal[2] = new Target(Color.PINK, 2,2);
        goal[3] = new Target(Color.GREEN, 3, 1);
        goal[4] = new Target(Color.LIGHTBLUE,3,4);
        goal[5] = new Target(Color.WHITE, 5, 0);
    }
    public void setPGoal4(){
        goal[0] = new Target(Color.YELLOW,0,4);
        goal[1] = new Target(Color.LIGHTBLUE, 2,0);
        goal[2] = new Target(Color.BLUE,2,2);
        goal[3] = new Target(Color.PINK,3,3);
        goal[4] = new Target(Color.WHITE,4,1);
        goal[5] = new Target(Color.GREEN,4,2);
    }
    public void setPGoal5(){
        goal[0] = new Target(Color.LIGHTBLUE,1,1);
        goal[1] = new Target(Color.BLUE,3,1);
        goal[2] = new Target(Color.WHITE,3,2);
        goal[3] = new Target(Color.PINK,4,4);
        goal[4] = new Target(Color.YELLOW,5,0);
        goal[5] = new Target(Color.GREEN, 5,3);
    }

    public void setPGoal6(){
        goal[0] = new Target(Color.LIGHTBLUE,0,2);
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
        goal[3] = new Target(Color.LIGHTBLUE, 3,0);
        goal[4] = new Target(Color.YELLOW,4,4);
        goal[5] = new Target(Color.WHITE, 5,2);
    }
    public void setPGoal8(){
        goal[0] = new Target(Color.BLUE, 0,4);
        goal[1] = new Target(Color.GREEN,1,1);
        goal[2] = new Target(Color.LIGHTBLUE,2,2);
        goal[3] = new Target(Color.PINK,3,0);
        goal[4] = new Target(Color.WHITE, 4,3);
        goal[5] = new Target(Color.YELLOW, 5,3);
    }
    public void setPGoal9(){
        goal[0] = new Target(Color.YELLOW,0,2);
        goal[1] = new Target(Color.GREEN,2,2);
        goal[2] = new Target(Color.WHITE,3,4);
        goal[3] = new Target(Color.LIGHTBLUE,4,1);
        goal[4] = new Target(Color.PINK,4,4);
        goal[5] = new Target(Color.BLUE,5,0);
    }
    public void setPGoal10(){
        goal[0] = new Target(Color.LIGHTBLUE,0,4);
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
        goal[5] = new Target(Color.LIGHTBLUE,5,3);
    }
    public void setPGoal12(){
        goal[0] = new Target(Color.WHITE, 0,2);
        goal[1] = new Target(Color.PINK,1,1);
        goal[2] = new Target(Color.BLUE, 2,2);
        goal[3] = new Target(Color.LIGHTBLUE,3,3);
        goal[4] = new Target(Color.YELLOW,4,4);
        goal[5] = new Target(Color.GREEN, 5,0);
    }
}
