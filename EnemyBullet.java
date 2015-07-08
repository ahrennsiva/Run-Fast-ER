import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * EnemyBullet is a Greenfoot Actor and a subclass of Projectile.
 * This is the bullet class utilized by the enemy in order to kill the player as a challenge. This will be a boss batle item in a sense.
 * 
 * @author Ahrenn Sivananthan, Hanson Ng
 * @version January 2015
 */
public class EnemyBullet extends Projectile
{    
    /**
     * Sets the velocities and angle of the bullet, based on values determined in the enemy class.
     * 
     *
     * 
     * @param velocityX Velocity in x-direction
     * @param velocityY Velocity in y-direciton
     * @param angle Angle between shot and player.
     */
    public EnemyBullet (double velocityX, double velocityY, int angle) {
        Vx = (int)velocityX;//Velocity in x-direction determined by enemy class
        Vy = (int)velocityY;//Velocity in y-direction determined by enemy class
        setRotation(angle);//Rotation found in enemy class
    }

    /**
     * Act method is used in each step of the enemy's bullet's movement.
     * It consistently moves at the velocity set above.
     * If there is a detection for outOfBounds or collision, the object will be removed and potentailly take away a life from the player.
     *
     * 
     */
    public void act() 
    {
        if (outOfBounds()) {
            getWorld().removeObject(this); //Removes object
        }else if (hasHit()){
            hitObject();//Takes damage to player.
        }else if (hasHit() == false) {//Will set location of bullet based on certain values found
            setLocation(getX() - Vx, getY() - Vy);
        }
    }    
    
    
    /**
     * Checks if there is an object between the projectile to the vertical and horizontal velocity. If there is, it will point towards it and hit the player.
     * 
     * @return boolean  whether or not the bullet has hit an object.
     */
    public boolean hasHit () {
        if(getOneObjectAtOffset(0,0,Player.class)!= null){//Used to check if the enemy has been hit.
            player = (Player)getOneObjectAtOffset(0,0,Player.class);//This is used to determine contact    
            return true;
        }else{//If no contact it will return false.
            return false;
        }      
    }
}
