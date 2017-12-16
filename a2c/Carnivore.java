package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * Carnivore class, moves around world eating Herbivores and Omnivores. Dies if
 * no food is found within 7 turns.
 * 
 * @author Danny Di Iorio
 * @version 4.0
 *
 */
public class Carnivore extends Animal {

    private static final long serialVersionUID = -3741331498152725447L;
    private static final int NUMEMPTY = 2;
    private static final int NUMEDIBLE = 2;
    private static final int NUMMATES = 1;
    private static final int LIFEREMAIN = 7;

    /**
     * Constructs Carnivore objects.
     * 
     * @param location
     *            cell location
     */
    public Carnivore(final Cell location) {
        super(location, NUMEMPTY, NUMEDIBLE, NUMMATES, LIFEREMAIN);
        addToAttList(Attributes.OCCUPIED);
        addToAttList(Attributes.CARNIVORE);
        addToAttList(Attributes.OMN_EDIBLE);
        init();
    }

    /**
     * Sets Carnivore's cell color to magenta.
     */
    protected void init() {
        setColor(Color.magenta);
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
        return ent.hasAtt(Attributes.CARN_EDIBLE);
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
                    && n[i].getEntity().hasAtt(Attributes.CARN_EDIBLE)) {
                edible++;
            }
        }
        return edible;
    }

    /**
     * Spawns new Carnivore into cell.
     * 
     * @param location
     *            cell location
     */
    protected void giveBirth(final Cell location) {
        location.setEntity(new Carnivore(location));
    }
}
