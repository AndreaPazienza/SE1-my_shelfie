package MODEL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;


public class PersonalGoalDeck {

    private ArrayList <PersonalGoal> personalGoalDeck = new ArrayList<>();

    public PersonalGoalDeck() {
        // file JSON read
        FileReader reader;

        {
            try {
                reader = new FileReader("src/main/java/File/PersonalGoals.json");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        // Parsing JSON file
        JSONParser parser = new JSONParser();
        Object obj;

        {
            try {
                obj = parser.parse(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        JSONObject jsonObject = (JSONObject) obj;

        //Now I have the JSON object representing the file content.
        //Extraction of the necessary data from this object.

        JSONArray goalsArray = (JSONArray) jsonObject.get("goal");


        for (Object goalObj : goalsArray) {
            JSONObject goalJson = (JSONObject) goalObj;
            JSONArray targetArray = (JSONArray) goalJson.get("target");

            // Creating PersonalGoal object and adding the targets.
            PersonalGoal personalGoal = new PersonalGoal();

            for (Object targetObj : targetArray) {
                JSONObject targetJson = (JSONObject) targetObj;

                Long posX = (Long) targetJson.get("posX");
                Long posY = (Long) targetJson.get("posY");

                String color = (String) targetJson.get("color");
                Color color1 = Color.colorToString(color);

                int intPosX = posX.intValue();
                int intPosY = posY.intValue();

                // Creating a Target object and adding it to the PersonalGoal object
                Target target = new Target(color1, intPosX, intPosY);
                personalGoal.addTarget(target);
            }
            this.personalGoalDeck.add(personalGoal);
        }
    }
    /*public PersonalGoalDeck(){
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
    /*
     */

    public PersonalGoal extractionPGoal(){

        int randomIndex = new Random().nextInt(personalGoalDeck.size());
        PersonalGoal returningPersonal = personalGoalDeck.get(randomIndex);
        personalGoalDeck.remove(randomIndex);
        return returningPersonal;
    }

    public ArrayList<PersonalGoal> getPersonalGoalDeck() {
        return personalGoalDeck;
    }

}
