import org.junit.jupiter.api.Test;

public class PersonalGoalDeckTest {

    //Test del costruttore passato
    @Test
    public void testPersonalGoalDeck() {

        PersonalGoalDeck personalGoalDeck;
        PersonalGoal pGoal;

        personalGoalDeck = new PersonalGoalDeck();
        pGoal = new PersonalGoal();

        for (int i = 0; i < personalGoalDeck.getPersonalGoalDeck().size(); i ++) {
            pGoal = personalGoalDeck.getPersonalGoalDeck().get(i);
            for(int j = 0; j < 6; j ++) {
                System.out.print("Colore: " + pGoal.getSingleTarget(j).getTile() + "\n");
                System.out.print("Coordinate: " + pGoal.getSingleTarget(j).getPosX() + ";" + pGoal.getSingleTarget(j).getPosY() + "\n");
            }
        }
    }

    //Test dell'estrazione dal mazzo passato
    @Test
    public void testExtraction() {

        PersonalGoalDeck personalGoalDeck;
        PersonalGoal pGoal;

        personalGoalDeck = new PersonalGoalDeck();
        pGoal = new PersonalGoal();

        pGoal = personalGoalDeck.extractionPGoal();

        for(int j = 0; j < 6; j ++) {
            System.out.print("Colore: " + pGoal.getSingleTarget(j).getTile() + "\n");
            System.out.print("Coordinate: " + pGoal.getSingleTarget(j).getPosX() + ";" + pGoal.getSingleTarget(j).getPosY() + "\n");
        }
        System.out.print("\n");

        for (int i = 0; i < personalGoalDeck.getPersonalGoalDeck().size(); i ++) {
            pGoal = personalGoalDeck.getPersonalGoalDeck().get(i);
            for(int j = 0; j < 6; j ++) {
                System.out.print("Colore: " + pGoal.getSingleTarget(j).getTile() + "\n");
                System.out.print("Coordinate: " + pGoal.getSingleTarget(j).getPosX() + ";" + pGoal.getSingleTarget(j).getPosY() + "\n");
            }
        }
    }

    //Test dell'estrazione multipla passato
    @Test
    public void testExtraction2() {

        PersonalGoalDeck personalGoalDeck;
        PersonalGoal pGoal;

        personalGoalDeck = new PersonalGoalDeck();
        pGoal = new PersonalGoal();

        for (int i = 0; i < 4; i++)  {

            pGoal = personalGoalDeck.extractionPGoal();

            for (int j = 0; j < 6; j++) {
                System.out.print("Colore: " + pGoal.getSingleTarget(j).getTile() + "\n");
                System.out.print("Coordinate: " + pGoal.getSingleTarget(j).getPosX() + ";" + pGoal.getSingleTarget(j).getPosY() + "\n");
            }
        System.out.print("\n");
        }
    }
}
