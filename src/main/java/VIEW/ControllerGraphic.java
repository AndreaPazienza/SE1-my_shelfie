package VIEW;

import Errors.SameNicknameException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ControllerGraphic {
    @FXML
    TextField inserimentoNick;
    Button confirmNick;


    public String  ConfermaScelta(javafx.event.ActionEvent actionEvent) {
        String nick = inserimentoNick.getText();
        return nick;
    }

    public void GetText(ActionEvent actionEvent) {
    }

    public int Return2(ActionEvent actionEvent) {
        int nPlayers = 2;
        return nPlayers;
    }

    public int Return3(ActionEvent actionEvent) {
        int nPlayers = 3;
        return nPlayers;
    }

    public int Return4(ActionEvent actionEvent) {
        int nPlayers = 4;
        return nPlayers;
    }
}
