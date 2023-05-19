package VIEW.GraphicObjects;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import VIEW.GraphicObjects.GraphicResources.*;


public class WaitingRoom extends JFrame{

    private JScrollPane scrollPane1, scrollPane2;
    private JLabel rulebook1, rulebook2;
    private JProgressBar enrolledPlayer;

    public WaitingRoom(){

        this.rulebook1 = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("src/main/java/VIEW/GraphicObjects/GraphicResources/rulebook/Rulebook_ITA_My-Shelfie-1.jpg"))));
        this.rulebook2 = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("src/main/java/VIEW/GraphicObjects/GraphicResources/rulebook/Rulebook_ITA_My-Shelfie-2.jpg"))));

        this.scrollPane1 = new JScrollPane(rulebook1);
        this.scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane2 = new JScrollPane(rulebook2);
        this.scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.enrolledPlayer = new JProgressBar();
        enrolledPlayer.setForeground(Color.RED);

        setLayout(new GridLayout(4,1));
        add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("src/main/java/VIEW/GraphicObjects/GraphicResources/Publisher material/Title 2000x618px.png")))));
        add(rulebook1);
        add(rulebook2);
        add(enrolledPlayer);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public JProgressBar getEnrolledPlayer(){
        return enrolledPlayer;
    }

}
