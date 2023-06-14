package Logic;

import Events.TickEvent;
import Events.TickListener;

import java.util.ArrayList;

public class GameManager extends Thread{
    static int points = 0;
    static ArrayList<TickListener> tickListeners;
    public GameManager(){
        this.tickListeners = new ArrayList<>();
    }

    @Override
    public void run() {
        while(true) {
            try {
                this.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            fireTick();
            System.out.println("tick");
        }
    }
    public static void addTickListener(TickListener tl){
        tickListeners.add(tl);
    }
    public static void rmTickListener(TickListener tl){
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
