package MODEL;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.io.IOException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Game game;

    //Test dei costruttori per ogi possbile parametro d'ingresso passato parzialmente (problemi a costruire i commonGoal)
    @Test
    void testGame2() throws IOException, ParseException {

        game = new Game(4);

        System.out.print("Numero di giocatori: " + Game.Nplayers + "\n");
        System.out.print("Lunghezza array: " + game.getPlayer().length + "\n");
        System.out.print("Game attivo: " + game.isGameOn() + "\n");

        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                if (game.getTable().getSingleSlot(i, j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!game.getTable().getSingleSlot(i, j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    @Test
    void testGame3() throws IOException, ParseException {

        game = new Game(4);

        System.out.print("Numero di giocatori: " + Game.Nplayers + "\n");
        System.out.print("Lunghezza array: " + game.getPlayer().length + "\n");
        System.out.print("Game attivo: " + game.isGameOn() + "\n");

        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                if (game.getTable().getSingleSlot(i, j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!game.getTable().getSingleSlot(i, j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

    }

    @Test
    void testGame4() throws IOException, ParseException {

        game = new Game(4);

        System.out.print("Numero di giocatori: " + Game.Nplayers + "\n");
        System.out.print("Lunghezza array: " + game.getPlayer().length + "\n");
        System.out.print("Game attivo: " + game.isGameOn() + "\n");

        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                if (game.getTable().getSingleSlot(i, j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!game.getTable().getSingleSlot(i, j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


    //Test signPlayer passato
    @Test
    void testSignPlayer() throws IOException, ParseException {

        Game game = new Game(4);

        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
        game.signPlayer("nome1");
        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
        game.signPlayer("nome2");
        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
        game.signPlayer("nome3");
        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
        game.signPlayer("nome4");
        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
    }


    //Test updateTurn passato (non verificata la parte relativa ai commonGoal)
    @Test
    void testUpdateTurn() throws IOException, ParseException {

        Game game = new Game(4);


        game.signPlayer("nome1");
        game.signPlayer("nome2");
        game.signPlayer("nome3");
        game.signPlayer("nome4");


        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");

        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                if (game.getTable().getSingleSlot(i, j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else System.out.print(game.getTable().getSingleSlot(i, j).getColor() + " , ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        System.out.print(game.getTable().checkRefill() + "\n");
        //game.updateTurn();
        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");
        System.out.print(game.getTable().checkRefill() + "\n");
        //game.updateTurn();
        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");
        System.out.print(game.getTable().checkRefill() + "\n");
        //game.updateTurn();
        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");
        System.out.print(game.getTable().checkRefill() + "\n");
        //game.updateTurn();
        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");
        System.out.print("\n");

        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {
                if (game.getTable().getSingleSlot(i, j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else System.out.print(game.getTable().getSingleSlot(i, j).getColor() + " , ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


    //Test finalScore passato
    @Test
    void testFinalScore() throws IOException, ParseException {

        Game game = new Game(4);
        game.signPlayer("nome1");
        game.signPlayer("nome2");
        game.signPlayer("nome3");
        game.signPlayer("nome4");
        game.getPlayer()[0].setScore(6);
        game.getPlayer()[0].setScore(6);
        game.getPlayer()[0].setScore(4);
        game.getPlayer()[0].setScore(3);
        game.getPlayer()[0].setOrderInTurn(0);
        game.getPlayer()[0].setOrderInTurn(1);
        game.getPlayer()[0].setOrderInTurn(2);
        game.getPlayer()[0].setOrderInTurn(3);

        game.finalScore();

    }

    @Test
    void testAssignPGoal() throws IOException, ParseException {
        Game game = new Game(4);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        game.signPlayer("Andrea");
        game.signPlayer("Rubin");
        game.assignPGoal();
        for (int i = 0; i < 6; i++) {
            System.out.println("Colore: " + game.getPlayer()[1].getPgoal().getSingleTarget(i).getTile());
            System.out.println("X: " + game.getPlayer()[1].getPgoal().getSingleTarget(i).getPosX());
            System.out.println("Y: " + game.getPlayer()[1].getPgoal().getSingleTarget(i).getPosY());
        }
    }

    @Test
    void testSetLastError() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        GameError gameError = GameError.SELECT_ERROR_NOT_CATCHABLE;
        game.setLastError(gameError);
    }

    @Test
    void testGetPosition() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        int position = game.positionInGame("Alessio");
        assertEquals(1, position);
    }

    @Test
    void testPlayerOnStage() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        Player nuovo = game.playerOnStage();
        String name = nuovo.getNickname();
        String compare = game.playerOnStage().getNickname();
        assertEquals(name, compare);
    }

    @Test
    void testSGame() throws IOException, ParseException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        Game game = new Game(2);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        game.startGame();
    }

    @Test
    void testgetNplayers() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        game.getNplayers();
    }

    @Test
    void testgetCommonGoalDeck() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        CommonGoalDeck deck = game.getCommonGoalDeck();
    }

    @Test
    void testsetCommonGoal1() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        game.setCommonGoal1(game.getCommonGoal1());
    }

    @Test
    void testPGoalDeck() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        game.getDeck();
    }
}
