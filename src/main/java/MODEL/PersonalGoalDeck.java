package MODEL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PersonalGoalDeck {

    private ArrayList <PersonalGoal> personalGoalDeck = new ArrayList<>();

    public PersonalGoalDeck() {
        // file JSON read
        FileReader reader;

        {
            try {
                reader = new FileReader("PersonalGoals.json");
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
