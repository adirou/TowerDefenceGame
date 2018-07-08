package UtilsPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by USER on 6/9/2017.
 */
//singletons
public class MainTimer implements ActionListener{
    private ArrayList<Tickable> tickables;
    public static MainTimer instance=null;
    private int ticks;
    private boolean isFastForward;
   // private boolean isActivated;
    private Timer timer;
    //private int speed;

    public static MainTimer getInstance() {
        if(instance==null)
            instance=new MainTimer();
        return instance;
    }
    private MainTimer(){
       // isActivated=true;
        isFastForward=false;
        tickables=new ArrayList<>();
        timer = new Timer(250,this);
        timer.setRepeats(true);


    }
    public void register(Tickable t){
         tickables.add(t);
    }
    public void unRegister(Tickable t){
         tickables.remove(t);
    }


    public void notifyEveryone(int ticksPassed){
    	ArrayList<Tickable> tickables=(ArrayList<Tickable>) this.tickables.clone();
    	
        for( Tickable t : tickables)
            t.tickHappened(ticksPassed);
    }

    public void stopTimer(){
        timer.stop();
        //isActivated=false;

    }
    public void stopTimerAndClear(){
        timer.stop();
        tickables.clear();

    }

    public void resumeTimer(){
        if(!timer.isRunning())
            timer.start();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int ticks=0;
        if(isFastForward)
            ticks++;
        ticks++;
        notifyEveryone(ticks);
    }

    public boolean isFastForward() {
        return isFastForward;
    }

    public void switchSpeed() {
          isFastForward=!isFastForward;
    }


}
