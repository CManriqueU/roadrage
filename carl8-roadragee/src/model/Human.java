package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * @author Carlos Manrique Ucharico.
 * @version Winter 2021
 * Human class with methods to determine the motion of human. 
 */

/**
 * 
 * @author carlos
 *
 */
public class Human extends AbstractVehicle {
        
    /**
     * Death time for the Human.
     */
    private static final int DEATH_TIME = 45;

    
    /**
     * Constructs a Human object with given x, y coordinates, direction and death time. 
     * 
     * @param theX   The X coordinate of a Human.
     * @param theY   The Y coordinate of a Human.
     * @param theDir The direction of a Human.
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    
    /**
     * Check if a Human is able to move in this terrain.
     * 
     * @param theTerrain terrain in which the human is located.
     * @param theLight light color at the certain.
     * @return returns false if human is unable to pass, it will return true otherwise.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        if (theTerrain == Terrain.GRASS) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            return true;
        }
        
        return false;
    }
    
    /**
     *  Decides which direction to take according to its environment.
     * 
     * @param theNeighbors map of the terrain next to the human.
     * @return the direction the human will take.
     */
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        //  in the List below we add the possible directions we can use
        final List<Direction> possibleDirections = new ArrayList<>();
        final Random randomValue = new Random();
        
        final Direction dir;
        
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {              
            return getDirection();      
        } else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {              
            return getDirection().left();
            
        } else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {               
            return getDirection().right();        
        }
      
        // adding directions in the list
        if (theNeighbors.get(getDirection().right()) == Terrain.GRASS) {          
            possibleDirections.add(getDirection().right());
        }
        if (theNeighbors.get(getDirection()) == Terrain.GRASS) {       
            possibleDirections.add(getDirection());
        }
        if (theNeighbors.get(getDirection().left()) == Terrain.GRASS) {          
            possibleDirections.add(getDirection().left());
        }        
        
        
        if (!possibleDirections.isEmpty()) {  
            dir = possibleDirections.get(randomValue.nextInt(possibleDirections.size()));
        } else {
            //if there is not any possible direction , then we have to reverse.
            dir = getDirection().reverse();
        }
        return dir;
    }
   
}
