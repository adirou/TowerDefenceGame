package FramePanels;

import Towers.*;
import UtilsPackage.TowersLeft;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 6/10/2017.
 */
public class ChooseTower extends JPanel implements ActionListener {
    private TowersLeft towersLeft;
    private static ImageIcon arrow= null;
    private static ImageIcon dino= null;
    private static ImageIcon goko= null;
    private static ImageIcon lava= null;
    private static ImageIcon magic= null;
    private static ImageIcon poison= null;
    private static ImageIcon sam= null;
    private JButton arrowB;
    private JButton samB;
    private JButton gokoB;
    private JButton dinoB;
    private JButton magicB;
    private JButton poisonB;
    private JButton lavaB;

    private CellLayered cell;
    public ChooseTower(TowersLeft towersLeft, CellLayered cell){
        super();

        if(arrow==null) {
            String arrowSrc="/towers/2.png";
            String dinoSrc="/towers/dino-1.png";
            String gokoSrc="/towers/6.png";
            String lavaSrc="/towers/1.png";
            String magicSrc="/towers/4.png";
            String poisonSrc="/towers/3.png";
            String samSrc="/towers/5.png";

            arrow = new ImageIcon(getClass().getResource(arrowSrc));
            dino = new ImageIcon(getClass().getResource(dinoSrc));
            goko = new ImageIcon(getClass().getResource(gokoSrc));
            lava = new ImageIcon(getClass().getResource((lavaSrc)));
            magic = new ImageIcon(getClass().getResource((magicSrc)));
            poison = new ImageIcon(getClass().getResource((poisonSrc)));
            sam= new ImageIcon(getClass().getResource((samSrc)));

            arrow=new ImageIcon( arrow.getImage().getScaledInstance(46,60, Image.SCALE_SMOOTH));
            dino=new ImageIcon( dino.getImage().getScaledInstance(60,40, Image.SCALE_SMOOTH));
            goko=new ImageIcon( goko.getImage().getScaledInstance(46,60, Image.SCALE_SMOOTH));
            lava=new ImageIcon( lava.getImage().getScaledInstance(46,60, Image.SCALE_SMOOTH));
            magic=new ImageIcon(magic.getImage().getScaledInstance(46,60, Image.SCALE_SMOOTH));
            poison=new ImageIcon( poison.getImage().getScaledInstance(46,60, Image.SCALE_SMOOTH));
            sam=new ImageIcon( sam.getImage().getScaledInstance(46,60, Image.SCALE_SMOOTH));

        }
        this.towersLeft=towersLeft;
        setPreferredSize(new Dimension(250,300));
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));


        this.cell=cell;
        JPanel panel= new JPanel();
        panel.setLayout(new GridLayout(4,2));


        arrowB=new JButton(""+towersLeft.getArrow(),arrow);
        if(towersLeft.getArrow()==0)
            arrowB.setEnabled(false);
        arrowB.addActionListener(this);

        dinoB=new JButton(""+towersLeft.getDino(),dino);
        if(towersLeft.getDino()==0)
            dinoB.setEnabled(false);
        dinoB.addActionListener(this);

        samB=new JButton(""+towersLeft.getSam(),sam);
        if(towersLeft.getSam()==0)
            samB.setEnabled(false);
        samB.addActionListener(this);

        gokoB=new JButton(""+towersLeft.getGoko(),goko);
        gokoB.addActionListener(this);
        if(towersLeft.getGoko()==0)
            gokoB.setEnabled(false);

        magicB=new JButton(""+towersLeft.getMagic(),magic);
        magicB.addActionListener(this);
        if(towersLeft.getMagic()==0)
            magicB.setEnabled(false);

        poisonB=new JButton(""+towersLeft.getPoison(),poison);
        poisonB.addActionListener(this);
        if(towersLeft.getPoison()==0)
            poisonB.setEnabled(false);

        lavaB=new JButton(""+towersLeft.getLava(),lava);
        lavaB.addActionListener(this);
        if(towersLeft.getLava()==0)
            lavaB.setEnabled(false);

        JButton exitB = new JButton("X");
        exitB.addActionListener(this);

        panel.add(arrowB);
        panel.add(dinoB);
        panel.add(samB);
        panel.add(magicB);
        panel.add(poisonB);
        panel.add(gokoB);
        panel.add(lavaB);
        panel.add(exitB);
        add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==lavaB) {
            towersLeft.decreaseLava();
            cell.addTower(new Lava(cell.getAllCells(),cell.get_X(),cell.get_Y()));
        }
        if(e.getSource()==arrowB&&towersLeft.getArrow()>0) {
            towersLeft.decreaseArrow();
            cell.addTower(new Arrow(cell.getAllCells(),cell.get_X(),cell.get_Y()));
        }
        if(e.getSource()==samB&&towersLeft.getSam()>0) {
            towersLeft.decreaseSam();
            cell.addTower(new Sam(cell.getAllCells(),cell.get_X(),cell.get_Y()));
        }
        if(e.getSource()==magicB&&towersLeft.getMagic()>0) {
            towersLeft.decreaseMagic();
            cell.addTower(new Magic(cell.getAllCells(),cell.get_X(),cell.get_Y()));
        }
        if(e.getSource()==gokoB&&towersLeft.getGoko()>0) {
            towersLeft.decreaseGoko();
            cell.addTower(new Goko(cell.getAllCells(),cell.get_X(),cell.get_Y()));
        }
        if(e.getSource()==dinoB&&towersLeft.getDino()>0) {
            towersLeft.decreaseDino();
            cell.addTower(new Dino(cell.getAllCells(),cell.get_X(),cell.get_Y()));
        }
        if(e.getSource()==poisonB&&towersLeft.getPoison()>0) {
            towersLeft.decreasePoison();
            cell.addTower(new Poison(cell.getAllCells(),cell.get_X(),cell.get_Y()));
        }

        cell.getPopup().hide();
        towersLeft.setPopUp(false);


    }
}
