package VIEW.GraphicObjects;

import javax.swing.*;

public class NumberOfPlayerChoice extends JFrame{

    JOptionPane nPlayersChoice;
    Object[] options = {2, 3, 4};
    private int choice;

    public NumberOfPlayerChoice(){
        super("SCELTA NUMERO DI GIOCATORI");
        this.choice = JOptionPane.showOptionDialog(this,"Scegliere il numero di giocatori: ",
                "Inserimento Numero Di Giocatori", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public int getChoice() {
        return choice;
    }

    public Object[] getOptions() {
        return options;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}
