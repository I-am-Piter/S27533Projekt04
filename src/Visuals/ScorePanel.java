package Visuals;

import Events.ScoreEvent;
import Events.ScoreEventListener;
import Logic.Gameboard;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements ScoreEventListener {
    MainFrame mf;
    int score;
    ScorePanel(MainFrame mf){
        this.mf = mf;
        score = 0;
        this.setPreferredSize(new Dimension(900,100));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(Integer.toString(score), this.getWidth()/2, 75);
    }

    @Override
    public void ScoreChanged(ScoreEvent se) {
        score = se.getScore();
        this.repaint();
    }
}
