package VIEW.GraphicObjects;
import MODEL.Player;
import javax.swing.*;
import java.awt.*;

public class EndGame extends JFrame {

    private JTextArea textArea1;
    private JTextArea textArea2;

    public EndGame() {

        setTitle("Ranking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Creazione della prima area di testo
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        add(scrollPane1);

        // Creazione della seconda area di testo
        textArea2 = new JTextArea();
        textArea2.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(textArea2);
        add(scrollPane2);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setTexts(Player ranking[]) {

        String rankingText = "";
        String winnerText = "";

        for(int i = 0; i < ranking.length; i ++) {
            rankingText = rankingText + i+1 + ": " + ranking[i].getNickname() + " \n punti totalizzati: " + ranking[i].getScore() + " \n ordine nel giro: " + ranking[i].getOrderInTurn() + "\n\n";
        }

        winnerText = "Congratulazioni " + ranking[0].getNickname() + "hai vinto questa partita!";

        textArea1.setText(rankingText);
        textArea2.setText(winnerText);
        Font font = textArea2.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        textArea2.setFont(boldFont);
    }
}
