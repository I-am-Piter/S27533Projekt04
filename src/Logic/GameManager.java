package Logic;

import Events.HitSomethingEvent;
import Events.HitSomethingListener;
import Events.TickEvent;
import Events.TickListener;

import java.util.ArrayList;

public class GameManager extends Thread implements HitSomethingListener {
    static int points = 0;
    boolean stillWork;
    static ArrayList<TickListener> tickListeners;
    public GameManager(){
        this.tickListeners = new ArrayList<>();
        this.stillWork = true;
    }

    @Override
    public void run() {
        while(stillWork) {
            try {
                this.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            fireTick();
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

    @Override
    public void hitsomething(HitSomethingEvent hse) {
        stillWork = false;
    }
}
