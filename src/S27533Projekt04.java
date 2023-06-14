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
        SaveManager sm = new SaveManager("file.bin");
        SaveManager.readFile();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mf = new MainFrame();
                Gameboard.addDataChangedListener(mf.getGp());
                Gameboard.addScoreEventListener(mf.getSp());
                SaveManager.addShowEntriesListener(mf);
            }

        });
        gb.sm = sm;
        MainFrame.addNickListener(sm);
        GamePanel.setDataInterface(gb);
        MainFrame.addDirectionEventListener(gb);
        MainFrame.addDirectionEventListener(gb.getSnake());
        GameManager.addTickListener(gb);
        Gameboard.addHitListener(gb.getGm());
    }
}
