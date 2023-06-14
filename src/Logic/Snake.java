package Logic;

import Enums.Type;
import Events.*;

import java.util.ArrayList;

public class Snake
        implements AteFoodListener, TickListener, DirectionEventListener {
    Gameboard gameboard;
    ArrayList<Segment> body;
    public Snake(Gameboard gameboard){
        this.body = new ArrayList<>();
        this.gameboard = gameboard;
    }

    int lastx = 0;
    int lasty = 0;
    @Override
    public void ateFood(AteFoodEvent afe) {
        Segment toAdd = new Segment(Type.BODY,lastx,lasty);
        body.add(toAdd);
    }
    public void move(){
        lastx = body.get(body.size()-1).x;
        lasty = body.get(body.size()-1).y;
//        for(Segment s:body){
//            switch(s.direction){
//                case LEFT -> s.x--;
//                case RIGHT -> s.x++;
//                case DOWN -> s.y++;
//                case UP -> s.y--;
//            }
//        }
        Head tmpHead = (Head) body.get(0);
        int tmpx = tmpHead.x;
        int tmpy = tmpHead.y;
        switch(tmpHead.direction){
            case LEFT -> tmpHead.x--;
            case RIGHT -> tmpHead.x++;
            case DOWN -> tmpHead.y++;
            case UP -> tmpHead.y--;
        }
        Segment tmp;
        for (int i = 1; i < body.size(); i++) {
            tmp = body.get(i);
            tmpx = tmp.x;
            tmpy = tmp.y;
        }
    }

    @Override
    public void tick(TickEvent te) {
        move();
    }

    @Override
    public void directionChanged(DirectionEvent de) {
        body.get(0).setDirection(de.getDirection());
    }
}
