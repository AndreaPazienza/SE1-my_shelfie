package VIEW.GraphicObjects;

import javax.swing.*;

public class WaitingRoom {

    private JFrame window;
    private JScrollBar scrollBar;
    private JProgressBar enrolledPlayer;

    public WaitingRoom(){
        this.window = new JFrame();
        this.scrollBar = new JScrollBar();
        this.enrolledPlayer = new JProgressBar();
        //settings vari

        setLayout(new );
        //inizializzazione Frame con il regolamento
    }

    public JFrame getWindow(){
        return window;
    }
    public JProgressBar getEnrolledPlayer(){
        return enrolledPlayer;
    }

    public JScrollBar getScrollBar() {
        return scrollBar;
    }
}
