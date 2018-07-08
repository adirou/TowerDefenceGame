package Towers;

import Creeps.*;
import FramePanels.CellLayered;
import UtilsPackage.MainTimer;
import UtilsPackage.Tickable;
import UtilsPackage.Visitor;
import javafx.scene.control.Cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by USER on 6/9/2017.
 */
public abstract class Tower  extends JLabel implements Tickable, Visitor, MouseListener {

    protected Creep target;//null if is'not attacking
    protected ImageIcon image;
    protected Image imageQ;
    protected boolean isKashan;
    protected int RADIOS_TO_CREEP;
    protected int DELTA_TICKS_TO_ATTACK;
    protected Color color;//time between attacks
    protected int lastTicks;

    protected LinkedList<CellLayered> cellsAround;
    protected LinkedList<CellLayered> cellsPath;
    protected CellLayered[][] allCells;//good for dino, and to checks the nearby cells
    protected int _x;
    protected int _y;

    public Tower(CellLayered[][] allCells, int x , int y){
        this.allCells=allCells;
        lastTicks=0;
        _x=x;
        _y=y;
        setBackground(null);
        addMouseListener(this);
    }


    //function to implement
    protected boolean isTargetOnRange() {
        if(target!=null&&target.isActivated())
            for(CellLayered cell : cellsPath)
                if(cell.getCreeps().contains(target))
                    return true;
        target=null;
        return false;
    }
    //calculate the distance and checks if in range;
    protected void findAndAttackInRange(){

        for(CellLayered cell : cellsPath) {
            LinkedList<Creep> creepsClone = (LinkedList<Creep>) cell.getCreeps().clone();
            for (Creep creep : cell.getCreeps())
                if (!isKashan) {
                    target = creep;
                    impact(creep);
                    return;
                } else {
                    impact(creep);
                    if (!creep.isActivated())
                        creepsClone.remove(creep);
                }
            cell.setCreeps(creepsClone);
        }

    }
    public void impact(Creep creep){
        if(creep.isActivated()) {
            System.out.println("imp");
           // creep.getFloor().setBackgroundGame(color);
            //creep.setForeground(new Color(20,30,50,100));
            creep.impact(this);

        }
    }
    protected void MakeRangeList(){
        LinkedList<CellLayered> listPath=new LinkedList<CellLayered>();
        for(int i=_y-RADIOS_TO_CREEP;i<=_y+RADIOS_TO_CREEP;i++)
            for(int j=_x-RADIOS_TO_CREEP;j<=_x+RADIOS_TO_CREEP;j++)
                if(i>=1&i<= allCells.length-2&&
                        j>=1&&j< allCells[0].length-2&&
                             allCells[i][j].isPath())
                    listPath.addLast(allCells[i][j]);
        cellsPath=listPath;
    }
    protected void MakeAroundList(int radios){
        LinkedList<CellLayered> listAround=new LinkedList<CellLayered>();
        for(int i=_y-radios;i<=_y+radios;i++)
            for(int j=_x-radios;j<=_x+radios;j++)
                if(i>=1&i<= allCells.length-2&&
                        j>=1&j< allCells[0].length-2&!(j==_x&i==_y))

                    listAround.addLast(allCells[i][j]);
        cellsAround=listAround;
    }

    protected void markNearbyCells(){
        for (CellLayered cell : cellsAround) {
            cell.setOpaque(true);
            cell.setBackgroundGame(Color.YELLOW);
        }

    }
    protected void unMarkNearbyCells(){
        for (CellLayered cell : cellsAround) {
            if(!cell.isPath()) {
                cell.setOpaque(false);
                cell.getParent().repaint();
            }
            else
               cell.setBackgroundGame();

        }


    }


    public void loadImage(){
        setIcon(image);
    }

    @Override
    public void tickHappened(int ticksPassed) {
        if(allCells[_y][_x].isGameOver()) {
            MainTimer.getInstance().unRegister(this);
            return;
        }

        lastTicks+=ticksPassed;
        if(lastTicks>=DELTA_TICKS_TO_ATTACK)
        {
            lastTicks=0;
            if(!isKashan){
                if(isTargetOnRange())
                   impact(target);
                else {
                    findAndAttackInRange();
                }
            }
            else {
                findAndAttackInRange();
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        unMarkNearbyCells();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        markNearbyCells();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setClip(-25/2,-25/2,50,50);
        //g.fillOval(-15/2,-15/2,30,30);
        g.drawImage(imageQ,-25/2,-25/2,50,40,this);

    }
}
