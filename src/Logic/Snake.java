package Logic;

import Enums.Direction;
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
        int tmpx2 = 0;
        int tmpy2 = 0;
        Segment tmp = null;
        for (int i = 1; i < body.size(); i++) {
            tmp = body.get(i);
            tmpx2 = tmp.x;
            tmpy2 = tmp.y;
            tmp.x = tmpx;
            tmp.y = tmpy;
            tmpx = tmpx2;
            tmpy = tmpy2;
        }
    }

    @Override
    public void tick(TickEvent te) {
        move();
    }

    @Override
    public void directionChanged(DirectionEvent de) {
        if(isOk(de.getDirection())) {
            body.get(0).setDirection(de.getDirection());
        }
    }
    private boolean isOk(Direction dir) {
        Direction currentDir = body.get(0).getDirection();
        if(currentDir == null){
            return true;
        }
        switch (currentDir){
            case DOWN, UP -> {
                if((dir == Direction.LEFT || dir == Direction.RIGHT)){
                    return true;
                }
            }
            case RIGHT,LEFT -> {
                if((dir == Direction.DOWN || dir == Direction.UP)){
                    return true;
                }
            }
        }
        return false;
    }
}
