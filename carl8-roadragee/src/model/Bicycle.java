package model;

import java.util.Map;

/**
 *  * This is the Bicycle class, contains the methods for the motion of the bicycle.
 * @author Carlos Manrique Ucharico
 * @version Winter 2021
 */
public class Bicycle extends AbstractVehicle {

    /**
     * Death time for the Bicycle.
     */
    public static final int DEATH_TIME = 25;

    /**
     * Constructor for the Bicycle class.
     * @param theVehicleX position of the Bicycle.
     * @param theVehicleY position of the Bicycle.
     * @param theDir direction of the Bicycle.
     */
    public Bicycle(final int theVehicleX, final int theVehicleY, final Direction theDir) {
        super(theVehicleX, theVehicleY, theDir, DEATH_TIME);

    }
  
    /**
     * canPass class, It will determine if the vehicle is able to pass.
     * @param theTerrain the terrain in which the vehicle is located.
     * @param theLight light color at the terrain.
     * @return true if bicycle is able to pass, false otherwise.
     */
    //@Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.TRAIL)     {
            return true;
        } else if (theTerrain == Terrain.LIGHT && theLight == Light.GREEN) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            return true;
        }
       
        return false;

    }
    
    
    
    /**
     *  ChooseDirection class, It will decide what direction to take,
     *   according to the neighbors. 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        final Direction dir;
        
        if (theNeighbors.get(getDirection()) == Terrain.TRAIL) {
            
            dir = getDirection();
        
        } else if (theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
            
            dir = getDirection().left();
        
        } else if (theNeighbors.get(getDirection().right()) == Terrain.TRAIL) {
            
            dir = getDirection().right();
        
        } else if (theNeighbors.get(getDirection()) == Terrain.STREET 
                || theNeighbors.get(getDirection()) == Terrain.LIGHT
                || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            
            dir = getDirection();

        
        } else if (theNeighbors.get(getDirection().left()) == Terrain.STREET
                || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            
            dir = getDirection().left();
        
        } else if (theNeighbors.get(getDirection().right()) == Terrain.STREET
                || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            
            dir = getDirection().right();
        
        } else {
            dir = getDirection().reverse();
        }
        
        return dir;
    }


}