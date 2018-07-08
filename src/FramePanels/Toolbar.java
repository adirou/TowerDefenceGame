package FramePanels;

import UtilsPackage.MainTimer;
import UtilsPackage.Tickable;
import UtilsPackage.UserDetails;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Created by USER on 6/9/2017.
 */
public class Toolbar extends JToolBar implements Tickable,ActionListener{
    private int DELTA_TICKS_TO_UPDATE_CLOCK;
    private int lastTicksForClock;
    private int lastHp;
    private UserDetails userDetails;
    private BoardPanel board;
    private JLabel time;
    private JLabel hp;
    private JLabel wave;
    private JButton fastForward;
    private JButton startWave;
    private JButton menu;

    public Toolbar(UserDetails ud,BoardPanel board) {
        super();
        this.userDetails=ud;
        setFloatable(false);
        this.board= board;
        this.DELTA_TICKS_TO_UPDATE_CLOCK = 4;
        lastTicksForClock=0;
        lastHp=userDetails.getHpLeft();

        time=new JLabel("00:00");
        hp=new JLabel("HP: 10");
        wave= new JLabel("Wave: 1");
        fastForward =new JButton("Speed");
        fastForward.addActionListener(this);
        startWave=new JButton("Start");
        startWave.addActionListener(this);
        menu=new JButton("Menu");
        menu.addActionListener(this);
        MainTimer.getInstance().register(this);


        add(hp);
        add(Box.createRigidArea(new Dimension(10,10)));
        add(wave);
        add(Box.createRigidArea(new Dimension(50,10)));
        add(startWave);
        add(Box.createRigidArea(new Dimension(10,10)));
        add(fastForward);
        add(Box.createRigidArea(new Dimension(400,10)));
        add(time);
        add(Box.createRigidArea(new Dimension(60,10)));
        add(menu);
        MainTimer.getInstance().resumeTimer();

    }

    @Override

    public void tickHappened(int ticksPassed) {

        lastTicksForClock+=ticksPassed;
        if(lastTicksForClock>=DELTA_TICKS_TO_UPDATE_CLOCK) {
            userDetails.setSeconds(userDetails.getSeconds()+1);
            time.setText(userDetails.getTimeString());
            lastTicksForClock=0;
        }
        if(lastHp!=userDetails.getHpLeft())
        {
            lastHp=userDetails.getHpLeft();
            hp.setText("HP:"+ lastHp);
        }

    }

	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menu) {
            MainTimer.getInstance().stopTimerAndClear();

            MainWindow mainWindow = MainWindow.getInstance();
           mainWindow.remove(getParent());
            mainWindow.setSize(800, 800);
            StartPanel startP = new StartPanel();
            mainWindow.add(startP);
            mainWindow.revalidate();
            mainWindow.repaint();
            mainWindow.pack();
            setVisible(false);
        }
        if(userDetails.isGameOver()) {
            MainTimer.getInstance().unRegister(this);
            return;
        }
		if(e.getSource()==startWave) {
            MainTimer.getInstance().resumeTimer();
            board.startWave();
            wave.setText("Wave:"+userDetails.getWave());
        }
        if(e.getSource()==fastForward) {
            MainTimer.getInstance().switchSpeed();
        }
		
	}
}
