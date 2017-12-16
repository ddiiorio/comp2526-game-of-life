package ca.bcit.comp2526.a2c;

/**
 * Attributes to be assigned to entities in the world.
 * 
 * @author Danny Di Iorio
 * @version 2.0
 * 
 */
public enum Attributes {
    /**
     * Entity is edible by a herbivore.
     */
    HERB_EDIBLE,
    
    /**
     * Entity is occupied, nothing can move onto it.
     */
    OCCUPIED,
    
    /**
     * Entity is a herbivore.
     */
    HERBIVORE,
    
    /**
     * Entity is a plant.
     */
    PLANT,
    
    /**
     * Entity is edible by an omnivore.
     */
    OMN_EDIBLE,
    
    /**
     * Entity is edible by a carnivore.
     */
    CARN_EDIBLE,
    
    /**
     * Entity is a carnivore.
     */
    CARNIVORE,
    
    /**
     * Entity is an omnivore.
     */
    OMNIVORE
}
