package model;

import java.util.Map;

/**
 * This is the Car class, contains the methods for the motion of the car.
 * @author Carlos Manrique Ucharico
 * @version Winter 2021
 */
public class Car extends AbstractVehicle {

    /**
     * Death time for the Car.
     */
    public static final int DEATH_TIME = 5;

    /**
     * Constructor for the Car class.
     * @param theVehicleX position of the class.
     * @param theVehicleY position of the class.
     * @param theDir direction of the human.
     */
    public Car(final int theVehicleX, final int theVehicleY, final Direction theDir) {
        super(theVehicleX, theVehicleY, theDir, DEATH_TIME);
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
            return true;
        }
        
        return false;
    }
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {            
  
        final Direction dir;
        
        if (theNeighbors.get(getDirection()) == Terrain.STREET) {
            
            dir = getDirection();
            
        } else if (theNeighbors.get(getDirection()) == Terrain.LIGHT
                || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
       
            dir = getDirection();

        } else if (theNeighbors.get(getDirection().left()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection().left()) == Terrain.STREET) {
               
            dir = getDirection().left();

        } else if (theNeighbors.get(getDirection().right()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection().right()) == Terrain.STREET) {
       
            dir = getDirection().right();

        } else {
            dir = getDirection().reverse();
        }

        return dir;
                
    }

}

