package FramePanels;

import UtilsPackage.LevelLoader;
import UtilsPackage.MainTimer;
import UtilsPackage.WinnerTableLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by USER on 6/9/2017.
 */
public class StartPanel extends JPanel implements ActionListener {
    private LevelLoader loaderMap;
    private Image background;
    private JButton startGame;
    private JComboBox<Integer> levels;
    private JButton tableWinners;
    private JList<String> winners;
    private int level;

    public StartPanel() {
        super();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        WinnerTableLoader loader = new WinnerTableLoader();

        try {
            background= ImageIO.read(getClass().getResource("/backgrounds/back4.jpg"))
                    .getScaledInstance(800,800,Image.SCALE_AREA_AVERAGING);
            loader.loader("winners.txt");
        } catch (Exception ex) {

        }
        Vector<String> list = loader.getBestFive();
        winners = new JList<>(list);
        winners.setVisibleRowCount(list.size());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        winners.setBorder(BorderFactory.createTitledBorder(blackline, "Top-5", 2, 0));
        winners.setBackground(Color.cyan);


        level = 1;
        loaderMap = new LevelLoader();
        try {
            loaderMap.load("levels.txt");
        } catch (Exception e) {
            System.out.println();
        }
        int countLevels = loaderMap.getLevelsCount();
        Integer[] lvls = new Integer[countLevels];
        for (int i = 0; i < countLevels; i++)
            lvls[i] = i + 1;

        JLabel label = new JLabel("Tower Defence ");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Georgia", Font.BOLD, 80));

        levels = new JComboBox<Integer>(lvls);
        levels.setSelectedIndex(0);
        levels.addActionListener(this);
        levels.setMaximumSize(new Dimension(150, 25));
        levels.setMinimumSize(new Dimension(150, 25));
        levels.setAlignmentX(CENTER_ALIGNMENT);

        startGame = new JButton("Start Game");
        startGame.setMinimumSize(new Dimension(150, 40));
        startGame.setMaximumSize(new Dimension(150, 40));
        startGame.setAlignmentX(CENTER_ALIGNMENT);
        startGame.addActionListener(this);


        tableWinners = new JButton("Winners Table");
        tableWinners.setMinimumSize(new Dimension(150, 40));
        tableWinners.setMaximumSize(new Dimension(150, 40));
        tableWinners.setAlignmentX(CENTER_ALIGNMENT);
        tableWinners.addActionListener(this);

        add(Box.createRigidArea(new Dimension(10, 100)));

        add(label);
        add(Box.createRigidArea(new Dimension(10, 80)));
        add(levels);
        add(Box.createRigidArea(new Dimension(10, 20)));
        add(startGame);
        add(Box.createRigidArea(new Dimension(10, 20)));
        add(tableWinners);
        add(Box.createRigidArea(new Dimension(10, 20)));
        add(winners);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == levels) {
            level = (Integer) levels.getSelectedItem();
        }
        if(e.getSource() == startGame) {

            MainWindow mainWindow=MainWindow.getInstance();
            GamePanel gamePanel = new GamePanel(loaderMap.get(level-1));
            mainWindow.remove(this);
            mainWindow.add(gamePanel);
            mainWindow.pack();
        }
        if(e.getSource()==tableWinners) {
            MainWindow mainWindow = MainWindow.getInstance();
            WinnersPanel winnersPanel = new WinnersPanel(0);
            mainWindow.remove(this);
            mainWindow.add(winnersPanel);
            mainWindow.pack();

        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, this); // see javadoc for more info on the parameters
    }


}
