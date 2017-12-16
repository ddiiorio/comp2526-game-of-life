package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Plant class is a stationary entity in the world that can seed new plants if
 * it has at least 3 neighboring plants to seed with, and at least 2 empty
 * adjacent cells.
 * 
 * @author Danny Di Iorio
 * @version 13.0
 * 
 */
public class Plant extends Entity {

    private static final long serialVersionUID = -6263282622052423082L;
    private static final int NUMMATES = 2;
    private static final int NUMEMPTY = 3;
    private static final int FILLER = 111;

    /**
     * Constructs Plant objects.
     * 
     * @param location
     *            Cell location
     */
    public Plant(final Cell location) {
        super(location, NUMEMPTY, FILLER, NUMMATES, FILLER);
        addToAttList(Attributes.HERB_EDIBLE);
        addToAttList(Attributes.OMN_EDIBLE);
        addToAttList(Attributes.PLANT);
        setMoved(true);
        init();
    }

    /**
     * Sets Plant cell color.
     */
    protected void init() {
        setColor(Color.green);
    }

    /**
     * Plants can act by seeding to create new plants.
     */
    protected void act() {
        seed();
    }

    /**
     * Counts plants and empty cells in surrounding cells.
     * 
     * @param neighbors
     *            adjacent cells array
     * @return true if plant count is greater than two and if empty cells are
     *         greater than three
     */
    protected boolean countNeighbors(final Cell[] neighbors) {
        int countPlant = 0;
        int countEmpty = 0;

        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i].getEntity() != null
                    && neighbors[i].getEntity().hasAtt(Attributes.PLANT)) {
                countPlant++;
            } else if (neighbors[i].getEntity() == null) {
                countEmpty++;
            }
        }
        return (countPlant >= NUMMATES && countEmpty >= NUMEMPTY);
    }

    /**
     * Seeds a plant in an adjacent cell if the necessary conditions are true.
     */
    protected void seed() {
        ArrayList<Cell> adjNull = new ArrayList<Cell>();
        Cell[] adj = this.getCell().getAdjacentCells();

        for (int i = 0; i < adj.length; i++) {
            if (adj[i].getEntity() == null) {
                adjNull.add(adj[i]);
            }
        }
        if (countNeighbors(adj)) {
            int choose = RandomGenerator.nextNumber(adjNull.size());
            Cell newLocation = adjNull.get(choose);
            if (newLocation != null) {
                newLocation.setEntity(new Plant(newLocation));

                newLocation.getEntity().setMoved(false);
            }
        }
    }
}
