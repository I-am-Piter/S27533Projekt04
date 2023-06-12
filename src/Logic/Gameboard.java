package Logic;

import Events.Direction;

public class Gameboard {
    public int[][] gameboard;
    Direction currentDir;

    public Gameboard(int x, int y){
        gameboard = new int[y][x];
    }

    public void setGame(){
        gameboard[gameboard.length/2][gameboard[0].length/2] = 2;

    }

    public void move() {
        //TODO
    }
}
