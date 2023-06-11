package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;
import org.junit.Test;
/**
 * This is the TruckTest class.
 * @author Carlos Manrique Ucharico
 * @version Winter 2021
 */
public class TruckTest {
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;
    
    /** Test method for Truck constructor. */
    @Test
    public void testTruckConstructor() {
        final Truck h = new Truck(10, 11, Direction.NORTH);
        
        assertEquals("Truck x coordinate not initialized correctly!", 10, h.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 11, h.getY());
        assertEquals("Truck direction not initialized correctly!",
                     Direction.NORTH, h.getDirection());
        assertEquals("Truck death time not initialized correctly!", 0, h.getDeathTime());
        assertTrue("Truck isAlive() fails initially!", h.isAlive());
    }
    
    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Truck h = new Truck(10, 11, Direction.NORTH);
        
        h.setX(12);
        assertEquals("Truck setX failed!", 12, h.getX());
        h.setY(13);
        assertEquals("Truck setY failed!", 13, h.getY());
        h.setDirection(Direction.SOUTH);
        assertEquals("Truck setDirection failed!", Direction.SOUTH, h.getDirection());
    }
    
    

    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        
        final List<Terrain> validTerrain = new ArrayList<>();
        
        //  gotta add the vlid terrains as a list here
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.LIGHT);
                
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET) {
                    //System.out.println("...."+currentLightCondition+"...");
                   
                    
                    assertTrue("Truck should be able to pass STREET"
                               + ", with light " + currentLightCondition,
                               truck.canPass(destinationTerrain, currentLightCondition));
                    
                    
                    
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                           // truck can pass CROSSWALK
                           // if the light is YELLOW or RED but not GREEN
                           
                            //if the light is GREEN or YELLOW but not RED
                    if (currentLightCondition == Light.RED) {
                        assertFalse("truck should NOT be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            truck.canPass(destinationTerrain,
                                          currentLightCondition));
                    } else { // light is GREEN or YELLOW
                        assertTrue("Truck should be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            truck.canPass(destinationTerrain,
                                          currentLightCondition));
                    }
                } else if (destinationTerrain == Terrain.LIGHT) {
                    
                    if (currentLightCondition == Light.RED) {
                        assertTrue("Truck should be able to pass LIGHT"
                                        + ", with color " + currentLightCondition,
                                        truck.canPass(destinationTerrain, 
                                                      currentLightCondition));
                    }
                    
                    if (currentLightCondition == Light.GREEN) {
                        assertTrue("Truck should be able to pass LIGHT"
                                        + ", with color " + currentLightCondition,
                                        truck.canPass(destinationTerrain, 
                                                      currentLightCondition));
                    }
                    
                    if (currentLightCondition == Light.YELLOW) {
                        assertTrue("Truck should be able to pass LIGHT"
                                        + ", with color " + currentLightCondition,
                                        truck.canPass(destinationTerrain, 
                                                      currentLightCondition));
                    }
                    
                } else if (!validTerrain.contains(destinationTerrain)) {
 
                    assertFalse("Truck should NOT be able to pass " + destinationTerrain
                        + ", with light " + currentLightCondition,
                        truck.canPass(destinationTerrain, currentLightCondition));
                }
            } 
        }
    }
    
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSurroundedByGrass() {
        
        //  the truck should not be able to move if it is surrounded by grass 
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.GRASS);
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.GRASS);
        
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = truck.chooseDirection(neighbors);
            //System.out.println("check :  .."+d+"..");
            //System.out.println(TRIES_FOR_RANDOMNESS);
            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) { 
                seenSouth = true;
            }
        }
 
        assertFalse("Truck chooseDirection() fails to select randomly "
                   + "among all possible valid choices!",
                   seenWest || seenNorth || seenEast);
    }
    
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnStreetMustReverse() {
        
        for (final Terrain t : Terrain.values()) {
            
            //System.out.println("__"+t);
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {
                //System.out.println("__"+t);
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.STREET);
                
                final Truck truck = new Truck(0, 0, Direction.NORTH);
                
                assertEquals("Truck chooseDirection() failed "
                                + "when reverse was the only valid choice!",
                             Direction.SOUTH, truck.chooseDirection(neighbors));
            }
                
        }
    }
        
    
}