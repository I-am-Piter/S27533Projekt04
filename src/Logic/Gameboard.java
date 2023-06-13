package Logic;

import Enums.Type;
import Events.AteFoodEvent;
import Events.AteFoodListener;
import Events.DataChangedEvent;
import Events.DataChangedListener;
import Enums.Direction;

import java.util.ArrayList;

public class Gameboard {
    public int[][] gameboard;
    Direction currentDir;
    int lastx;
    int lasty;
    int headx;
    int heady;
    int count;
    Snake snake;
    ArrayList<Food> food;

    public Gameboard(int x, int y){
        gameboard = new int[y][x];
        snake = new Snake();
        food = new ArrayList<>();
    }

    public void setGame(){
        snake.body.add(new Segment(currentDir, Type.HEAD,gameboard[0].length/2,gameboard.length/2));
        gameboard[gameboard.length/2][gameboard[0].length/2] = 1;
        food.add(new Food(((int)(Math.random()*gameboard[0].length)),((int)(Math.random()*gameboard.length))));
    }
    public void setIntTab(Snake snake){
        for (Segment s:
             snake.body) {
            gameboard[s.y][s.x] = s.type.getId();
        }
    }

    ArrayList<DataChangedListener> listeners = new ArrayList<>();
    void addDataChangedListener(DataChangedListener dcl){
        listeners.add(dcl);
    }
    void rmDataChangedListener(DataChangedListener dcl){
        listeners.remove(dcl);
    }
    void fireDataChanged(){
        DataChangedEvent dce = new DataChangedEvent(this,gameboard);
        for(DataChangedListener dcl:listeners){
            dcl.dataChanged(dce);
        }
    }
}
