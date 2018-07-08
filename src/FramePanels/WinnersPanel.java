package FramePanels;

import UtilsPackage.LevelLoader;
import UtilsPackage.LevelMap;
import UtilsPackage.WinnerTableLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by USER on 6/9/2017.
 */
public class WinnersPanel extends JPanel implements ActionListener{
    private WinnerTableLoader loader;
    private JComboBox<String> levels;
    private JList<String> winners;
    private JScrollPane scrollPane;
    private JButton startB;
    //open list of winners by level field;
    //two constructors
    //one default 1
    //one by level
    public WinnersPanel(int level){
        super();
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        loader=new WinnerTableLoader();
        LevelLoader levelLoader= new LevelLoader();

        try {
            levelLoader.load("levels.txt");
            loader.loader("winners.txt");
        }
        catch (Exception ex){}


        setPreferredSize(new Dimension(800,800));
        JLabel label= new JLabel("Winners Table");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Georgia", Font.BOLD ,20));
        int countLevels = levelLoader.getLevelsCount()+1;
        String[] lvls = new String[countLevels];
        lvls[0]="All";
        for (int i = 1; i < countLevels; i++)
            lvls[i] = ""+i;
        levels = new JComboBox<>(lvls);
        levels.setSelectedIndex(level);
        levels.addActionListener(this);
        levels.setMaximumSize(new Dimension(150, 25));
        levels.setMinimumSize(new Dimension(150, 25));
        levels.setAlignmentX(CENTER_ALIGNMENT);


        Vector<String> list;
        if(level==0){
            list=loader.get_winners();
        }
        else
            list=loader.getWinnersByLevel(level);

        winners=new JList<>(list);
        winners.setVisibleRowCount(list.size());

        scrollPane=new JScrollPane(winners);
        scrollPane.setPreferredSize(new Dimension(450,650));
        scrollPane.setMaximumSize(new Dimension(450,650));


        startB=new JButton("Go to Menu");
        startB.addActionListener(this);
        startB.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(10,25)));
        add(label);
        add(Box.createRigidArea(new Dimension(10,20)));
        add(levels);
        add(Box.createRigidArea(new Dimension(10,5)));
        add(scrollPane);
        add(Box.createRigidArea(new Dimension(10,10)));
        add(startB);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == levels) {
            if(  levels.getSelectedIndex()==0)
                winners.setListData(loader.get_winners());
            else
                winners.setListData(loader.getWinnersByLevel(levels.getSelectedIndex()));

        }
        if(e.getSource()==startB){
            MainWindow.getInstance().remove(this);
            MainWindow.getInstance().add(new StartPanel());
            MainWindow.getInstance().pack();
        }
    }
}
