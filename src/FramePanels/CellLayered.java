package FramePanels;

import Creeps.Abir;
import Creeps.Creep;
import Towers.Arrow;
import Towers.Dino;
import Towers.Tower;
import UtilsPackage.TowersLeft;
import UtilsPackage.UserDetails;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * Created by USER on 6/10/2017.
 */
public class CellLayered extends JPanel implements ActionListener,MouseListener {
    private boolean isPath;
    private boolean isTower;
    private boolean isRight;
    private int _x;
    private int _y;
    private UserDetails userDetails;
    private CellLayered nextInPath;
    private CellLayered[][] allCells;
    private LinkedList<Creep> creeps;
    private JButton chooseTower;


    private Popup popup;

    public CellLayered(CellLayered[][] allCells,int x, int y, UserDetails ud) {
        super();

        setLayout(new BorderLayout());
        isRight=true;
        creeps = new LinkedList<>();
        setBackground(new Color(0,0,0,0));
        this.allCells=allCells;
        setPreferredSize(new Dimension(25,25));

        setBorder(null);

        this.userDetails=ud;
        isPath = false;
        isTower = false;

        nextInPath = null;
        _x = x;
        _y = y;

       addMouseListener(this);
    }

    public void addTower(Tower tower){
        removeAll();
        isTower=true;
        tower.setLocation(getX(),getY());
        tower.setSize(25,25);
        add(tower,BorderLayout.CENTER);
        getParent().revalidate();
        getParent().repaint();
    }
    public void addCreep(Creep c){
        creeps.add(c);
        add(c);
        System.out.println("cM");
       revalidate();
       repaint();

    }
    public void removeCreep(Creep c){
        creeps.remove(c);
        remove(c);
        revalidate();
        repaint();
    }
    public void addDino(Dino d){
        add(d);
        revalidate();
        repaint();
    }
    public void removeDino(Dino d){
        remove(d);
        revalidate();
        repaint();
        getParent().repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PopupFactory factory;
        if(!isPath&!isTower&!userDetails.getTowersLeft().isPopUp()) {
            factory = PopupFactory.getSharedInstance();
            popup = factory.getPopup(getParent(), new ChooseTower(userDetails.getTowersLeft(), this), e.getXOnScreen(), e.getYOnScreen());
            userDetails.getTowersLeft().setPopUp(true);
            popup.show();
        }
    }

    public void updateCreepDie(Creep c){
        System.out.println("uD");

        remove(c);
        revalidate();
        repaint();

        userDetails.setNumberOfKillings(userDetails.getNumberOfKillings() + 1);
        userDetails.setLeftCreepsCurrLvl(userDetails.getLeftCreepsCurrLvl() - 1);
        System.out.println(userDetails.getLeftCreepsCurrLvl());
    }
    public void updateCreepFinished(Creep c){
        userDetails.setLeftCreepsCurrLvl(userDetails.getLeftCreepsCurrLvl()-1);
        removeCreep(c);
    	if(userDetails.getHpLeft()==1)
    		userDetails.setGameOver(true);
    	else
    		userDetails.setHpLeft(userDetails.getHpLeft()-1);
    }
    public boolean isGameOver(){
        return userDetails.isGameOver();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    public void setBackgroundGame(Color color){
        setBackground(color);
        revalidate();
        repaint();
    }

    public void setBackgroundGame(){
        if(isPath())
            setBackground(Color.orange);
        else {
            setBackground(null);
        }
        revalidate();
        repaint();
    }


    public void setPath(boolean path) {
        isPath = path;
        if(path)
            setBackground(Color.orange);

    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public boolean isPath() {
        return isPath;
    }

    public boolean isTower() {
        return isTower;
    }

    public void setTower(boolean tower) {
        isTower = tower;
    }


    public int get_X() {
        return _x;
    }

    public void set_X(int x) {
        _x = x;
    }

    public int get_Y() {
        return _y;
    }

    public void set_Y(int y) {
        _y = y;
    }

    public CellLayered getNextInPath() {
        return nextInPath;
    }

    public void setNextInPath(CellLayered nextInPath) {
        this.nextInPath = nextInPath;
    }

    public LinkedList<Creep> getCreeps() {
        return creeps;
    }

    public void setCreeps(LinkedList<Creep> creeps) {
        this.creeps = creeps;
    }

    public JButton getChooseTower() {
        return chooseTower;
    }

    public void setChooseTower(JButton chooseTower) {
        this.chooseTower = chooseTower;
    }

    public Popup getPopup() {
        return popup;
    }

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    public CellLayered[][] getAllCells() {
        return allCells;
    }

}
