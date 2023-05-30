package VIEW;

import MODEL.Player;
import org.junit.jupiter.api.Test;

public class EndGameTextTest {

    @Test
    void RankingTest (){

        Player[] ranking = new Player[4];
        ranking[0] = new Player("Nome1");
        ranking[1] = new Player("Nome2");
        ranking[2] = new Player("Nome3");
        ranking[3] = new Player("Nome4");
        ranking[0].setScore(10);
        ranking[1].setScore(8);
        ranking[2].setScore(6);
        ranking[3].setScore(4);
        ranking[0].setOrderInTurn(1);
        ranking[1].setOrderInTurn(2);
        ranking[2].setOrderInTurn(3);
        ranking[3].setOrderInTurn(4);

        String rankingText = "";

        for(int i = 1; i <= ranking.length; i ++) {
            rankingText = rankingText + i + "°: " + ranking[i-1].getNickname() + " \n    punti totalizzati: " + ranking[i-1].getScore() + " \n    ordine nel giro: " + ranking[i-1].getOrderInTurn() + "\n\n";
        }

        System.out.print(rankingText);
    }

    @Test
    void WinnerTest (){

        Player[] ranking = new Player[4];
        ranking[0] = new Player("Nome1");
        ranking[1] = new Player("Nome2");
        ranking[2] = new Player("Nome3");
        ranking[3] = new Player("Nome4");
        ranking[0].setScore(10);
        ranking[1].setScore(8);
        ranking[2].setScore(6);
        ranking[3].setScore(4);
        ranking[0].setOrderInTurn(1);
        ranking[1].setOrderInTurn(2);
        ranking[2].setOrderInTurn(3);
        ranking[3].setOrderInTurn(4);

        String winnerText = "";

        winnerText = "Congratulazioni " + ranking[0].getNickname() + " hai vinto questa partita!";

        System.out.print(winnerText);

    }

}
