package Towers;

import Creeps.Abir;
import Creeps.Goli;
import Creeps.Mike;
import Creeps.Naji;
import FramePanels.CellLayered;
import UtilsPackage.MainTimer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by USER on 6/9/2017.
 */
public class Arrow extends Tower {
    private static ImageIcon image= null;

    public Arrow(CellLayered[][] allCells,int x,int y) {
        super(allCells,x,y);
        DELTA_TICKS_TO_ATTACK=2;
        RADIOS_TO_CREEP=2;
        color=Color.white;
        if(image==null) {
            String src1="/towers/2.png";
            image = new ImageIcon(getClass().getResource(src1));
            image=new ImageIcon( image.getImage().getScaledInstance(27,30, Image.SCALE_SMOOTH));
        }
        super.imageQ=imageQ;
        super.image=image;
        loadImage();
        MakeRangeList();
        MakeAroundList(RADIOS_TO_CREEP);
        MainTimer.getInstance().register(this);

    }
    @Override
    public void impact(Abir creep) {

        creep.getHurt(0,color);
    }
    @Override
    public void impact(Goli creep)
    {
         creep.getHurt(15,color);
    }

    @Override
    public void impact(Mike creep) {

        creep.getHurt(30,color);
    }

    @Override
    public void impact(Naji creep) {

        creep.getHurt(30,color);
    }
}
