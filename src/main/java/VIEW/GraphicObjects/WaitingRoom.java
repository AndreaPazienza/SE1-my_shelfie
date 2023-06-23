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

    public WaitingRoom(){
        super("IN ATTESA CHE ARRIVINO TUTTI I GIOCATORI");
        ImageIcon[] images = new ImageIcon[3];
        ImageIcon[] scaledImages = new ImageIcon[3];
        images[0] = new ImageIcon("src/main/GraphicResources/rulebook/Rulebook_ITA_My-Shelfie-1.jpg");
        images[1] = new ImageIcon("src/main/GraphicResources/rulebook/Rulebook_ITA_My-Shelfie-2.jpg");
        images[2] = new ImageIcon("src/main/GraphicResources/Publisher material/Title 2000x618px.png");
        for(int i = 0; i < images.length; i++){
            Image scaledImage = images[i].getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            scaledImages[i] = new ImageIcon(scaledImage);
        }
        this.rulebook1 = new JLabel(scaledImages[0]);
        rulebook1.setHorizontalAlignment(JLabel.CENTER);
        rulebook1.setVerticalAlignment(JLabel.CENTER);
        rulebook1.setVerticalTextPosition(JLabel.CENTER);
        rulebook1.setHorizontalTextPosition(JLabel.CENTER);
        rulebook1.setIconTextGap(0);
        this.rulebook2 = new JLabel(scaledImages[1]);
        rulebook2.setHorizontalAlignment(JLabel.CENTER);
        rulebook2.setVerticalAlignment(JLabel.CENTER);
        rulebook2.setVerticalTextPosition(JLabel.CENTER);
        rulebook2.setHorizontalTextPosition(JLabel.CENTER);
        rulebook2.setIconTextGap(0);
        JLabel gameName = new JLabel(scaledImages[2]);
        gameName.setHorizontalAlignment(JLabel.CENTER);
        gameName.setVerticalAlignment(JLabel.CENTER);
        gameName.setVerticalTextPosition(JLabel.CENTER);
        gameName.setHorizontalTextPosition(JLabel.CENTER);
        gameName.setIconTextGap(0);

        /*this.scrollPane1 = new JScrollPane(rulebook1);
        this.scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane1.setAutoscrolls(true);
        this.scrollPane2 = new JScrollPane(rulebook2);
        this.scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane2.setAutoscrolls(true);*/

        this.enrolledPlayer = new JProgressBar();
        enrolledPlayer.setForeground(Color.RED);

        setLayout(new GridLayout(4,1));
        add(gameName);
        /*rulebook1.setPreferredSize(new Dimension(500,500));
        rulebook2.setPreferredSize(new Dimension(500,500));
        enrolledPlayer.setPreferredSize(new Dimension(200, 100));*/
        add(rulebook1);
        add(rulebook2);
        add(enrolledPlayer);
        pack();
        setVisible(true);
    }

    public JProgressBar getEnrolledPlayer(){
        return enrolledPlayer;
    }

}
