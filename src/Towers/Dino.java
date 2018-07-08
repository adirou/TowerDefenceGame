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
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by USER on 6/9/2017.
 */
public class Dino extends Tower   {
    private static ImageIcon imageR;
    private static ImageIcon image2R;
    private static ImageIcon imageL;
    private static ImageIcon image2L;
    private static Image imageQ;
    private int DELTA_TICKS_TO_MOVE;
    private LinkedList<CellLayered> optionToMove;
    private LinkedList<CellLayered> backAround;
    private int lastTickMove;
    private int leftMovesInDirection;
    private int direction;
    private boolean isRight;
    public Dino(CellLayered[][] allCells, int x, int y) {
        super(allCells,x,y);

        leftMovesInDirection=0;
        isRight=true;
        DELTA_TICKS_TO_ATTACK=4;
        DELTA_TICKS_TO_MOVE=4;
        RADIOS_TO_CREEP=2;
        color=Color.darkGray;

        lastTickMove=0;
        if(imageR==null) {

            String src1="/towers/dino-1.png";
            String src2="/towers/dino-2.png";
            String src1R="/towers/dino-1R.png";
            String src2R="/towers/dino-2R.png";
            imageL = new ImageIcon(getClass().getResource(src1));
            imageL=new ImageIcon( imageL.getImage().getScaledInstance(25,20, Image.SCALE_SMOOTH));

            image2L = new ImageIcon(getClass().getResource(src2));
            image2L=new ImageIcon( image2L.getImage().getScaledInstance(25,20, Image.SCALE_SMOOTH));
            imageR = new ImageIcon(getClass().getResource(src1R));
            imageR=new ImageIcon( imageR.getImage().getScaledInstance(25,20, Image.SCALE_SMOOTH));
            image2R = new ImageIcon(getClass().getResource(src2R));
            image2R=new ImageIcon( image2R.getImage().getScaledInstance(25,20, Image.SCALE_SMOOTH));
        }

        MakeRangeList();
        MakeAroundList(RADIOS_TO_CREEP);
        MainTimer.getInstance().register(this);
        MakeMoveList();
        updateImage(isRight);
    }
    protected void MakeMoveList(){
        LinkedList<CellLayered> listAround=new LinkedList<CellLayered>();
        if(_x-1>1)
            listAround.addLast(allCells[_y][_x-1]);
        if(_x+1<allCells[0].length-2)
            listAround.addLast(allCells[_y][_x+1]);
        if(_y-1>1)
            listAround.addLast(allCells[_y-1][_x]);
        if(_y+1<allCells.length-2)
            listAround.addLast(allCells[_y+1][_x]);

        optionToMove=listAround;
    }
    protected void updateImage(boolean isRight){
        if(isRight){
            if(getIcon()==imageL||getIcon()==imageR)
                setIcon(image2R);
            else
                setIcon(imageR);
        }
        else {
            if(getIcon()==imageL||getIcon()==imageR)
                setIcon(image2L);
            else
                setIcon(imageL);
        }
        //getParent().getGraphics().drawImage(imageL.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH),25*_x,25*_y,this);
        repaint();

    }
    //


    protected void move(){
        allCells[_y][_x].removeDino(this);
        Random r=new Random();
        if(leftMovesInDirection==0||direction>=optionToMove.size()) {
            leftMovesInDirection =4+r.nextInt(4);
            direction = r.nextInt(optionToMove.size());
        }
        else {
            leftMovesInDirection--;
        }
        optionToMove.get(direction).addDino(this);

        if(_x>optionToMove.get(direction).get_X())
            updateImage(false);
        else
            updateImage(true);

        _x=optionToMove.get(direction).get_X();
        _y=optionToMove.get(direction).get_Y();
        MakeAroundList(RADIOS_TO_CREEP);
        MakeRangeList();
        MakeMoveList();
    }

    @Override
    protected void markNearbyCells(){
        super.markNearbyCells();
            backAround=cellsAround;

    }
    @Override
    protected void unMarkNearbyCells(){
        for (CellLayered cell : backAround) {
            if(!cell.isPath()) {
                cell.setOpaque(false);
                cell.getParent().repaint();
            }
            else
                cell.setBackgroundGame();

        }


    }
    @Override
    public void tickHappened(int ticksPassed) {
        super.tickHappened(ticksPassed);
        lastTickMove+=ticksPassed;
        if(lastTickMove>=DELTA_TICKS_TO_MOVE) {
            lastTickMove = 0;
            move();
        }

    }

    @Override
    public void impact(Abir creep) {

        creep.getHurt(15,color);
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
