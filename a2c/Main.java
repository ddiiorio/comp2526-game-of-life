package ca.bcit.comp2526.a2c;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Launches new game of life GUI.
 * 
 * @author Danny Di Iorio
 * @version 5.0
 *
 */
public final class Main {
    private static final int GAME_SIZE = 25;
    private static final float SCREEN_AREA = 0.80f;
    private static final float WIDTH_PERCENT = 100.0f;
    private static final Toolkit TOOLKIT;

    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * 
     * Constructs Main objects.
     */
    private Main() {
    }

    /**
     * Main entry point for the program.
     * 
     * @param argv Not used.
     */
    public static void main(final String[] argv) {
        final GameFrame frame;
        final World world;

        RandomGenerator.reset();
        world = new World(GAME_SIZE, GAME_SIZE);
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Positions GUI on the center of the user's screen.
     * 
     * @param frame Game frame
     */
    private static void position(final GameFrame frame) {
        final Dimension size;

        size = calculateScreenArea(SCREEN_AREA, SCREEN_AREA);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }
    
    /**
     * Calculates center of the user's screen.
     * 
     * @param size User's screen size
     * @return Point at center of screen
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2, 
                (screenSize.height - size.height) / 2));
    }

    /**
     * Calculates user's screen area.
     * 
     * @param widthPercent Screen width percent
     * @param heightPercent Screen height percent
     * @return Screen area size
     */
    public static Dimension calculateScreenArea(final float widthPercent,
            final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > WIDTH_PERCENT)) {
            throw new IllegalArgumentException("widthPercent cannot be "
                    + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > WIDTH_PERCENT)) {
            throw new IllegalArgumentException("heightPercent cannot be "
                    + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
