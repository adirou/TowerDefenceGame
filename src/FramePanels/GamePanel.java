package FramePanels;

import UtilsPackage.LevelMap;
import UtilsPackage.UserDetails;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by USER on 6/9/2017.
 */
public class GamePanel extends JPanel {

    public GamePanel(LevelMap levelMap){
        super(new BorderLayout());
        setSize(800,800);
        try {
             UserDetails userDetails=new UserDetails(levelMap.getLevel());
             BoardPanel board = new BoardPanel(userDetails, levelMap);

             Toolbar toolbar=new Toolbar(userDetails, board);
             add(toolbar,BorderLayout.NORTH);

             add(board,BorderLayout.CENTER);
        }
        catch (Exception e) {}
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

      //  g.drawImage(new ImageIcon("towers/1.png").getImage(), 0, 0, this); // see javadoc for more info on the parameters
    }

}
