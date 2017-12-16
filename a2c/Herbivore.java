package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * Herbivore class, moves around world eating plants, but dies if no plants are
 * found within 10 turns.
 * 
 * @author Danny Di Iorio
 * @version 11.0
 * 
 */
public class Herbivore extends Animal {

    private static final long serialVersionUID = 454314853298561678L;
    private static final int NUMEMPTY = 1;
    private static final int NUMEDIBLE = 2;
    private static final int NUMMATES = 2;
    private static final int LIFEREMAIN = 10;

    /**
     * Constructs Herbivore objects.
     * 
     * @param location
     *            cell location
     * @throws CouldNotAddException 
     * 
     */
    public Herbivore(final Cell location) {
        super(location, NUMEMPTY, NUMEDIBLE, NUMMATES, LIFEREMAIN);
        addToAttList(Attributes.OCCUPIED);
        addToAttList(Attributes.HERBIVORE);
        addToAttList(Attributes.OMN_EDIBLE);
        addToAttList(Attributes.CARN_EDIBLE);
        init();
    }

    /**
     * Sets Herbivore's cell color to yellow.
     */
    protected void init() {
        setColor(Color.yellow);
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
        return ent.hasAtt(Attributes.HERB_EDIBLE);
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
                    && n[i].getEntity().hasAtt(Attributes.HERB_EDIBLE)) {
                edible++;
            }
        }
        return edible;
    }

    /**
     * Spawns new Herbivore into cell.
     * 
     * @param location
     *            cell location
     */
    protected void giveBirth(final Cell location) {
        location.setEntity(new Herbivore(location));
    }
}
