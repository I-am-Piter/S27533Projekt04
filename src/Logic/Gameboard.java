package Logic;

import Controller.GetData;
import Enums.Type;
import Events.*;
import Enums.Direction;
import java.util.ArrayList;

public class Gameboard implements DirectionEventListener, GetData, TickListener {
    GameManager gm;
    public int[][] gameboard;
    Direction currentDir;
    boolean gotDirection;
    Snake snake;
    Food food;
    public SaveManager sm;

    public Gameboard(int x, int y){
        gameboard = new int[y][x];
        gm = new GameManager();
        snake = new Snake(this);
        food = null;
        gm.addTickListener(snake);
        addFoodListener(snake);
        gotDirection = false;
        setGame();
    }
    public void setGame(){
        snake.body.add(new Head(currentDir,Type.HEAD,gameboard[0].length/2,gameboard.length/2));
        gameboard[gameboard.length/2][gameboard[0].length/2] = 1;
        food = new Food(((int)(Math.random()*gameboard[0].length)),((int)(Math.random()*gameboard.length)));
        updateTab();
    }
    private boolean ateFood(){
        if((food.x == snake.body.get(0).x)&&(food.y == snake.body.get(0).y)){
            boolean goodValues = false;
            int foodx = 0;
            int foody = 0;
            while(!goodValues) {
                foodx = (int)(Math.random() * gameboard[0].length);
                foody = (int)(Math.random() * gameboard.length);
                for (Segment s : snake.body) {
                    if (!(s.x == foodx && s.y == foody)) {
                        goodValues = true;
                    }
                }
            }
            food.x = foodx;
            food.y = foody;
            return true;
        }
        return false;
    }

    static ArrayList<ScoreEventListener> scoreEventList = new ArrayList<>();
    public static void addScoreEventListener(ScoreEventListener sel){
        scoreEventList.add(sel);
    }
    public static void rmScoreEventListener(ScoreEventListener sel){
        scoreEventList.remove(sel);
    }
    private void fireScoreChanged(int size) {
        ScoreEvent se = new ScoreEvent(this,size);
        for (ScoreEventListener sel:scoreEventList) {
            sel.ScoreChanged(se);
        }
    }

    public void updateTab(){
        if(ateFood()){
            fireAteFood();
            fireScoreChanged(snake.body.size()-1);
        }
        if(!hitSmth()) {
            for (int i = 0; i < gameboard.length; i++) {
                for (int j = 0; j < gameboard[i].length; j++) {
                    gameboard[i][j] = 0;
                }
            }
            for (Segment s : snake.body) {
                gameboard[s.y][s.x] = s.type.getId();
            }
                gameboard[food.y][food.x] = Type.FOOD.getId();
            fireDataChanged();
        }else{
            fireHitSmth();
            System.out.println("u hit something");
            sm.addScore(snake.body.size()-1);
        }
    }


    private boolean hitSmth() {
        Segment s = snake.body.get(0);
        if(((s.x >= gameboard[0].length)||(s.x < 0))||((s.y >= gameboard.length)||(s.y < 0))){
            return true;
        }
//        for(Segment x: snake.body){
//            if(!x.equals(s)) {
//                if ((x.x == s.x)||(x.y == s.y)){
//                    return true;
//                }
//            }
//        }
        for (int i = 1; i < snake.body.size(); i++) {
            Segment tmp = snake.body.get(i);
            if(s.x == tmp.x && s.y == tmp.y){
                return true;
            }
        }
        return false;
    }

    static ArrayList<HitSomethingListener> hitListeners = new ArrayList<>();
    public static void addHitListener(HitSomethingListener hsl){
        hitListeners.add(hsl);
    }
    public static void rmHitListener(HitSomethingListener hsl) {
        hitListeners.remove(hsl);
    }
    private void fireHitSmth() {
        HitSomethingEvent hse = new HitSomethingEvent(this);
        for(HitSomethingListener hsl:hitListeners){
            hsl.hitsomething(hse);
        }
    }

    static ArrayList<AteFoodListener> foodListeners = new ArrayList<>();
    public static void addFoodListener(AteFoodListener afl){
        foodListeners.add(afl);
    }
    public static void rmFoodListener(AteFoodListener afl){
        foodListeners.remove(afl);
    }
    void fireAteFood() {
        AteFoodEvent afe = new AteFoodEvent(this);
        for(AteFoodListener afl:foodListeners){
            afl.ateFood(afe);
        }
    }

    static ArrayList<DataChangedListener> datalisteners = new ArrayList<>();
    public static void addDataChangedListener(DataChangedListener dcl){
        datalisteners.add(dcl);
    }
    public static void rmDataChangedListener(DataChangedListener dcl){
        datalisteners.remove(dcl);
    }
    void fireDataChanged(){
        DataChangedEvent dce = new DataChangedEvent(this,gameboard);
        for(DataChangedListener dcl:datalisteners){
            dcl.dataChanged(dce);
        }
    }
    @Override
    public void directionChanged(DirectionEvent de) {
        Direction tmpDir = de.getDirection();
        currentDir = de.getDirection();
        if(SaveManager.hasNick) {
            if (gotDirection == false) {
                gm.start();
                gotDirection = true;
            }
        }else{
            System.out.println("Brak nicku");
        }
    }

    @Override
    public int[][] getTable() {
        return gameboard;
    }
    public Snake getSnake(){
        return snake;
    }

    @Override
    public void tick(TickEvent te) {
        updateTab();
    }
    public GameManager getGm(){
        return gm;
    }
}
