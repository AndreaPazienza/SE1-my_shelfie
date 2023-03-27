import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    @BeforeEach
    void setUp() {
        player = new Player("Gabriele");
    }

}