package Creeps;

import FramePanels.CellLayered;
import UtilsPackage.MainTimer;
import UtilsPackage.Tickable;
import UtilsPackage.Visitor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by USER on 6/9/2017.
 */
public abstract class Creep extends JLabel implements Tickable {

    protected int hp;
    private boolean isActivated;//entered to the board
    protected double poisonIntensity;

    protected ImageIcon move1R;
    protected ImageIcon move2R;
    protected ImageIcon move1L;
    protected ImageIcon move2L;

    private boolean isPoisoned;
    private boolean isSlowDowned;
    private boolean isHurted;

    protected boolean isRight;

    protected Color colorHurt;

    protected int DELTA_TICKS_TO_MOVE;
    protected int lastTicks;
    protected int lastTicksHurted;
    protected int lastTicksSlowDowned;
    protected int lastTicksPoisoned;
    protected int sumTicksSlowDown;
    protected int sumTicksPoisoned;



    protected CellLayered floor;

    protected Creep(){
        isRight=true;
        hp=100;
        isActivated=false;
        poisonIntensity=0;
        isPoisoned=false;
        isSlowDowned=false;
        lastTicksPoisoned=0;
        lastTicksSlowDowned=0;
        DELTA_TICKS_TO_MOVE=0;
        lastTicks=0;
        lastTicksHurted=0;
        isHurted=false;
        colorHurt=null;
    }
    public CellLayered getFloor() {
		return floor;
	}
	public void setFloor(CellLayered floor) {
		this.floor = floor;
	}

    protected void updateImage(boolean isRight){
        if(isRight){
            if(getIcon()==move1L||getIcon()==move1R)
                setIcon(move2R);
            else
                setIcon(move1R);
        }
        else {
            if(getIcon()==move1L||getIcon()==move1R)
                setIcon(move2L);
            else
                setIcon(move1L);
        }

    }
    public void getHurt(int decreaseBy,Color color){
	    colorHurt=color;
	    lastTicksHurted=1;
        decreaseBy*=poisonIntensity;
        hp-=decreaseBy;
        if(hp<=0)
            die();
    }
    public void getPoisoned(int ticks){

        lastTicksPoisoned=ticks;
        sumTicksPoisoned=ticks;
        isPoisoned=true;
    }
    public void getSlowDowned(int ticks){
        lastTicksSlowDowned=ticks;
        sumTicksSlowDown=ticks;
        isSlowDowned=true;
    }

    private void move(){
    	floor.removeCreep(this);
    	if(floor.getNextInPath()!=null){
    		floor.getNextInPath().addCreep(this);
    		floor=floor.getNextInPath();
    		if(floor.isRight())
    		   updateImage(true);
    		else
    		    updateImage(false);
    	}
    	else
    	{
            MainTimer.getInstance().unRegister(this);
    		floor.updateCreepFinished(this);
            return;
    	}

    }
    private void die(){
        setActivated(false);
        floor.updateCreepDie(this);
        MainTimer.getInstance().unRegister(this);


    }//remove from timer



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillOval(3, 0,(int)(19*((double)hp/100)), 5);
        if(lastTicks<2&lastTicksHurted>0&colorHurt!=null) {
            lastTicksHurted--;
            g.setColor(colorHurt);
            g.fillOval(0, 0, 25, 25);
        }
        if(isPoisoned) {
            g.setColor(Color.green);
            g.fillOval(3, 15,(int)(19*((double)lastTicksPoisoned/sumTicksPoisoned)), 5);
        }
        if(isSlowDowned) {
            g.setColor(Color.gray);
            g.fillOval(3, 20,(int)(19*((double)lastTicksSlowDowned/sumTicksSlowDown)), 5);
        }
    }

    @Override
    public void tickHappened(int ticksPassed) {
        if(floor.isGameOver()) {
            MainTimer.getInstance().unRegister(this);
            return;
        }
    	lastTicks+=ticksPassed;
    	int mulDelta=1;
    	if(isSlowDowned) {
    	    lastTicksSlowDowned-=ticksPassed;
    	    if(lastTicksSlowDowned<0)
            {
                lastTicksSlowDowned=0;
                isSlowDowned=false;
            }
            mulDelta = 2;
        }
        if(isPoisoned) {
            lastTicksPoisoned -= ticksPassed;
            if(lastTicksPoisoned<0)
            {
                lastTicksPoisoned=0;
                isPoisoned=false;
            }
        }


    	if(lastTicks>=DELTA_TICKS_TO_MOVE*mulDelta&isActivated()){
    		move();
    		lastTicks=0;
    	}
    }

    public abstract void impact(Visitor v);

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
    	if(activated)
    		MainTimer.getInstance().register(this);
    	else
    		MainTimer.getInstance().unRegister(this);
        isActivated = activated;
    }

    public double getPoisonIntensity() {
        return poisonIntensity;
    }

    public void setPoisonIntensity(int poisonIntensity) {
        this.poisonIntensity = poisonIntensity;
    }

    public boolean isPoisoned() {
        return isPoisoned;
    }

    public void setPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }

    public boolean isSlowDowned() {
        return isSlowDowned;
    }

    public void setSlowDowned(boolean slowDowned) {
        isSlowDowned = slowDowned;
    }


    public int getLastTicksSlowDowned() {
        return lastTicksSlowDowned;
    }

    public void setLastTicksSlowDowned(int lastTicksSlowDowned) {
        this.lastTicksSlowDowned = lastTicksSlowDowned;
    }

    public int getLastTicksPoisoned() {
        return lastTicksPoisoned;
    }

    public void setLastTicksPoisoned(int lastTicksPoisoned) {
        this.lastTicksPoisoned = lastTicksPoisoned;
    }
}
