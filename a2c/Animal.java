package ca.bcit.comp2526.a2c;

import java.util.ArrayList;

/**
 * Animal abstract class that outlines key methods for all Animal
 * objects, and additional abstract methods to be implemented by
 * subclasses.
 * 
 * @author Danny Di Iorio
 * @version 12.0
 * 
 */
public abstract class Animal extends Entity {

    private static final long serialVersionUID = 1L;
    private int numEmpty;
    private int numEdible;
    private int numMates;
    private int lifeRemain;
    private int emptyNeighbor;
    private int mateNeighbor;
    private int hungryLevel;
    private boolean alive = true;

    /**
     * Constructs Animal objects.
     * 
     * @param cell
     *            cell location
     * @param numEmpty
     *            number of empty adjacent cells
     * @param numEdible
     *            number of adjacent cells with food
     * @param numMates
     *            number of adjacent cells with mates
     * @param lifeRemain
     *            turns remaining before death
     */
    public Animal(final Cell cell, final int numEmpty,
            final int numEdible, final int numMates, final int lifeRemain) {
        super(cell, numEmpty, numEdible, numMates, lifeRemain);
        this.numEmpty = numEmpty;
        this.numEdible = numEdible;
        this.numMates = numMates;
        this.lifeRemain = lifeRemain;
    }

    /**
     * Gives birth to new animal.
     * 
     * @param location
     *            cell location
     */
    protected abstract void giveBirth(final Cell location);

    /**
     * Checks if entity being moved onto is edible.
     * 
     * @param e
     *            entity being moved onto
     * @return true if edible
     */
    protected abstract boolean eat(final Entity e);

    /**
     * Counts edible entities in adjacent cells.
     * 
     * @param n
     *            adjacent cells array
     * @return edible number of edible neighbors
     */
    protected abstract int countEdible(final Cell[] n);

    /**
     * Getter for alive status.
     * 
     * @return the alive
     */
    protected boolean isAlive() {
        if (hungryLevel < this.lifeRemain) {
            alive = true;
        } else {
            alive = false;
        }
        return alive;
    }

    /**
     * Setter for alive status.
     * 
     * @param alive
     *            the status to set
     */
    protected void setAlive(final boolean alive) {
        this.alive = alive;
    }

    /**
     * Kills an entity and sets alive to false.
     */
    protected void die() {
        setAlive(false);
    }

    /**
     * Moves the Herbivore one cell, where it eats if the cell contains a Plant.
     */
    protected void move() {
        if (!this.isAlive()) {
            die();
            return;
        }
        if (this.getMoved()) {
            return;
        }
        this.setMoved(true);
        hungryLevel++;
        Attributes att = null;
        if (this.hasAtt(Attributes.HERBIVORE)) {
            att = Attributes.HERBIVORE;
        } else if (this.hasAtt(Attributes.CARNIVORE)) {
            att = Attributes.CARNIVORE;
        } else if (this.hasAtt(Attributes.OMNIVORE)) {
            att = Attributes.OMNIVORE;
        }
        Cell[] adj = movableAdjacent(getCell().getAdjacentCells(), att);
        if (adj != null) {
            int choose = RandomGenerator.nextNumber(adj.length);
            Cell newCell = adj[choose];
            if (newCell.getEntity() != null && eat(newCell.getEntity())) {
                hungryLevel = 0;
            }
            Cell currentLocation = getLocation();
            this.setLocation(newCell);
            newCell.setEntity(this);
            currentLocation.setEntity(null);
        }
    }

    /**
     * Checks adjacent cells for empty positions or edible entities
     * to move on top of and eat.
     * 
     * @param n
     *            adjacent cells array
     * @param att
     *            attribute of current entity
     * @return adj array with cells to move into
     */
    protected Cell[] movableAdjacent(final Cell[] n, final Attributes att) {
        ArrayList<Cell> adjacents = new ArrayList<Cell>();
        ArrayList<Cell> adjacentEdible = new ArrayList<Cell>();
        Cell[] adj = new Cell[1];
        for (int i = 0; i < n.length; i++) {
            if (n[i].getEntity() == null) {
                adjacents.add(n[i]);
            } else {
                switch (att) {
                case HERBIVORE:
                    if (n[i].getEntity().hasAtt(Attributes.HERB_EDIBLE)) {
                        adjacentEdible.add(n[i]);
                    }
                    break;
                case CARNIVORE:
                    if (n[i].getEntity().hasAtt(Attributes.CARN_EDIBLE)) {
                        adjacentEdible.add(n[i]);
                    }
                    break;
                case OMNIVORE:
                    if (n[i].getEntity().hasAtt(Attributes.OMN_EDIBLE)) {
                        adjacentEdible.add(n[i]);
                    }
                    break;
                default:
                    break;
                }
            }
        }
        if (adjacentEdible.size() > 0) {
            return adjacentEdible.toArray(adj);
        } else if (adjacents.size() > 0) {
            return adjacents.toArray(adj);
        } else {
            return null;
        }
    }

    /**
     * Creates array of cells that animals can reproduce into.
     * 
     * @param n
     *            adjacent cells array
     * @param life
     *            attribute of the current entity
     * @return array of cells to reproduce into
     */
    protected Cell[] reproducibleCells(final Cell[] n, final Attributes life) {
        ArrayList<Cell> empty = new ArrayList<Cell>();
        Cell[] adj = new Cell[1];
        int edibleNeighbor = countEdible(n);
        for (int i = 0; i < n.length; i++) {
            if (n[i].getEntity() != null && n[i].getEntity().hasAtt(life)) {
                mateNeighbor++;
            } else if (n[i].getEntity() == null) {
                emptyNeighbor++;
                empty.add(n[i]);
            }
        }
        if (mateNeighbor >= this.numMates && emptyNeighbor >= this.numEmpty
                && empty.size() > 0 && edibleNeighbor >= this.numEdible) {
            return empty.toArray(adj);
        } else {
            return null;
        }
    }

    /**
     * Creates new animal if conditions are met.
     */
    protected void reproduce() {
        Attributes att = null;
        if (this.hasAtt(Attributes.HERBIVORE)) {
            att = Attributes.HERBIVORE;
        } else if (this.hasAtt(Attributes.CARNIVORE)) {
            att = Attributes.CARNIVORE;
        } else if (this.hasAtt(Attributes.OMNIVORE)) {
            att = Attributes.OMNIVORE;
        }
        Cell[] adj = reproducibleCells(getCell().getAdjacentCells(), att);
        if (adj != null) {
            int choose = RandomGenerator.nextNumber(adj.length);
            Cell offspring = adj[choose];
            giveBirth(offspring);
        }
    }
}
