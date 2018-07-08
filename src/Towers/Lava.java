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
public class Lava extends Tower {
    private static ImageIcon image= null;
    public Lava(CellLayered[][] allCells, int x, int y){
        super(allCells,x,y);
        DELTA_TICKS_TO_ATTACK=4;
        RADIOS_TO_CREEP=1;
        isKashan=true;
        color= Color.pink;

        if(image==null) {
            String src1="/towers/1.png";
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

        creep.getHurt(10,color);
    }
    @Override
    public void impact(Goli creep)
    {
        creep.getHurt(15,color);
    }

    @Override
    public void impact(Mike creep) {

        creep.getHurt(15,color);
    }

    @Override
    public void impact(Naji creep) {

        creep.getHurt(15,color);
    }
}