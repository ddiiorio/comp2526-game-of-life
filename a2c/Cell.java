package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Cells are what the world consists of, and can contain entities of type Plant,
 * Herbivore, Carnivore, Omnivore, or empty.
 * 
 * @author Danny Di Iorio
 * @version 12.0
 * 
 */
public class Cell extends JPanel implements Serializable {

    private static final long serialVersionUID = 3820524964430182869L;
    private static final int RANDOM = 100;
    private static final int HERBIVORE = 80;
    private static final int PLANT = 50;
    private static final int CARNIVORE = 40;
    private static final int OMNIVORE = 32;
    private Entity entity;
    private World world;
    private int locX;
    private int locY;

    /**
     * Constructs Cell objects.
     * 
     * @param world
     *            World holding the cell
     * @param row
     *            Row location of cell
     * @param column
     *            Column location of cell
     */
    public Cell(final World world, final int row, final int column) {
        this.world = world;
        this.locX = column;
        this.locY = row;
        init();
    }

    /**
     * Sets up the initial game layout of entities.
     */
    protected void init() {
        int num = RandomGenerator.nextNumber(RANDOM);
        if (num >= HERBIVORE) {
            setEntity(new Herbivore(this));
        } else if (num >= PLANT) {
            setEntity(new Plant(this));
            getEntity().resetMoved();
        } else if (num >= CARNIVORE) {
            setEntity(new Carnivore(this));
        } else if (num >= OMNIVORE) {
            setEntity(new Omnivore(this));
        }
    }

    /**
     * Paints cells the appropriate color by calling the getColor method on
     * entities, and white for nulls.
     * 
     * @param gg
     *            Game panel
     */
    protected void paintComponent(final Graphics gg) {
        if (entity != null) {
            gg.setColor(entity.getColor());
        } else {
            gg.setColor(Color.WHITE);
        }
        gg.fillRect(0, 0, getWidth(), getHeight());
        gg.setColor(Color.BLACK);
        gg.drawRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Gets surrounding cells - corners return 3 Cells, sides return 5,
     * and all others return 8.
     * 
     * @return array of the adjacent Cells
     */
    protected Cell[] getAdjacentCells() {
        ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
        Cell[] neighbors = new Cell[1];
        Cell cellHolder = null;
        int yy = 0;
        int xx = 0;

        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                xx = this.locX + row;
                yy = this.locY + col;
                if (xx >= 0 && xx < world.getRowCount() 
                        && yy >= 0 && yy < world.getColumnCount()) {
                    cellHolder = this.world.getCellAt(xx, yy);
                    if (cellHolder != null && cellHolder != this) {
                        adjacentCells.add(this.world.getCellAt(xx, yy));
                    }
                }
            }
        }
        return adjacentCells.toArray(neighbors);
    }

    /**
     * Getter for the entity in a given cell.
     * 
     * @return the entity within a cell
     */
    protected Entity getEntity() {
        return entity;
    }

    /**
     * Setter for the entity in a given cell.
     * 
     * @param entity
     *            the entity to set
     */
    protected void setEntity(final Entity entity) {
        this.entity = entity;
    }
}
