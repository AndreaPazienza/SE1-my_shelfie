package VIEW.GraphicObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.util.Objects;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import VIEW.GraphicObjects.GraphicResources.*;


public class WaitingRoom extends JFrame{

    private JScrollPane scrollPane1, scrollPane2;
    private JLabel rulebook1, rulebook2;
    private JProgressBar enrolledPlayer;

    public WaitingRoom() throws IOException {
        super("IN ATTESA CHE ARRIVINO TUTTI I GIOCATORI");
        BufferedImage[] images = new BufferedImage[3];
        images[0] = ImageIO.read(new File("src/main/GraphicResources/rulebook/Rulebook_ITA_My-Shelfie-1.jpg"));
        images[1] = ImageIO.read(new File("src/main/GraphicResources/rulebook/Rulebook_ITA_My-Shelfie-2.jpg"));
        images[2] = ImageIO.read(new File("src/main/GraphicResources/Publisher material/Title 2000x618px.png"));
        this.rulebook1 = new JLabel((Icon) images[0]);
        this.rulebook2 = new JLabel((Icon) images[1]);

        this.scrollPane1 = new JScrollPane(rulebook1);
        this.scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane1.setAutoscrolls(true);
        this.scrollPane2 = new JScrollPane(rulebook2);
        this.scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane2.setAutoscrolls(true);

        this.enrolledPlayer = new JProgressBar();
        enrolledPlayer.setForeground(Color.RED);

        setLayout(new GridLayout(4,1));
        add(new JLabel((Icon) images[2]));
        add(rulebook1);
        add(rulebook2);
        add(enrolledPlayer);

        setVisible(true);

    }

    public JProgressBar getEnrolledPlayer(){
        return enrolledPlayer;
    }

}
