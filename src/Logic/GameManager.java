package Logic;

public class GameManager extends Thread{
    Gameboard gameboard;
    static int points = 0;
    public GameManager(Gameboard gameboard){
        this.gameboard = gameboard;
    }

    @Override
    public void run() {
        try {
            this.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        gameboard.move();
    }
}
