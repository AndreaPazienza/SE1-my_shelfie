import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

public class DashboardTest {

    private Dashboard dashboard;

    @Test
    void testDashboard2 () {

        dashboard = new Dashboard(2);

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++ ) {
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

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++ ) {
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

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++ ) {
                if (dashboard.getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print(". ");
                else if (dashboard.getSingleSlot(i,j).getColor().equals(Color.GREY)) System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
