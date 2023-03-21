import java.util.Random;

public class PersonalGoal {
    private Target[] goal;

    public int AssignPoint(PersonalShelf shelf) { //dubbio: devo passare shelf??
        int counter = 0;
        int points = 0;
        for (int i = 0; i < 6; i++) {            //nel for faccio i confronti; counter mi tiene traccia del numero
            int x = goal[i].getPosX();           //di confronti passati
            int y = goal[i].getPosY();
            Color col = goal[i].getTile();
            if (col.equals(shelf[x][y].getColor())) {
                counter++;
            }
        }
        switch (counter) {           //lo switch mi serve ad assegnare i punteggi; essendo una void, aggiorno diretto
            case 0:                  //l'attributo Score del player;
                points = 0;
            case 1:
                points = points++;
            case 2:
                points = points + 2;
            case 3:
                points = points + 4;
            case 4:
                points = points + 6;
            case 5:
                points = points + 9;
            case 6:
                points = points + 12;
        }
        return points;
    }

    public PersonalGoal(Target[] obj) {
        Target[] pgoal = new Target[6];
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            int x = random.nextInt(6);      //genero randomicamente ogni coordinata e colore dell'obbiettivo
            int y = random.nextInt(5);
            Color c = Color.randomColor();
            pgoal[i] = new Target(c, x, y);
        }
    }
}
