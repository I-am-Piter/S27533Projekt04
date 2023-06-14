import Logic.GameManager;
import Logic.Gameboard;
import Logic.SaveManager;
import Visuals.GamePanel;
import Visuals.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;

public class S27533Projekt04 {
    public static void main(String[] args) {
        Gameboard gb = new Gameboard(25,16);
        gb.setGame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mf = new MainFrame();
                Gameboard.addDataChangedListener(mf.getGp());
            }

        });
        SaveManager sm = new SaveManager("path");
        MainFrame.addNickListener(sm);
        GamePanel.setDataInterface(gb);
        MainFrame.addDirectionEventListener(gb);
        MainFrame.addDirectionEventListener(gb.getSnake());
        GameManager.addTickListener(gb);
    }

}
