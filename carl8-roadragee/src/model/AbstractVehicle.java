package model;
/**
 * This is the AbstractVehicle class which contains the characteristics of the vehicles.
 * @author Carlos Manrique Ucharico
 * @version Winter 2021
 */
public abstract class AbstractVehicle implements Vehicle {

    /**
     * x position.
     */
    private int myX;

    /**
     * y position.
     */
    private int myY;

    /**
     * Direction of the vehicle.
     */
    private Direction myDirection;

    /**
     * x position.
     */
    private final int myResetX;
  
    /**
     * y position.
     */
    private final int myResetY;

    /**
     * The direction set.
     */
    private final Direction myResetDirection;
    
    /**
     * number of pokes.
     */
    private int myPokesCount;
  
    /**
     * Death time for the vehicle.
     */
    private final int myDeathTime;

    protected AbstractVehicle(final int theVehicleX, final int theVehicleY,
                              final Direction theDir, final int theDeathTime) {
        myX = theVehicleX;
        myY = theVehicleY;
        myDirection = theDir;
        myDeathTime = theDeathTime;
        myResetX = theVehicleX;
        myResetY = theVehicleY;
        myResetDirection = theDir;
        myPokesCount = 0;

    }

    @Override
    public final void collide(final Vehicle theVehicle) {
        if (theVehicle.isAlive() && this.isAlive()) {
            if (theVehicle.getDeathTime() > this.getDeathTime()) {
                theVehicle.poke();
            } else if (theVehicle.getDeathTime() < this.getDeathTime()) {
                this.poke();
            }
          
        }

    }

    @Override
    public final int getDeathTime() {     
        return myDeathTime;
    }

    @Override
    public final String getImageFileName() {
        final String picture;
        if (!this.isAlive()) {
            picture = getClass().getSimpleName().toLowerCase() + "_dead.gif";
        } else {
            picture = getClass().getSimpleName().toLowerCase() + ".gif";
        }
        return picture;
    }
  
    @Override
    public final Direction getDirection() {
        return myDirection;
    }
  
    @Override
    public final int getX() {
        return myX;
    }
  
    @Override
    public final int getY() {
        return myY;
    }
  
    @Override
    public final boolean isAlive() {
        
        if (myPokesCount > 0) {
            return false;
        } else {
            return true;
        }
    }
  
    @Override
    public final void poke() {
        myPokesCount++;
        if (myPokesCount > getDeathTime()) {
            myPokesCount = 0;
            this.setDirection(Direction.random());
            
        }

    }
  
    @Override
    public final void reset() {
        setDirection(myResetDirection);
        setX(myResetX);
        setY(myResetY);      
    }
  
    @Override
    public final void setDirection(final Direction theDir) {
        myDirection = theDir;

    }
    
    @Override
    public final String toString() {
        return this.getClass().getSimpleName();

    }
    @Override
    public final void setX(final int theX) {
        myX = theX;

    }
  
    @Override
    public final void setY(final int theY) {
        myY = theY;

    }
  
   

}

