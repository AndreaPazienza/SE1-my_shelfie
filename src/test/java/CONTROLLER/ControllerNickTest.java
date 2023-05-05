package CONTROLLER;

import Errors.SameNicknameException;
import MODEL.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerNickTest {
    private Game model = new Game(2);
    private GameController controller = new GameController(model);


    @Test
    void checkNick() {
        boolean check;
        String nick1 = "Gabry";
        String nick2 = "Gabry";
        model.signPlayer(nick1);
        check=controller.checkNick(nick2);
        if(check){
            System.out.print("Hai selezionato un nick corretto");
            model.signPlayer(nick2);
        }
        else {
            System.out.print("Hai selezionato un nick non corretto, riprova");
        }

    }
}