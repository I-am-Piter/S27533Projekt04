import Logic.GameManager;
import Logic.Gameboard;

public class S27533Projekt04 {
    public static void main(String[] args) {
        Gameboard gb = new Gameboard(25,16);
        gb.setGame();
        GameManager gm = new GameManager(gb);
    }
}
