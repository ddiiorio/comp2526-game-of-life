package ca.bcit.comp2526.a2c;

import java.io.Serializable;

/**
 * World consists of a 2D array of Cells that make up the game environment, and
 * determines what happens during each turn.
 * 
 * @author Danny Di Iorio
 * @version 11.0
 * 
 */
public class World implements Serializable {

    private static final long serialVersionUID = -5760579759391160672L;
    private int columns;
    private int rows;
    private Cell[][] game;

    /**
     * Constructs World objects.
     * 
     * @param columns
     *            World width
     * @param rows
     *            World height
     */
    public World(final int columns, final int rows) {
        this.columns = columns;
        this.rows = rows;
        this.game = new Cell[columns][rows];
    }

    /**
     * Places the Cells on the world.
     */
    protected void init() {
        for (int p = 0; p < this.rows; p++) {
            for (int q = 0; q < this.columns; q++) {
                this.game[q][p] = new Cell(this, q, p);
            }
        }
    }

    /**
     * Retrieves the requested Cell from the specified location in the World.
     * 
     * @param row
     *            Cell row number
     * @param col
     *            Cell column number
     * @return Cell at given location
     */
    protected Cell getCellAt(final int row, final int col) {
        return this.game[col][row];
    }

    /**
     * Retrieves the number of rows in the World.
     * 
     * @return row count
     */
    protected int getRowCount() {
        return this.rows;
    }

    /**
     * Retrieves the number of columns in the World.
     * 
     * @return column count
     */
    protected int getColumnCount() {
        return this.columns;
    }

    /**
     * Updates world by having animals act, removing dead animals, 
     * then resetting movement.
     */
    protected void update() {
        
        for (int p = 0; p < this.rows; p++) {
            for (int q = 0; q < this.columns; q++) {
                if (game[p][q].getEntity() != null
                        && !(game[p][q].getEntity().getMoved())) {
                    game[p][q].getEntity().act();
                }
            }
        }
        removeDead();
        reset();
    }

    /**
     * Removes animals from the world that have died in the last turn.
     */
    protected void removeDead() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (game[i][j].getEntity() != null
                        && !(game[i][j].getEntity().hasAtt(Attributes.PLANT))
                        && !(((Animal) game[i][j].getEntity()).isAlive())) {
                    game[i][j].setEntity(null);
                }
            }
        }
    }

    /**
     * Resets moved status of all entities.
     */
    protected void reset() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (game[i][j].getEntity() != null) {
                    game[i][j].getEntity().resetMoved();
                }
            }
        }
    }

    /**
     * Takes a turn for each entity.
     */
    protected void takeTurn() {
        update();
    }
}
