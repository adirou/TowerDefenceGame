package FramePanels;

import UtilsPackage.UserDetails;
import UtilsPackage.WinnerTableLoader;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by USER on 6/9/2017.
 */
public class EndPanel extends JPanel implements ActionListener {
    private UserDetails userDetails;
    private JLabel isWon;
    private JLabel level;
    private JLabel creepsDied;
    private JLabel timePassed;
    private JLabel points;
    private JLabel lastWave;
    private JPanel registerPanel;
    private JButton send;
    private JTextField name;
    private JLabel enterName;
    private JButton start;
    private JButton winnerTable;
    private String nameS;

    private Image background;


    public EndPanel(UserDetails ud) throws IOException {

        super();
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.userDetails=ud;
        setPreferredSize(new Dimension(800,800));

        nameS="";

        userDetails=ud;

        if(userDetails.isWon())
            isWon = new JLabel("Congratulation You Won!!!");
        else
            isWon=new JLabel("You loss, try again");
        isWon.setAlignmentX(CENTER_ALIGNMENT);
        isWon.setFont(new Font("Georgia", Font.BOLD ,20));

        creepsDied =new JLabel("Creeps Died: "+userDetails.getNumberOfKillings());
        creepsDied.setAlignmentX(CENTER_ALIGNMENT);

        timePassed = new JLabel("Time lapsed:" +ud.getTimeString());
        timePassed.setAlignmentX(CENTER_ALIGNMENT);

        points=new JLabel("Points:"+userDetails.getPointsString());
        points.setAlignmentX(CENTER_ALIGNMENT);

        level=new JLabel("Level:"+(userDetails.getLevel()+1));
        level.setAlignmentX(CENTER_ALIGNMENT);

        lastWave=new JLabel("lastWave: "+ userDetails.getWave());
        lastWave.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(10,200)));
        add(isWon);
        add(Box.createRigidArea(new Dimension(10,20)));
        add(level);
        add(Box.createRigidArea(new Dimension(10,10)));
        add(lastWave);
        add(Box.createRigidArea(new Dimension(10,10)));
        add(creepsDied);
        add(Box.createRigidArea(new Dimension(10,10)));
        add(timePassed);
        add(Box.createRigidArea(new Dimension(10,10)));
        add(points);
        add(Box.createRigidArea(new Dimension(10,10)));
        if(userDetails.isWon())
        {
            registerPanel=new JPanel();
            registerPanel.setLayout(new FlowLayout());
            registerPanel.setSize(new Dimension(300,60));
            enterName=new JLabel("Enter To Winners Table:");
            enterName.setAlignmentX(CENTER_ALIGNMENT);
            name=new JTextField();
            name.setPreferredSize(new Dimension(100,30));
            send= new JButton("Send");
            send.addActionListener(this);
            registerPanel.add(enterName);
            add(Box.createRigidArea(new Dimension(20,10)));
            registerPanel.add(name);
            add(Box.createRigidArea(new Dimension(20,10)));
            registerPanel.add(send);
            add(registerPanel);
        }
        JPanel buttons=new JPanel(new FlowLayout());
        start= new JButton("go to Menu");
        start.addActionListener(this);
        winnerTable= new JButton("Winner Table");
        winnerTable.addActionListener(this);
        buttons.add(start);
        buttons.add(Box.createRigidArea(new Dimension(20,10)));
        buttons.add(winnerTable);
        add(buttons);

    }

    public void writeWinner() throws IOException {
        WinnerTableLoader winnerLoader= new WinnerTableLoader();
        winnerLoader.writeWinner(userDetails,"winners.txt");
    }
    @Override
    public void actionPerformed(ActionEvent e)  {


        if(e.getSource()==send) {
            nameS=name.getText();
            if(nameS.equals(""))
                return;
            nameS=name.getText();
            nameS= nameS.replace(' ' ,'_');
            userDetails.setFullNameS(nameS);
            try {
                writeWinner();
            }
            catch (Exception ex){

            }
        }
        if(e.getSource()==send||e.getSource()==winnerTable){
            MainWindow mainWindow = MainWindow.getInstance();
            mainWindow.remove(this);
            mainWindow.add(new WinnersPanel(userDetails.getLevel()+1));
            mainWindow.pack();
        }
        if(e.getSource()==start){
            MainWindow mainWindow = MainWindow.getInstance();
            mainWindow.remove(this);
            mainWindow.setSize(800,800);
            StartPanel startP=new StartPanel();
            mainWindow.add(startP);
            mainWindow.revalidate();
            mainWindow.repaint();
            mainWindow.pack();
            setVisible(false);
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
