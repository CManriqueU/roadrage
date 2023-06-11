package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This is the Atv class, contains the methods for the motion of the atv.
 * @author Carlos Manrique Ucharico
 * @version Winter 2021
 */
public class Atv extends AbstractVehicle {
  
    /**
     * Death time for the Atv.
     */
    public static final int DEATH_TIME = 15;

    /**
     * Constructor for the Atv class.
     * @param theVehicleX position of the Atv.
     * @param theVehicleY position of the Atv.
     * @param theDir direction of the Atv.
     */
    public Atv(final int theVehicleX, final int theVehicleY, final Direction theDir) {
        super(theVehicleX, theVehicleY, theDir, DEATH_TIME);
    }
  
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain != Terrain.WALL;
    }
  
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
                
        final Random randomDirection = new Random();
        final List<Direction> possibleDirections = new ArrayList<>();
        final Direction dir;
        
        
        //  adding all the directions possible to the list.
        if (theNeighbors.get(getDirection()) != Terrain.WALL) {
            possibleDirections.add(getDirection());
        }
        if (theNeighbors.get(getDirection().right()) != Terrain.WALL) {
            possibleDirections.add(getDirection().right());
        }
        if (theNeighbors.get(getDirection().left()) != Terrain.WALL) {
            possibleDirections.add(getDirection().left());
        }
        
        
        // Atv never reverse direction, no need to add a condition
        dir = possibleDirections.get(randomDirection.nextInt(possibleDirections.size()));
        
        return dir;
    }

}
