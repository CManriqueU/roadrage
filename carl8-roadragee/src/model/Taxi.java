package model;

import java.util.Map;

/**
 *  * This is the Taxi class, contains the methods for the motion of the taxi.
 * @author Carlos Manrique Ucharico
 * @version Winter 2021
 */
public class Taxi extends AbstractVehicle {
  
    /**
     * Death time for the taxi.
     */
    public static final int DEATH_TIME = 15;
    
    /**
     * counter of time.
     */
    private int myCounter;
    /**
     * limit of time for the taxi to wait.
     */
    private int myLimit = 3;

    /**
     * Constructor for the taxi class.
     * @param theVehicleX   position of the taxi.
     * @param theVehicleY   position of the taxi.
     * @param theDir direction of the taxi.
     */
    public Taxi(final int theVehicleX, final int theVehicleY, final Direction theDir) {
        super(theVehicleX, theVehicleY, theDir, DEATH_TIME);
        myCounter = 0;
    }
  
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        if (theTerrain == Terrain.STREET) {    
            return true;   
        } else if (theTerrain == Terrain.LIGHT && theLight == Light.GREEN) {       
            return true;
        } else if (theTerrain == Terrain.LIGHT && theLight == Light.YELLOW) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            myCounter = 0;
            return true;
            
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW) {
            myCounter = 0;
            return true;
            
        } else if (theTerrain == Terrain.CROSSWALK 
                        && theLight == Light.RED && myCounter >= myLimit) {
            myCounter = 0;
            return true;
            
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED 
                        && myCounter < myLimit) {
            myCounter++;
        }

        return false;

    }
  
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
   
        final Direction route;
        
        if (theNeighbors.get(getDirection()) == Terrain.STREET 
                || theNeighbors.get(getDirection()) == Terrain.LIGHT
                || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            
            route = getDirection();

        } else if (theNeighbors.get(getDirection().left()) == Terrain.STREET 
                || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
                    
            route = getDirection().left();

        } else if (theNeighbors.get(getDirection().right()) == Terrain.STREET 
                || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            
            route = getDirection().right();

        } else {
            route = getDirection().reverse();
        }
        
        return route;
    }

}
