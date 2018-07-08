package FramePanels;

import Creeps.*;

import UtilsPackage.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by USER on 6/9/2017.
 */
public class BoardPanel extends JPanel implements Tickable  {
    private UserDetails userDetails;

    private boolean isWaving;
    private Queue<Creep> creeps;

    private CellLayered[][] cells;
    private LevelMap map;

    private int lastTicks;
    private int DELTA_TICKS_TO_ENTER_CREEP;

    private Image background;

    public BoardPanel(UserDetails ud,LevelMap levelMap)throws IOException{

        super(new GridLayout(levelMap.getHeight(),levelMap.getWidth()));
       // setBackground(Color.DARK_GRAY);
        background=ImageIO.read(getClass().getResource("/backgrounds/soil3.jpg"));

        background=background.getScaledInstance(800,800,Image.SCALE_AREA_AVERAGING);
        map=levelMap;
        userDetails=ud;
        isWaving =false;
        creeps=null;
        cells=new CellLayered[levelMap.getHeight()+2][levelMap.getWidth()+2];
        lastTicks=0;
        DELTA_TICKS_TO_ENTER_CREEP=2;

        buildBoard();
        MainTimer.getInstance().register(this);

        
    }

    //functions

    private void buildBoard(){
        buildEmptyCells();

        buildPath();
    }
    //use those
    private void buildEmptyCells(){//null at the borders

           for(int i=1;i<cells.length-1;i++) {
               for (int j = 1; j < cells[0].length - 1; j++) {
                   try {
                       //JOptionPane.showMessageDialog(null,"hh");
                       cells[i][j] = new CellLayered(cells, j, i, userDetails);

                   }
                   catch (Exception ex)
                   {
                       JOptionPane.showMessageDialog(null,"hh");
                   }
                   add(cells[i][j]);
               }
           }


    }//border of null objects

    private void buildPath() {
        int xNext = 1;
        int yNext = map.getStartPos() + 1;
        CellLayered before = cells[yNext][xNext];
        while(true)
        {
        Byte[] xy = map.getDirection(xNext-1, yNext-1);
        xNext = xNext + xy[0];
        yNext = yNext + xy[1];

        CellLayered next = cells[yNext][xNext];

        if(xy[0]==-1)
            before.setRight(false);
        else
            before.setRight(true);
        before.setPath(true);
        if(next==null)
            break;

        before.setNextInPath(next);
        before = next;
        }
    }
    private void generateCreeps(){
		creeps=new LinkedBlockingQueue<>() ;
		int creepCount=(int)Math.pow(2,userDetails.getWave()-1);
		int creepsLeft[]=new int[4];
		creepsLeft[0]=creepCount;
		creepsLeft[1]=creepCount;
		creepsLeft[2]=creepCount;
		creepsLeft[3]=creepCount;
		Random rand=new Random();
		for(int i=0;i<creepCount*4;i++){
			int j=rand.nextInt(4);
			while(creepsLeft[j]==0)
				j=rand.nextInt(4);
			creepsLeft[j]--;
			switch (j)
			{
			case 0: creeps.add(new Abir());break;
			case 1: creeps.add(new Goli());break;
			case 2: creeps.add(new Mike());break;
			case 3: creeps.add(new Naji());break;
			}
		}

    }
    public void startWave(){
        if(userDetails.getLeftCreepsCurrLvl()<=0) {
            userDetails.setWave(userDetails.getWave()+1);
            generateCreeps();
            isWaving = true;
            userDetails.setLeftCreepsCurrLvl(creeps.size());
        }
    }
    public void gameOver(){
        MainWindow mainWindow=MainWindow.getInstance();
        MainTimer.getInstance().stopTimerAndClear();
        userDetails.setPointsString(""+(((20-userDetails.getHpLeft())*10000)/userDetails.getSeconds()));
        EndPanel endPanel;
        try {
            endPanel = new EndPanel(userDetails);
            mainWindow.remove(getParent());
            mainWindow.add(endPanel);
            mainWindow.pack();
        }
        catch (Exception ex){
            System.out.println("error"+ ex.toString());
        }

    }

    @Override
    public void tickHappened(int ticksPassed) {

        if(userDetails.getLeftCreepsCurrLvl()==0){
            if(userDetails.getWave()>=5) {
                if(userDetails.getHpLeft()!=0)
                   userDetails.setWon(true);
                gameOver();
                return;
            }
            else
                MainTimer.getInstance().stopTimer();
        }

        lastTicks+=ticksPassed;

        if(isWaving&&lastTicks>=DELTA_TICKS_TO_ENTER_CREEP){
            lastTicks=0;
            if(creeps.isEmpty())
            	isWaving=false;
            else{
            	
                CellLayered floor = cells[map.getStartPos() + 1][1];
                Creep creep = creeps.remove();
                creep.setActivated(true);
                creep.setFloor(floor);
                floor.addCreep(creep);
            }
            	
        }
        if(userDetails.isGameOver()) {
            gameOver();

        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage( background, 0, 0, this); // see javadoc for more info on the parameters
    }

}
