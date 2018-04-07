package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import datamap1.*;

/**
 *
 * @author nhatt_000
 */
public class MapData {

    private  ArrayList<Tile> arraytile = new ArrayList<Tile>();
    private  ArrayList<Tile> arraytile2 = new ArrayList<Tile>();
    private  ArrayList<Tile> arraytile3 = new ArrayList<Tile>();
    private Robot robot1, robot2, robot3;

    public MapData(int map) throws IOException {
        if (map == 1) {
            robot1 = new Robot();
            loadmap("map1.txt", 1, robot1);
        } else if (map == 2) {
            robot2 = new Robot();
            loadmap("map2.txt", 2, robot2);
        } else if (map == 3) {
            robot3 = new Robot();
            loadmap("map3.txt", 3, robot3);
        }
    }

    public void loadmap(String map, int c, Robot robot) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        int width = 0;
        int height = 0;
        String path = "/Maps/" + map;
        InputStream in = getClass().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(in)
        );
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                reader.close();
                break;
            }
            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }
        height = lines.size();
        for (int i = 0; i < height; i++) {
            String line = (String) lines.get(i);
            for (int j = 0; j < width; j++) {
                if (j < line.length()) {
                    char ch = line.charAt(j);
                    if (c == 1) {
                        Tile t = new Tile(j, i, Character.getNumericValue(ch), robot1);
                        arraytile.add(t);
                    } else if (c == 2) {
                        Tile t = new Tile(j, i, Character.getNumericValue(ch), robot2);
                        arraytile2.add(t);
                    } else if (c == 3) {
                        Tile t = new Tile(j, i, Character.getNumericValue(ch), robot3);
                        arraytile3.add(t);
                    }
                }
            }
        }

    }

    public Robot getRobot1() {
        return robot1;
    }

    public Robot getRobot2() {
        return robot2;
    }

    public  ArrayList<Tile> getArraytile() {
        return arraytile;
    }

    public  ArrayList<Tile> getArraytile2() {
        return arraytile2;
    }

    public  ArrayList<Tile> getArraytile3() {
        return arraytile3;
    }

    public Robot getRobot3() {
        return robot3;
    }
    
}
