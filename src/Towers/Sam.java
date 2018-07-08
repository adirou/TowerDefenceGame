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
public class Sam extends Tower {
    private static ImageIcon image= null;
    public Sam(CellLayered[][] allCells, int x, int y){
        super(allCells,x,y);

        DELTA_TICKS_TO_ATTACK=2;
        RADIOS_TO_CREEP=2;
        color= Color.cyan;
        isKashan=true;

        if(image==null) {
            String src1="/towers/6.png";
            image = new ImageIcon(getClass().getResource(src1));
            image=new ImageIcon( image.getImage().getScaledInstance(22,30, Image.SCALE_SMOOTH));
        }
        super.image=image;
        loadImage();
        MakeRangeList();
        MakeAroundList(RADIOS_TO_CREEP);
        MainTimer.getInstance().register(this);
    }
    @Override
    public void impact(Abir creep) {
        creep.getSlowDowned(24);
    }
    @Override
    public void impact(Goli creep)
    {
        creep.getSlowDowned(12);

    }

    @Override
    public void impact(Mike creep) {

        creep.getSlowDowned(12);
        creep.getHurt(10,color);
    }

    @Override
    public void impact(Naji creep) {

        creep.getSlowDowned(12);
    }
}