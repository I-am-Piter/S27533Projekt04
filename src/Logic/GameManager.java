package Logic;

import Events.TickEvent;
import Events.TickListener;

import java.util.ArrayList;

public class GameManager extends Thread{
    Gameboard gameboard;
    static int points = 0;
    ArrayList<TickListener> tickListeners;
    public GameManager(Gameboard gameboard){
        this.gameboard = gameboard;
        this.tickListeners = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            this.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        fireTick();
    }
    void addTickListener(TickListener tl){
        tickListeners.add(tl);
    }
    void rmTickListener(TickListener tl){
        tickListeners.remove(tl);
    }
    void fireTick(){
        TickEvent te = new TickEvent(this);
        for (TickListener tl:
             tickListeners) {
            tl.tick(te);
        }
    }
}
