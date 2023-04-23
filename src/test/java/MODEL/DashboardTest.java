package MODEL;

import MODEL.Bag;
import MODEL.Color;
import MODEL.Dashboard;
import org.testng.annotations.Test;
import java.util.Random;


//Test dei costruttori per tutti e tre i parametri passato
public class DashboardTest {

    private Dashboard dashboard;
    private Bag bag;

    @Test
    void testDashboard2 () {

        dashboard = new Dashboard(2);

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    @Test
    void testDashboard3 () {

        dashboard = new Dashboard(3);

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    @Test
    void testDashboard4 () {

        dashboard = new Dashboard(4);

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


    //Test di refill da MODEL.Dashboard vuota passato per tutti e tre i costruttori
    @Test
    void testRefill2 () {

        dashboard = new Dashboard(2);
        bag = new Bag();

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        dashboard.refill(bag);

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY))
                    System.out.print("1 ");
            }
            System.out.print("\n");
        }
    }

    @Test
    void testRefill3 () {

        dashboard = new Dashboard(3);
        bag = new Bag();

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        dashboard.refill(bag);

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY))
                    System.out.print("1 ");
            }
            System.out.print("\n");
        }
    }

    @Test
    void testRefill4 () {

        dashboard = new Dashboard(4);
        bag = new Bag();

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        dashboard.refill(bag);

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY))
                    System.out.print("1 ");
            }
            System.out.print("\n");
        }
    }

    //Test della casualità dell'estrazione passato
    @Test
    void testRefillColor () {

        dashboard = new Dashboard(4);
        bag = new Bag();

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");




        for(int k = 0; k < Dashboard.getSide(); k++){
            System.out.print("\t  "+k+"  \t");
        }
        System.out.print("\n");
        dashboard.refill(bag);
        for (int i = 0; i < Dashboard.getSide(); i ++) {
            System.out.print(""+i);
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print("\t     \t");
                else if (!dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY))
                    System.out.print("\t" + dashboard.getSingleSlot(i,j).getColor() + "\t");
                else {
                    System.out.print("\t     \t");
                }
            }
            System.out.print("\n");
        }
    }


    //Test del setting di catchable passato
    @Test
    void testRefillCatchable () {

        dashboard = new Dashboard(4);
        bag = new Bag();

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        dashboard.refill(bag);

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY) && !dashboard.getSingleSlot(i,j).isCatchable())
                    System.out.print("0 ");
                else if (!dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY) && dashboard.getSingleSlot(i,j).isCatchable())
                    System.out.print("1 ");
            }
            System.out.print("\n");
        }
    }

    //Test di refill da una qualsiasi situazione di gioco (creazione dashboard randomica) passato
    @Test
    void testRefillCasualDashboard () {

        dashboard = new Dashboard(4);
        bag = new Bag();

        //Riempimento casuale della MODEL.Dashboard per simulare un qualsiasi momento della partita
        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                int riempi = new Random().nextInt(2);

                if (riempi == 1 && dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) {
                    dashboard.setSingleSlot(bag.getSingleSlot(), i,j);
                }
            }
        }

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else System.out.print(dashboard.getSingleSlot(i,j).getColor() + " , ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        dashboard.refill(bag);

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY))
                    System.out.print(dashboard.getSingleSlot(i,j).getColor() + " , ");
            }
            System.out.print("\n");
        };
    }

    //Test del setting di catchable da una qualsiasi situazione di gioco (creazione dashboard randomica) passato
    @Test
    void testRefillCasualDashboardCatchable () {

        dashboard = new Dashboard(4);
        bag = new Bag();

        //Riempimento casuale della MODEL.Dashboard per simulare un qualsiasi momento della partita
        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                int riempi = new Random().nextInt(2);

                if (riempi == 1 && dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) {
                    dashboard.setSingleSlot(bag.getSingleSlot(), i,j);
                }
            }
        }

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else System.out.print(dashboard.getSingleSlot(i,j).getColor() + " , ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        dashboard.refill(bag);

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (!dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY) && dashboard.getSingleSlot(i,j).isCatchable())
                    System.out.print(dashboard.getSingleSlot(i,j).getColor() + " , ");
                else if (!dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY) && !dashboard.getSingleSlot(i,j).isCatchable())
                    System.out.print("-, ");
            }
            System.out.print("\n");
        };
    }


    //Test di checkRefill in una qualsiasi situazione di gioco (creazione dashboard randomica) passato
    @Test
    void testCheckRefillCasual () {

        dashboard = new Dashboard(4);
        bag = new Bag();

        //Riempimento casuale della MODEL.Dashboard per simulare un qualsiasi momento della partita
        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                int riempi = new Random().nextInt(2);

                if (riempi == 1 && dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) {
                    dashboard.setSingleSlot(bag.getSingleSlot(), i,j);
                }
            }
        }

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else System.out.print(dashboard.getSingleSlot(i,j).getColor() + " , ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        System.out.print(dashboard.checkRefill());
    }

    //Test di checkRefill nei casi particolari passato
    @Test
    void testCheckRefill () {

        dashboard = new Dashboard(4);
        bag = new Bag();

        //Riempimento della MODEL.Dashboard
        dashboard.setSingleSlot(bag.getSingleSlot(),4 ,4);
        dashboard.setSingleSlot(bag.getSingleSlot(), 5,5);
        dashboard.setSingleSlot(bag.getSingleSlot(),3 ,5);
        dashboard.setSingleSlot(bag.getSingleSlot(), 3,3);
        dashboard.setSingleSlot(bag.getSingleSlot(), 5,3);
        dashboard.setSingleSlot(bag.getSingleSlot(), 6,2);
        dashboard.setSingleSlot(bag.getSingleSlot(), 8,4);

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else System.out.print(dashboard.getSingleSlot(i,j).getColor() + " , ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        System.out.print(dashboard.checkRefill());
    }


    //Test di adjaciencies passato
    @Test
    void testAdjaciencies () {

        dashboard = new Dashboard(4);
        bag = new Bag();

        //Riempimento casuale della MODEL.Dashboard per simulare un qualsiasi momento della partita
        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                int riempi = new Random().nextInt(2);

                if (riempi == 1 && dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) {
                    dashboard.setSingleSlot(bag.getSingleSlot(), i,j);
                }
            }
        }

        for (int i = 0; i < Dashboard.getSide(); i ++) {
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else System.out.print(dashboard.getSingleSlot(i,j).getColor() + " , ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        int x = new Random().nextInt(9);
        int y = new Random().nextInt(9);


        System.out.print("MODEL.Slot estratto: " + x +";"+ y + "\n");
        System.out.print ("Il numero delle adiacenze è " + dashboard.adjaciencies(x,y));
    }
}

// TEST

