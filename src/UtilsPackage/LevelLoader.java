package UtilsPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by USER on 6/8/2017.
 */

public class LevelLoader {

    /** contains the initial board state of all the levels  */
    private Vector<LevelMap> _levels;

    public LevelLoader() {
        _levels = new Vector<>();
    }

    /**
     * Loads all the levels to the internal levels buffer
     * @param levelsFile the name of the file contains the levels
     * @return true if success
     * @throws IOException if there is any error with the file
     */
    public boolean load(String levelsFile) throws IOException {
        _levels.clear();

        BufferedReader br = new BufferedReader(new FileReader(levelsFile));
        String line = null;
        Byte[][][] map = null;
        int w=0;
        int h=0;
        int row = 0;
        while ((line = br.readLine()) != null) {

            // end of level
            if (line.trim().isEmpty()) {
                if (map!=null){
                    _levels.add(new LevelMap(map,_levels.size()));
                    map = null;
                    row=0;
                }
                continue;
            }

            // board size
            if (line.trim().startsWith("w")){
                w = Integer.valueOf(line.trim().substring(1));
                continue;
            }
            if (line.trim().startsWith("h")){
                h = Integer.valueOf(line.trim().substring(1));
                continue;
            }

            // comment
            if (line.startsWith(";")) {
                continue;
            }

            // start of level definition
            if (null == map && h>0 && w>0){
                map = new Byte[h][w][2];
                row = 0;
            }

            // regular board line

            //Byte[][] rowBytes = new Byte[w][2];
            int col = 0;
            StringTokenizer stToken = new StringTokenizer(line, "[]");
            while (stToken.hasMoreTokens()) {
                String str = stToken.nextToken();
                String[] xys = str.split(",");
                Byte[] xy = new Byte[2];
                xy[0] = Byte.parseByte(xys[0]);
                xy[1] = Byte.parseByte(xys[1]);
                if(map!=null)
                   map[row][col]=xy;
                else {
                    br.close();
                    return false;
                }
             //   rowBytes[col] = xy;
                col++;
            }
            row++;
        }
        if (null != map){
            _levels.add(new LevelMap(map,_levels.size()));
            map = null;
        }
        br.close();
        return true;
    }

    /**
     * @return the number of levels available
     */
    public int getLevelsCount() {

        return _levels.size();
    }

    /**
     * @param index - the level number
     * @return the initial state of level number {@code index}
     *
     * TODO - is recommended to create a deep copy of the array.
     */
    public LevelMap get(int index) {
        return _levels.get(index);
    }

    /**
     * create {@code Cell} instance from {@code char} representation
     *
     * @return the {@code Cell} object
     */

}
