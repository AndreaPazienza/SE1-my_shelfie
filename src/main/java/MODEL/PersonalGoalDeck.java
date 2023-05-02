package MODEL;

import java.util.ArrayList;
import java.util.Random;

public class PersonalGoalDeck {

    private ArrayList <PersonalGoal> personalGoalDeck = new ArrayList<>();

    public PersonalGoalDeck(){
        PersonalGoal pgoal1 = new PersonalGoal();
        pgoal1.setPGoal1();
        this.personalGoalDeck.add(pgoal1);

        PersonalGoal pgoal2 = new PersonalGoal();
        pgoal2.setPGoal2();
        this.personalGoalDeck.add(pgoal2);

        PersonalGoal pgoal3 = new PersonalGoal();
        pgoal3.setPGoal3();
        this.personalGoalDeck.add(pgoal3);

        PersonalGoal pgoal4 = new PersonalGoal();
        pgoal4.setPGoal4();
        this.personalGoalDeck.add(pgoal4);

        PersonalGoal pgoal5 = new PersonalGoal();
        pgoal5.setPGoal5();
        this.personalGoalDeck.add(pgoal5);

        PersonalGoal pgoal6 = new PersonalGoal();
        pgoal6.setPGoal6();
        this.personalGoalDeck.add(pgoal6);

        PersonalGoal pgoal7 = new PersonalGoal();
        pgoal7.setPGoal7();
        this.personalGoalDeck.add(pgoal7);

        PersonalGoal pgoal8 = new PersonalGoal();
        pgoal8.setPGoal8();
        this.personalGoalDeck.add(pgoal8);

        PersonalGoal pgoal9 = new PersonalGoal();
        pgoal9.setPGoal9();
        this.personalGoalDeck.add(pgoal9);

        PersonalGoal pgoal10 = new PersonalGoal();
        pgoal10.setPGoal10();
        this.personalGoalDeck.add(pgoal10);

        PersonalGoal pgoal11 = new PersonalGoal();
        pgoal11.setPGoal11();
        this.personalGoalDeck.add(pgoal11);

        PersonalGoal pgoal12 = new PersonalGoal();
        pgoal12.setPGoal12();
        this.personalGoalDeck.add(pgoal12);
    }

    public PersonalGoal extractionPGoal(){

        int randomIndex = new Random().nextInt(personalGoalDeck.size()-1);
        PersonalGoal returningPersonal = personalGoalDeck.get(randomIndex);
        personalGoalDeck.remove(randomIndex);
        return returningPersonal;
    }

    public ArrayList<PersonalGoal> getPersonalGoalDeck() {
        return personalGoalDeck;
    }
}
