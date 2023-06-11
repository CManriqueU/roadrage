package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * This is the Truck class, contains the methods for the motion of the truck.
 * @author Carlos Manrique Ucharico
 * @version Winter 2021
 */
public class Truck extends AbstractVehicle {

    /**
     * Death time for the truck.
     */
    public static final int DEATH_TIME = 0;
    
    /**
     * Constructor for the Trick class.
     * @param theVehicleX position of the vehicle.
     * @param theVehicleY position of the vehicle.
     * @param theDir direction of the Truck.
     */
    public Truck(final int theVehicleX, final int theVehicleY, final Direction theDir) {
        super(theVehicleX, theVehicleY, theDir, DEATH_TIME);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {      
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {  
            return true;
        }
        
        return false;
    }

  
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        
        final List<Direction> possibleDirections = new ArrayList<>();
        final Random randomDirection = new Random();
        final Direction route;
               
        if (theNeighbors.get(getDirection().left()) == Terrain.STREET 
                || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
          
            possibleDirections.add(getDirection().left());
        }
        
        if (theNeighbors.get(getDirection()) == Terrain.STREET 
                || theNeighbors.get(getDirection()) == Terrain.LIGHT
                || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
          
            possibleDirections.add(getDirection());
        }
        
        if (theNeighbors.get(getDirection().right()) == Terrain.STREET 
                || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
          
            possibleDirections.add(getDirection().right());
        }
        
        if (!possibleDirections.isEmpty()) {  
            route = possibleDirections.get(randomDirection.nextInt(possibleDirections.size()));
        } else {
            route = getDirection().reverse();
        }
        
        return route;
    }


}