package Logic;

import Events.NickEvent;
import Events.NickEventListener;
import Events.ShowTop10Event;
import Events.ShowTop10EventListener;
import Exceptions.TooLongNickException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class SaveManager implements NickEventListener {
    static ArrayList<ScoreEntry> scores;
    static String path;
    static File file;
    static String nick;
    public static boolean hasNick;
    public SaveManager(String path){
        scores = new ArrayList<>();
        this.path = path;
        hasNick = false;
        file = new File(path);
    }

    @Override
    public void nickChanged(NickEvent ne) {
        this.nick = ne.getNick();
        hasNick = true;
    }

    public static void readFile(){
        if(file.canRead()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                while (fis.available() > 0) {
                    byte nameLength = (byte) fis.read();
                    byte[] nameBuffer = new byte[nameLength];
                    if (fis.read(nameBuffer) != nameLength) {
                        System.out.println("Błąd odczytu nazwy gracza");
                        break;
                    }
                    String name = new String(nameBuffer);
                    int points =
                            (fis.read() << 24) |
                                    (fis.read() << 16) |
                                    (fis.read() << 8) |
                                    fis.read();
                    scores.add(new ScoreEntry(name,points));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveToFile(){
        best10();
        showEntries();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            for (ScoreEntry se : scores) {
                byte nameLength = (byte) se.nick.length();
                fos.write(nameLength);
                fos.write(se.nick.getBytes());
                fos.write((se.points >> 24)& 0xFF);
                fos.write((se.points >> 16)& 0xFF);
                fos.write((se.points >> 8)& 0xFF);
                fos.write(se.points & 0xFF);
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void best10(){
        Collections.sort(scores,
                (s1, s2) -> {return s2.points-s1.points;}
        );
        ArrayList<ScoreEntry> tmp = new ArrayList<>();
        for(int i = 0;i<10&&i<scores.size();i++) {
            tmp.add(scores.get(i));
        }
        scores = tmp;
    }
    public void showEntries(){
        int[] points = new int[scores.size()];
        String[] nicks = new String[scores.size()];
        int index = 0;

        for (ScoreEntry se:scores) {
            points[index] = se.points;
            nicks[index] = se.nick;
            index++;
        }

        fireShowEntries(points,nicks);
    }
    static ArrayList<ShowTop10EventListener> showEntriesListeners = new ArrayList<>();
    public static void addShowEntriesListener(ShowTop10EventListener st1el){
        showEntriesListeners.add(st1el);
    }
    public static void rmShowEntriesListener(ShowTop10EventListener st1el){
        showEntriesListeners.remove(st1el);
    }
    private void fireShowEntries(int[] points, String[] nicks) {
        ShowTop10Event st1e = new ShowTop10Event(this,nicks,points);
        for (ShowTop10EventListener st1el:showEntriesListeners) {
            st1el.showTop10(st1e);
        }
    }

    public void addScore(int score){
        ScoreEntry se = new ScoreEntry(nick,score);
        scores.add(se);
        System.out.println("Score for " + se.nick + " was " + se.points);
        saveToFile();
    }
}
