package MODEL;

import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.rmi.RemoteException;

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
    void testUpdateTurn() throws RemoteException {

        Game game = new Game(4);


        game.signPlayer("nome1");
        game.signPlayer("nome2");
        game.signPlayer("nome3");
        game.signPlayer("nome4");


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
         game.signPlayer("nome1");
         game.signPlayer("nome2");
         game.signPlayer("nome3");
         game.signPlayer("nome4");
         game.getPlayer()[0].setScore(1);
         game.getPlayer()[1].setScore(6);
         game.getPlayer()[2].setScore(6);
         game.getPlayer()[3].setScore(6);
         game.getPlayer()[0].setOrderInTurn(0);
         game.getPlayer()[1].setOrderInTurn(1);
         game.getPlayer()[2].setOrderInTurn(2);
         game.getPlayer()[3].setOrderInTurn(3);

         game.finalScore();

         for (int i = 0; i < game.getPlayer().length; i ++)
             System.out.println(i + 1 + ": " + game.getPlayer()[i].nickname + ", punti: " + game.getPlayer()[i].getScore() + ", ordine: " + game.getPlayer()[i].getOrderInTurn());

     }

     @Test
     void testAssignPGoal(){
        Game game = new Game(4);
        game.signPlayer("Gabriele");
        game.signPlayer("Alessio");
        game.signPlayer("Andrea");
        game.signPlayer("Rubin");
        game.assignPGoal();
        for(int i = 0; i < 6; i++){
            System.out.println("Colore: "+game.getPlayer()[1].getPgoal().getSingleTarget(i).getTile());
            System.out.println("X: "+game.getPlayer()[1].getPgoal().getSingleTarget(i).getPosX());
            System.out.println("Y: "+game.getPlayer()[1].getPgoal().getSingleTarget(i).getPosY());
        }
     }
}
