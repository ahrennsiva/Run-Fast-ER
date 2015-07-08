import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Projectile is a Greenfoot Actor.
 * Projectile class is the super class that manages both the bullet, and enemy bullet.
 * 
 * @author Ahrenn Sivananthan, Hanson Ng
 * @version January 2015
 */
public abstract class Projectile extends Actor
{   
    protected int width = getImage().getWidth();//Determining width of bullet for usage in enemy class.
    protected int height = getImage().getHeight();//Determining height of bullet for usage in enemy class.
    protected int Vx;// Velocity in x-direction;
    protected int Vy;// Velocity in y-direction;
    protected Player player;//Instance of player;
    protected Enemy enemy;//Instance of enemy;
    /**
     * Abstract class for determining contact with another instance.
     */
    abstract boolean hasHit();

    /**
     * Inflicts damage on the player or enemy being hit, and will then remove the bullet.
     * 
     * 
     */
    protected void hitObject () {
        // checks if the projectile is in contact with an object
        if (getOneObjectAtOffset(0, 0, Player.class) != null) {
            //Reference to the player who will receive damage
            player = (Player)getOneObjectAtOffset(0, 0, Player.class);
        }
        // Removes the projectile from the world
        getWorld().removeObject(this);
    }

    /**
     * Will return true once an object escapes from the boundaries of the map, if it doesn't make contact.
     * 
     * 
     */
    protected boolean outOfBounds() { 
        //Checks coordinates based on the dimensions of the map in the x and y direction. This was mathematically determined.
        if(getX() >= 895 || getX() <= 63) {
            return true;//True for contact with x coordinate boundaries.
        } else if (getY() >= 576 || getY() <= 0) {
            return true;//True for contact with y coordinate boundaries.
        }
        return false;//Otherwise return false.
    }

}
