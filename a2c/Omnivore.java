package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * Omnivore class, moves around world eating plants, Herbivores, and Carnivores.
 * Dies if no food is found within 2 turns.
 * 
 * @author Danny Di Iorio
 * @version 5.0
 *
 */
public class Omnivore extends Animal {

    private static final long serialVersionUID = 1L;
    private static final int NUMEMPTY = 3;
    private static final int NUMEDIBLE = 3;
    private static final int NUMMATES = 1;
    private static final int LIFEREMAIN = 2;

    /**
     * Constructs Omnivore objects.
     * 
     * @param location
     *            cell location
     */
    public Omnivore(final Cell location) {
        super(location, NUMEMPTY, NUMEDIBLE, NUMMATES, LIFEREMAIN);
        addToAttList(Attributes.OCCUPIED);
        addToAttList(Attributes.OMNIVORE);
        addToAttList(Attributes.CARN_EDIBLE);
        init();
    }

    /**
     * Sets Omnivore's cell color to blue.
     */
    protected void init() {
        setColor(Color.blue);
    }

    /**
     * Directs the entity to act - which consists of moving and reproducing for
     * animals.
     */
    protected void act() {
        move();
        reproduce();
    }

    /**
     * Checks if entity being moved onto is edible.
     * 
     * @param ent
     *            entity being moved onto
     * @return true if edible
     */
    protected boolean eat(final Entity ent) {
        return ent.hasAtt(Attributes.OMN_EDIBLE);
    }

    /**
     * Counts edible entities in adjacent cells.
     * 
     * @param n
     *            adjacent cells array
     * @return edible number of edible neighbors
     */
    protected int countEdible(final Cell[] n) {
        int edible = 0;
        for (int i = 0; i < n.length; i++) {
            if (n[i].getEntity() != null 
                    && n[i].getEntity().hasAtt(Attributes.OMN_EDIBLE)) {
                edible++;
            }
        }
        return edible;
    }

    /**
     * Spawns new Omnivore into cell.
     * 
     * @param location
     *            cell location
     */
    protected void giveBirth(final Cell location) {
        location.setEntity(new Omnivore(location));
    }
}
