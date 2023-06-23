package VIEW.GraphicObjects;

import javax.swing.*;
import java.awt.*;

public class NickChoice extends JPanel{

    private JTextField nickField;

    private JButton confirm;

    private JLabel label;


    public NickChoice(){
        super();
        this.nickField = new JTextField();
        this.label = new JLabel("Inserire il nickname: ");
        this.confirm = new JButton("Conferma!");
        confirm.addActionListener(e -> {
            String nickname = nickField.getText();
            JOptionPane.showMessageDialog(this, "Nickname scelto: "+nickname);
        });
        this.nickField.setEnabled(true);
        setLayout(new GridLayout(3,1));
        add(label);
        add(nickField);
        add(confirm);
        setVisible(true);
    }

    public String getNick(){
        return nickField.getText();
    }
    public JButton getConfirm() {
        return confirm;
    }

    public JTextField getNickField() {
        return nickField;
    }

}
