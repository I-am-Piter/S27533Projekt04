package Visuals;

import Controller.GetData;
import Enums.Direction;
import Events.DirectionEvent;
import Events.DirectionEventListener;
import Events.NickEvent;
import Events.NickEventListener;
import Logic.SaveManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    GetData data;
    JTable jTable;
    GamePanel gp;
    public MainFrame() {
        {
            JFrame nickFrame = new JFrame();
            JPanel nickPanel = new JPanel();
            nickFrame.add(nickPanel);
            JTextField nickTextField = new JTextField();
            JButton nickButton = new JButton("done");

            nickPanel.setLayout(new BorderLayout());
            nickPanel.add(nickTextField,BorderLayout.CENTER);
            nickPanel.add(nickButton,BorderLayout.PAGE_END);

            nickFrame.setVisible(true);
            nickFrame.setSize(500,200);

            nickButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nickFrame.setVisible(false);
                    fireNickSubmited(nickTextField.getText());
                }
            });
        }

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    fireDirectionChange(Direction.UP);
                }
                else if (keyCode == KeyEvent.VK_DOWN) {
                    fireDirectionChange(Direction.DOWN);
                }
                else if (keyCode == KeyEvent.VK_LEFT) {
                    fireDirectionChange(Direction.LEFT);
                }
                else if (keyCode == KeyEvent.VK_RIGHT) {
                    fireDirectionChange(Direction.RIGHT);
                }
            }
        });
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(SaveManager.hasNick) {
                    requestFocus();
                }
            }
        });

        this.gp = new GamePanel();
        this.setLayout(new BorderLayout());
        this.add(gp,BorderLayout.PAGE_END);

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280,720);
    }
    static ArrayList<DirectionEventListener> directionEventList = new ArrayList<>();
    public static void addDirectionEventListener(DirectionEventListener del){
        directionEventList.add(del);
    }
    public static void rmDirectionEventListener(DirectionEventListener del){
        directionEventList.remove(del);
    }
    private void fireDirectionChange(Direction direction) {
        DirectionEvent directionEvent = new DirectionEvent(direction,this);
        for (DirectionEventListener del:directionEventList) {
            del.directionChanged(directionEvent);
        }
    }
    static ArrayList<NickEventListener> nickEventListeners = new ArrayList<>();
    public static void addNickListener(NickEventListener nel){
        nickEventListeners.add(nel);
    }
    public static void rmNickListener(NickEventListener nel){
        nickEventListeners.remove(nel);
    }
    public void fireNickSubmited(String nick){
        NickEvent ne = new NickEvent(this,nick);
        for (NickEventListener nel:nickEventListeners) {
            nel.nickChanged(ne);
        }
    }
    public GamePanel getGp(){
        return gp;
    }
}
