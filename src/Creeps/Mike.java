package Creeps;
import UtilsPackage.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by USER on 6/9/2017.
 */
public class Mike extends Creep {
    private static ImageIcon image1= null;
    private static ImageIcon image2= null;
    private static ImageIcon image1L= null;
    private static ImageIcon image2L= null;
    public Mike() {
        super();

        if (image1 == null) {
            String src1 = "/creeps/mike-1.png";
            String src2 = "/creeps/mike-2.png";
            String src1L="/creeps/mike-1L.png";
            String src2L="/creeps/mike-2L.png";
            image1 = new ImageIcon(getClass().getResource((src1)));
            image2 = new ImageIcon(getClass().getResource((src2)));
            image1L = new ImageIcon(getClass().getResource((src1L)));
            image2L = new ImageIcon(getClass().getResource((src2L)));
        }
        move1R=new ImageIcon(image1.getImage().getScaledInstance(22,25, Image.SCALE_SMOOTH));
        move2R=new ImageIcon(image2.getImage().getScaledInstance(22,25, Image.SCALE_SMOOTH));
        move1L=new ImageIcon(image1L.getImage().getScaledInstance(22,25, Image.SCALE_SMOOTH));
        move2L=new ImageIcon(image2L.getImage().getScaledInstance(22,25, Image.SCALE_SMOOTH));
        updateImage(true);

        poisonIntensity = 1;
        DELTA_TICKS_TO_MOVE = 4;


    }
    public void impact(Visitor v){
        v.impact(this);

    }

}