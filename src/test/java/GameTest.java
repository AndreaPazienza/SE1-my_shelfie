import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

public class GameTest {

    private Game game;

    //Test dei costruttori per ogi possbile parametro d'ingresso passato parzialmente (problemi a costruire i commonGoal)
    @Test
    void testGame2 () {

        game = new Game(4);

        System.out.print("Numero di giocatori: "+ Game.Nplayers +"\n");
        System.out.print("Lunghezza array: "+ game.getPlayer().length +"\n");
        System.out.print("Game attivo: "+ game.isGameOn() +"\n");

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (game.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!game.getTable().getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

    }

    @Test
    void testGame3 () {

        game = new Game(4);

        System.out.print("Numero di giocatori: "+ Game.Nplayers +"\n");
        System.out.print("Lunghezza array: "+ game.getPlayer().length +"\n");
        System.out.print("Game attivo: "+ game.isGameOn() +"\n");

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (game.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!game.getTable().getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

    }

    @Test
    void testGame4 () {

        game = new Game(4);

        System.out.print("Numero di giocatori: "+ Game.Nplayers +"\n");
        System.out.print("Lunghezza array: "+ game.getPlayer().length +"\n");
        System.out.print("Game attivo: "+ game.isGameOn() +"\n");

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (game.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!game.getTable().getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


    //Test signPlayer passato
    @Test
    void testSignPlayer () {

        Game game = new Game(4);
        Player player1 = new Player("nome1");
        Player player2 = new Player("nome2");
        Player player3 = new Player("nome3");
        Player player4 = new Player("nome4");

        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
        game.signPlayer(player1);
        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
        game.signPlayer(player2);
        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
        game.signPlayer(player3);
        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
        game.signPlayer(player4);
        System.out.print("Giocatori iscritti: " + game.getPlayerInGame() + "\n");
    }


    //Test updateTurn passato (non verificata la parte relativa ai commonGoal)
    @Test
    void testUpdateTurn() {

        Game game = new Game(4);

        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (game.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else System.out.print(game.getTable().getSingleSlot(i,j).getColor() + " , ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        System.out.print(game.getTable().checkRefill() + "\n");
        game.updateTurn();
        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");
        System.out.print(game.getTable().checkRefill() + "\n");
        game.updateTurn();
        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");
        System.out.print(game.getTable().checkRefill() + "\n");
        game.updateTurn();
        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");
        System.out.print(game.getTable().checkRefill() + "\n");
        game.updateTurn();
        System.out.print("Giocatore di turno: " + game.getPlayerInGame() + "\n");
        System.out.print("\n");

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (game.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else System.out.print(game.getTable().getSingleSlot(i,j).getColor() + " , ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
     }


     //Test finalScore passato
     @Test
     void testFinalScore() {

         Game game = new Game(4);
         Player player1 = new Player("nome1");
         Player player2 = new Player("nome2");
         Player player3 = new Player("nome3");
         Player player4 = new Player("nome4");
         game.signPlayer(player1);
         game.signPlayer(player2);
         game.signPlayer(player3);
         game.signPlayer(player4);
         player1.setScore(6);
         player2.setScore(6);
         player3.setScore(4);
         player4.setScore(3);
         player1.setOrderInTurn(0);
         player2.setOrderInTurn(1);
         player3.setOrderInTurn(2);
         player4.setOrderInTurn(3);

         game.finalScore();

     }
}
