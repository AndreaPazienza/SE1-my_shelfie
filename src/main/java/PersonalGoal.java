import java.util.Random;

public class PersonalGoal {
    private Target[] goal;
    private static final int N_GOALS = 6;

    public int AssignPoint(PersonalShelf shelf) { //dubbio: devo passare shelf??
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
        Random random = new Random();
        for(int i = 0; i < N_GOALS; i++){
            int x = random.nextInt(6);      //genero randomicamente ogni coordinata e colore dell'obbiettivo
            int y = random.nextInt(5);
            Color c = Color.RandomColor();
            this.goal[i] = new Target(c, x, y);
        }
    }
}
