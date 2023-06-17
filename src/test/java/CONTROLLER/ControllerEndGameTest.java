package CONTROLLER;

import MODEL.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.View;

class ControllerEndGameTest {
    private Game model = new Game(2);
    private GameController controller = new GameController(model);

    @BeforeEach
    void init() {
        model.getTable().refill(model.getBag());
        model.getTable().catchAfterRefill();
        String nick1 = "Gabry";
        String nick2 = "Rubin";
        model.signPlayer(nick1);
        model.signPlayer(nick2);
        model.assignPGoal();
    }


    @Test
        void checkEndGame1(){
           PersonalGoal personal = model.getPlayer()[0].getPgoal();
           PersonalGoal personalB= model.getPlayer()[1].getPgoal();
           personal.setPGoal1();
           personalB.setPGoal2();
           model.getPlayer()[0].setPgoal(personal);
           model.getPlayer()[1].setPgoal(personalB);

        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                    model.getPlayer()[0].getShelf().getSingleSlot(i, j).setColor(Color.randomColor());
                }
            }
        model.getPlayer()[0].getShelf().getSingleSlot(0,0).setColor(Color.PINK);
        model.getPlayer()[0].getShelf().getSingleSlot(0,2).setColor(Color.BLUE);
        model.getPlayer()[0].getShelf().getSingleSlot(1,4).setColor(Color.GREEN);
        model.getPlayer()[0].getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        model.getPlayer()[0].getShelf().getSingleSlot(3,1).setColor(Color.YELLOW);
        model.getPlayer()[0].getShelf().getSingleSlot(5,2).setColor(Color.LBLUE);

        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                model.getPlayer()[1].getShelf().getSingleSlot(i, j).setColor(Color.randomColor());
            }
        }
        int firstScore = model.getPlayer()[0].getShelf().calculatePoints();
        int secondScore = model.getPlayer()[1].getShelf().calculatePoints();
        int pgoalFirst = model.getPlayer()[0].getPgoal().assignPoint(model.getPlayer()[0].getShelf());
        int pgoalSecond = model.getPlayer()[1].getPgoal().assignPoint(model.getPlayer()[1].getShelf());
        System.out.println("Il giocatore 1 ha fatto:"+ firstScore +" punti con le adiacenze, e "+ pgoalFirst
                +" con i PGoal"+ ".Il secondo invece:"+ secondScore+ " con le adiacenze e " +pgoalSecond + " con i Pgoal");
        //String end = controller.endGame();
        //System.out.print(end);
        }
    }


