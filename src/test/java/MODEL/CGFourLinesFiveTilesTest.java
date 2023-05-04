package MODEL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGFourLinesFiveTilesTest {


    private Player current1;
    private CGOnLines testingCommonGoal;
    @BeforeEach
    public void init(){
        current1 = new Player("Dovahkiin");
        testingCommonGoal = new CGFourLinesFiveTiles(4);
    }

    @Test
    void emptyOne(){
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void checkOneColumn(){
        //Controllo anche il fatto che venga trovata almeno la riga verde
        current1.getShelf().getSingleSlot(0,0).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,0).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(3,0).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(4,0).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore() );
    }
    @Test
    void checkFirstMiddleLastColumnTrue(){
        //Controllo anche il fatto che venga trovata almeno la riga verde
        current1.getShelf().getSingleSlot(0,0).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(0,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(0,2).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(0,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(0,4).setColor(Color.GREEN);

        current1.getShelf().getSingleSlot(1,4).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(1,3).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,0).setColor(Color.LBLUE);

        current1.getShelf().getSingleSlot(5,4).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,2).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);

        current1.getShelf().getSingleSlot(3,4).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(3,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(3,1).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(3,0).setColor(Color.WHITE);


        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore() );
    }

    //Controllo solo sul algoritmo con un giocatore, gli altri casi di assegnazione punti corretta sono già coperti in altre classi
    //Di conseguenza l'importante è che questo controllo funzioni una volta

    @Test
    void testShow(){
        testingCommonGoal.show();
    }


}