package FramePanels;

import UtilsPackage.LevelLoader;
import UtilsPackage.MainTimer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by USER on 6/9/2017.
 */
public class MainWindow extends JFrame{
    private static MainWindow instance=null;
    public static MainWindow getInstance(){
        if (instance==null){
            instance=new MainWindow();
        }
        return instance;
    }
    private MainWindow() {
        super("TowersDefence");
        setSize(800,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        StartPanel startPanel = new StartPanel();
        add(startPanel);
        setVisible(true);
    }
    public static void main(String[] args) {
        getInstance();
    }
    @Override
    public void repaint(){
        super.repaint();
    }
}
