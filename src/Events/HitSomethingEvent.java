package Events;

import java.util.EventObject;

public class HitSomethingEvent extends EventObject {
    public HitSomethingEvent(Object source) {
        super(source);
    }
}
