package UtilsPackage;

/**
 * Created by USER on 6/10/2017.
 */
public class TowersLeft {
    private boolean isPopUp;
    private int arrow;
    private int dino;
    private int goko;
    private int lava;
    private int magic;
    private int poison;
    private int sam;
    public TowersLeft(){
        isPopUp=false;
        arrow=3;
        lava=3;
        magic=3;
        poison=3;
        sam=1;
        dino=1;
        goko=1;
    }
    public void decreaseArrow(){
        arrow--;
    }
    public void decreaseDino(){
        dino--;
    }
    public void decreaseLava(){
        lava--;
    }
    public void decreaseMagic(){
        magic--;
    }
    public void decreaseSam(){
        sam--;
    }
    public void decreaseGoko(){
        goko--;
    }
    public void decreasePoison(){
        poison--;
    }

    public int getArrow() {
        return arrow;
    }

    public int getDino() {
        return dino;
    }

    public int getGoko() {
        return goko;
    }

    public int getLava() {
        return lava;
    }

    public int getMagic() {
        return magic;
    }

    public int getPoison() {
        return poison;
    }

    public int getSam() {
        return sam;
    }

    public boolean isPopUp() {
        return isPopUp;
    }

    public void setPopUp(boolean popUp) {
        isPopUp = popUp;
    }
}
