package Events;

import java.util.EventObject;

public class DataChangedEvent extends EventObject {
    int[][] gameboard;
    public DataChangedEvent(Object source,int[][] gameboard) {
        super(source);
        this.gameboard = gameboard;
    }
    public int[][] getData(){
        return gameboard;
    }
}
