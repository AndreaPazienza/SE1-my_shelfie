package MODEL;

import org.json.simple.parser.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameViewTest {


    @Test
    void testConstructor1 () throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        GameView gameView = new GameView(game);
    }


    @Test
    void testConstructor2 () throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        int i = 1;
        GameView gameView = new GameView(i, game);
    }

    @Test
    void testConstructor3()throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        int i = 1;
        GameView gameView = new GameView(game, i);
    }

    @Test
    void testGetACommonGoal1() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        GameView gameView = new GameView(game);
        CommonGoalAbs commonGoalAbsg = gameView.getCommonGoal1();
    }

    @Test
    void testGetACommonGoal2() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        GameView gameView = new GameView(game);
        CommonGoalAbs commonGoalAbsg = gameView.getCommonGoal2();
    }

    @Test
    void testGetTable() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        GameView gameView = new GameView(game);
        Dashboard table = gameView.getTable();
    }

    @Test
    void testGetShelf() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        GameView gameView = new GameView(game);
        PersonalShelf shelf = gameView.getShelf();
    }

    @Test
    void testShelf() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        GameView gameView = new GameView(game);
        PersonalShelf shelf = gameView.getShelf();
    }

    @Test
    void getPGoal() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        GameView gameView = new GameView(game);
        PersonalGoal goal = gameView.getPgoal();
    }


    @Test
    void getGameError() throws IOException, ParseException {
        Game game = new Game(2);
        game.signPlayer("Mark");
        game.signPlayer("Gianni");
        GameView gameView = new GameView(game);
        GameError gameError = gameView.getGameError();
    }













}