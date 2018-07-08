package Towers;

import Creeps.Abir;
import Creeps.Goli;
import Creeps.Mike;
import Creeps.Naji;
import FramePanels.CellLayered;
import UtilsPackage.MainTimer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by USER on 6/9/2017.
 */
public class Goko extends Tower {
    private static ImageIcon image= null;
    private int lastHurts;
    private int H;
    private int DELTA_HURTS;
    public Goko(CellLayered[][] allCells, int x, int y){
        super(allCells,x,y);

        DELTA_HURTS=10;
        DELTA_TICKS_TO_ATTACK=4;
        RADIOS_TO_CREEP=2;
        H=1;
        color= Color.blue;

        lastHurts=0;
        if(image==null) {
            String src1="/towers/5.png";
            image = new ImageIcon(getClass().getResource(src1));
            image=new ImageIcon( image.getImage().getScaledInstance(22,30, Image.SCALE_SMOOTH));
        }
        super.image=image;
        loadImage();
        MakeRangeList();
        MakeAroundList(RADIOS_TO_CREEP);
        MainTimer.getInstance().register(this);
    }

    private void incrementHurtsTick(){
        lastHurts++;
        if(lastHurts==DELTA_HURTS) {
            H++;
            lastHurts=0;
        }
    }

    @Override
    public void impact(Goli creep)
    {
        creep.getHurt(10*H,color);
        incrementHurtsTick();
    }

    @Override
    public void impact(Mike creep) {

        creep.getHurt(5*H,color);
        incrementHurtsTick();
    }
    @Override
    public void impact(Abir creep) {
        creep.getHurt(7*H,color);
        incrementHurtsTick();
    }
    @Override
    public void impact(Naji creep) {

        creep.getHurt(5*H,color);
        incrementHurtsTick();
    }

}