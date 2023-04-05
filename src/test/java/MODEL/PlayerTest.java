package MODEL;

import MODEL.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    @BeforeEach
    void init() {
        player = new Player("Gabriele");
    }
    @Test
    void testSelection2P(){
        Dashboard dashboard = new Dashboard(2);
        Bag bag = new Bag();
        dashboard.refill(bag);
        Slot selectedCard = player.selectCard(dashboard,1 ,3);
        System.out.println("MODEL.Color: "+selectedCard.getColor());
    }

    @Test
    void testSelection3P(){
        Dashboard dashboard = new Dashboard(3);
        Bag bag = new Bag();
        dashboard.refill(bag);
        Slot[] selectedCards = new Slot[3];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = new Slot(Color.GREY);
        }
        selectedCards[0] = player.selectCard(dashboard,2,6 );
        selectedCards[1] = player.selectCard(dashboard, 5,0);
        selectedCards[2] = player.selectCard(dashboard, 7,5);
        for(int i = 0; i < 3; i++){
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
    }
    @Test
    void testSelection4P(){
        Dashboard dashboard = new Dashboard(4);
        Bag bag = new Bag();
        dashboard.refill(bag);
        Slot[] selectedCards = new Slot[2];
        for(int i = 0; i < 2; i++){
            selectedCards[i] = new Slot(Color.GREY);
        }
        selectedCards[0] = player.selectCard(dashboard, 0,4);
        selectedCards[1] = player.selectCard(dashboard,6,2);
        for(int i = 0; i < 2; i++){
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
    }
    @Test
    void testOrder3(){
        Slot[] selectedCards = new Slot[3];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = new Slot(Color.randomColor());
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
        player.orderCards(selectedCards, 2, 3,1);
        for(int i = 0; i < 3; i++){
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
    }
    @Test
    void testOrder3_1(){
        Slot[] selectedCards = new Slot[3];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = new Slot(Color.randomColor());
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
        player.orderCards(selectedCards, 3, 2,1);
        for(int i = 0; i < 3; i++){
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
    }

    @Test
    void testOrder3_2(){
        Slot[] selectedCards = new Slot[3];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = new Slot(Color.randomColor());
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
        player.orderCards(selectedCards, 1, 2,3);
        for(int i = 0; i < 3; i++){
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
    }
    @Test
    void testOrder3_3(){
        Slot[] selectedCards = new Slot[3];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = new Slot(Color.randomColor());
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
        player.orderCards(selectedCards, 1, 3,2);
        for(int i = 0; i < 3; i++){
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
    }

    @Test
    void testOrder3_4(){
        Slot[] selectedCards = new Slot[3];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = new Slot(Color.randomColor());
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
        player.orderCards(selectedCards, 3, 1,2);
        for(int i = 0; i < 3; i++){
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
    }

    @Test
    void testOrder3_5(){
        Slot[] selectedCards = new Slot[3];
        for(int i = 0; i < 3; i++){
            selectedCards[i] = new Slot(Color.randomColor());
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
        player.orderCards(selectedCards, 2, 1,3);
        for(int i = 0; i < 3; i++){
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
    }
    @Test
    void testOrder2(){
        Slot[] selectedCards = new Slot[2];
        for(int i = 0; i < 2; i++){
            selectedCards[i]=new Slot(Color.randomColor());
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
        player.orderCards(selectedCards);
        for(int i = 0; i < 2; i++){
            System.out.println("MODEL.Color: "+selectedCards[i].getColor());
        }
    }

    @Test
    void testSumPoint(){
        int p = 8;
        player.sumPoints(p);
        assertEquals(8, player.getScore());
    }

    @Test
    void testCheckScore(){ //non so se serve: in fondo se sono testati i 3 metodi che lo compongono

    }
}