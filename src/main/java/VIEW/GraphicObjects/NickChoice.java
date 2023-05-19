package VIEW.GraphicObjects;

import javax.swing.*;
import java.awt.*;

public class NickChoice extends JFrame {

    private JFrame window;
    private JTextField nickField;

    private JButton confirm;

    private JLabel label;


    public NickChoice(){
        this.window = new JFrame();
        this.label = new JLabel("Inserire il nickname: ");
        this.nickField = new JTextField();
        this.confirm = new JButton("INVIA");

        window.setSize(500, 600);
        window.setVisible(true);
        //caricamento sfondo
        nickField.setVisible(true);
        confirm.setVisible(true);

        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(nickField, BorderLayout.CENTER);
        add(confirm, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getConfirm() {
        return confirm;
    }

    public JTextField getNickField() {
        return nickField;
    }

    public JFrame getWindow() {
        return window;
    }
}
