package UtilsPackage;

/**
 * Created by USER on 6/8/2017.
 */
public class LevelMap {
    private int  _level;
    private Byte[][][] _origMap ;
    private int _startPosY;
    private int _endPosY;


    public LevelMap(Byte[][][] map,int lvl){
        _level=lvl;
        _origMap=map;
        boolean flag=true;
        for (int i = 0; i < getHeight()&&flag; i++)
            if (getDirection(0, i)[0] != 0 || getDirection(0, i)[1] != 0) {
                _startPosY = i;
                flag=false;
            }
            flag=true;
        for (int i = 0; i < getHeight()&&flag; i++)
            if (getDirection(0, i)[0] != 0 || getDirection(0, i)[1] != 0) {
                _endPosY = i;
                flag = false;
            }
    }

    public boolean isOnMap(int x,int y){
        return (x>=0&x<getWidth()&y>=0&y<getHeight());
    }

    public Byte[] getDirection(int x,int y){
        if(isOnMap(x,y))
            return _origMap[y][x];
        else return null;
    }
    public boolean isGrass(int x,int y){
        if(isOnMap(x,y))
            return (_origMap[y][x][0] == 0 && _origMap[y][x][1] == 0);
        throw new IllegalArgumentException();
    }

    public int getStartPos() {
        return _startPosY;
    }
    public int getEndPos() {
        return  _endPosY;
    }

    public int getHeight(){return _origMap.length;}
    public int getWidth(){return _origMap[0].length;}

    public int getLevel() {
        return _level;
    }

    public void setLevel(int _level) {
        this._level = _level;
    }
}