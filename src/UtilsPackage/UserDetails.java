package UtilsPackage;

/**
 * Created by USER on 6/9/2017.
 */
public class UserDetails {
    private String fullNameS;
    private String hpLeftS;
    private String timeS;
    private String pointsS ;
    private String levelS ;
    private int numberOfKillings;
    private int numberOfCreepsFinished;
    private int wave;
    private int hpLeft;
    private int seconds;
    private int level;
    private int leftCreepsCurrLvl;
    private boolean isWon;
    private boolean isGameOver;

    private TowersLeft towersLeft;

    public UserDetails(String level,String fullName, String hpLeft, String time, String points) {
        this.levelS=level;
        this.fullNameS = fullName;
        this.hpLeftS = hpLeft;
        this.timeS = time;
        this.pointsS = points;
    }

    public UserDetails(int level) {
        this.level=level;
        hpLeft=10;
        wave=0;
        seconds=0;
        numberOfKillings=0;
        numberOfCreepsFinished=0;
        isWon=false;
        isGameOver=false;
        towersLeft= new TowersLeft();

    }

    public String toString(){
        return levelS+"| "+fullNameS + " points: "+pointsS+" hp-left: "+hpLeftS+" time: "+timeS;
    }
    public int getLevel() {
        return level;
    }

    public String getLevelS() {
        return levelS;
    }

    public void setLevelS(String levelS) {
        this.levelS = levelS;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFullNameS() {
        return fullNameS;
    }

    public void setFullNameS(String fullNameS) {
        this.fullNameS = fullNameS;
    }

    public int getNumberOfKillings() {
        return numberOfKillings;
    }

    public void setNumberOfKillings(int numberOfKillings) {
        this.numberOfKillings = numberOfKillings;
    }

    public int getNumberOfCreepsFinished() {
        return numberOfCreepsFinished;
    }

    public void setNumberOfCreepsFinished(int numberOfCreepsFinished) {
        this.numberOfCreepsFinished = numberOfCreepsFinished;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public int getHpLeft() {
        return hpLeft;
    }

    public void setHpLeft(int hpLeft) {
        this.hpLeft = hpLeft;
        hpLeftS=""+hpLeft;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        if(seconds/60<10)
            timeS="0";
        timeS+=""+seconds/60+":";
        if(seconds%60<10)
            timeS+="0";
        timeS+=""+seconds%60;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public String getFullNameString() {
        return fullNameS;
    }

    public void setFullNameString(String fullName) {
        this.fullNameS = fullName;
    }

    public String getHpLeftString() {
        return hpLeftS;
    }

    public void setHpLeftString(String hpLeft) {
        hpLeftS = hpLeft;
    }

    public String getTimeString() {
        return timeS;
    }

    public void setTimeString(String time) {
        this.timeS = time;
    }

    public String getPointsString() {
        return pointsS;
    }

    public void setPointsString(String points) {
        this.pointsS = points;
    }

    public TowersLeft getTowersLeft() {
        return towersLeft;
    }

    public void setTowersLeft(TowersLeft towersLeft) {
        this.towersLeft = towersLeft;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public int getLeftCreepsCurrLvl() {
        return leftCreepsCurrLvl;
    }

    public void setLeftCreepsCurrLvl(int leftCreepsCurrLvl) {
        this.leftCreepsCurrLvl = leftCreepsCurrLvl;
    }
}
