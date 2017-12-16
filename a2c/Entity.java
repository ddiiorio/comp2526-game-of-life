package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.io.Serializable;

/**
 * Entity abstract class that outlines key methods for implementation by
 * subclasses.
 * 
 * @author Danny Di Iorio
 * @version 15.0
 * 
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -1024661565857667738L;
    private Cell cell;
    private DoubleLinkedList<Attributes> values;
    private Color color;
    private boolean moved;

    /**
     * Constructs Entity objects.
     * 
     * @param cell
     *            Cell location
     * @param numEmpty
     *            Empty neighbor cells
     * @param numEdible
     *            Edible neighbor cells
     * @param numMates
     *            Reproducible neighbor cells
     * @param lifeRemain
     *            Turns left until death
     */
    public Entity(final Cell cell, final int numEmpty,
            final int numEdible, final int numMates, final int lifeRemain) {
        this.cell = cell;
        values = new DoubleLinkedList<Attributes>();
        init();
    }

    /**
     * To be overwritten by subclasses.
     */
    protected abstract void init();

    /**
     * To be overwritten by subclasses.
     */
    protected abstract void act();

    /**
     * Getter for cell.
     * 
     * @return the cell
     */
    protected Cell getCell() {
        return cell;
    }

    /**
     * Setter for cell.
     * 
     * @param location
     *            cell on the world
     */
    protected void setCell(final Cell location) {
        this.cell = location;
    }

    /**
     * Adds an attribute to an entity's attribute list.
     * 
     * @param att
     *            Attribute to add
     */
    public void addToAttList(final Attributes att) {
        try {
            values.addToEnd(att);
        } catch (CouldNotAddException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if entities array contains an argument attribute.
     * 
     * @param att
     *            from Attribute enumeration
     * @return true if their double linked list contains attribute
     */
    public boolean hasAtt(final Attributes att) {
        for (Attributes value: values) {
            if (value.equals(att)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets Cell location of entity.
     * 
     * @return cell cell location
     */
    protected Cell getLocation() {
        return cell;
    }

    /**
     * Sets Cell location of entity.
     * 
     * @param loc
     *            cell to set
     */
    protected void setLocation(final Cell loc) {
        this.cell = loc;
    }

    /**
     * Gets color of entity.
     * 
     * @return color entity color
     */
    protected Color getColor() {
        return color;
    }

    /**
     * Sets color of entity.
     * 
     * @param color
     *            Color to set
     */
    protected void setColor(final Color color) {
        this.color = color;
    }

    /**
     * Getter for moved status.
     * 
     * @return moved boolean
     */
    protected boolean getMoved() {
        return moved;
    }

    /**
     * Resets moved status to false.
     */
    protected void resetMoved() {
        this.moved = false;
    }

    /**
     * Setter for moved status.
     * 
     * @param moved
     *            the moved to set
     */
    protected void setMoved(final boolean moved) {
        this.moved = moved;
    }
}
