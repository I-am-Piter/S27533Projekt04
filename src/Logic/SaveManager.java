package Logic;

import Events.NickEvent;
import Events.NickEventListener;

public class SaveManager implements NickEventListener {
    String path;
    String nick;
    public static boolean hasNick;
    public SaveManager(String path){
        this.path = path;
        hasNick = false;
    }

    @Override
    public void nickChanged(NickEvent ne) {
        this.nick = ne.getNick();
        hasNick = true;
    }
}
