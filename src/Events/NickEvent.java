package Events;

import java.util.EventObject;

public class NickEvent extends EventObject {
    String nick;
    public NickEvent(Object source,String nick) {
        super(source);
        this.nick = nick;
    }
    public String getNick(){
        return nick;
    }
}
