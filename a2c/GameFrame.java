package ca.bcit.comp2526.a2c;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Frame to hold the game of life, includes the game panel and a panel to hold
 * buttons for game-related functions.
 * 
 * @author Danny Di Iorio
 * @version 5.0
 *
 */
public class GameFrame extends JFrame implements Serializable {

    private static final long serialVersionUID = 7618313201830387217L;
    private static final int FOUR = 4;
    private static final int EIGHTN = 18;
    private World world;
    private JPanel gamePanel;

    /**
     * Constructs GameFrame objects.
     * 
     * @param w
     *            World object for current game
     */
    public GameFrame(final World w) {
        world = w;
    }

    /**
     * Fills Game Frame with cells and their contents.
     * 
     * @return JPanel containing the cells
     */
    protected JPanel fillGameFrame() {
        JPanel cellPanel = new JPanel();
        cellPanel.setLayout(new GridLayout(world.getRowCount(), 
                world.getColumnCount()));
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                cellPanel.add(world.getCellAt(row, col));
            }
        }
        return cellPanel;
    }

    /**
     * Adds all panels to the GameFrame.
     */
    protected void init() {
        setTitle("Assignment 2c - Danny Di Iorio");
        setLayout(new BorderLayout());
        add(buttonPanelSetup(), BorderLayout.NORTH);
        gamePanel = fillGameFrame();
        add(gamePanel, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                super.mouseClicked(arg0);
                takeTurn();
            }
        });
    }

    /**
     * Takes a turn in the game upon mouse click.
     */
    protected void takeTurn() {
        world.takeTurn();
        repaint();
    }

    /**
     * Sets up button panel for GameFrame.
     * 
     * @return JPanel with new, save, load, and exit game buttons
     */
    protected JPanel buttonPanelSetup() {
        Icon sv = new ImageIcon(getClass().getResource("save.png"));
        Icon op = new ImageIcon(getClass().getResource("open.png"));
        Icon st = new ImageIcon(getClass().getResource("start.png"));
        Icon ex = new ImageIcon(getClass().getResource("exit.png"));

        JPanel buttonPanel = new JPanel(new GridLayout(0, FOUR));
        JButton newButton = new JButton("New Game", st);
        JButton loadButton = new JButton("Load Game", op);
        JButton saveButton = new JButton("Save Game", sv);
        JButton exitButton = new JButton("Exit Game", ex);
        newButton.setBackground(Color.black);
        newButton.setForeground(Color.pink);
        newButton.setFont(new Font("Tahoma", Font.BOLD, EIGHTN));
        loadButton.setBackground(Color.black);
        loadButton.setForeground(Color.pink);
        loadButton.setFont(new Font("Tahoma", Font.BOLD, EIGHTN));
        saveButton.setBackground(Color.black);
        saveButton.setForeground(Color.pink);
        saveButton.setFont(new Font("Tahoma", Font.BOLD, EIGHTN));
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.pink);
        exitButton.setFont(new Font("Tahoma", Font.BOLD, EIGHTN));
        buttonPanel.add(newButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return buttonPanel;
    }

    /**
     * Clears current game and starts a new one in the game panel.
     */
    protected void newGame() {
        RandomGenerator.reset();
        remove(gamePanel);
        world.init();
        JPanel temp = fillGameFrame();
        gamePanel = temp;
        add(gamePanel, BorderLayout.CENTER);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    /**
     * Saves current game state to local file.
     */
    protected void saveGame() {
        final JFileChooser fc = new JFileChooser();
        int value = fc.showSaveDialog(this);
        if (value == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(world);
                out.close();
                fileOut.close();
                System.out.println("Game saved!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads saved game and repaints game panel.
     */
    protected void loadGame() {
        final JFileChooser fc = new JFileChooser();
        int value = fc.showOpenDialog(this);
        if (value == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                world = (World) in.readObject();
                in.close();
                fileIn.close();
                System.out.println("Game loaded!");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
                return;
            }
            gamePanel.removeAll();
            for (int row = 0; row < world.getRowCount(); row++) {
                for (int col = 0; col < world.getColumnCount(); col++) {
                    gamePanel.add(world.getCellAt(row, col));
                }
            }
            gamePanel.repaint();
        }
    }
}
