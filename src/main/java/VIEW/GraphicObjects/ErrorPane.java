package VIEW.GraphicObjects;

import javax.swing.*;

public class ErrorPane extends JOptionPane {

    public ErrorPane(String error){
        JOptionPane.showMessageDialog(null,
                error,
                "ERRORE!",
                JOptionPane.WARNING_MESSAGE);
    }

}
