package VIEW.GraphicObjects;

import javax.swing.*;
import java.awt.*;

public class NickChoice extends JFrame{

    private JTextField nickField;

    private JButton confirm;

    private JLabel label;


    public NickChoice(){
        super(" INSERIMENTO NICKNAME");
        this.label = new JLabel("Inserire il nickname: ");
        this.nickField = new JTextField();
        this.confirm = new JButton("INVIA");

        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(nickField, BorderLayout.CENTER);
        add(confirm, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton getConfirm() {
        return confirm;
    }

    public JTextField getNickField() {
        return nickField;
    }

}
