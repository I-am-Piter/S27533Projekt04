package Events;

import java.util.EventObject;

public class DirectionEvent extends EventObject {

    Direction direction;

    DirectionEvent(Direction direction, Object source){
        super(source);
        this.direction = direction;
    }
}
