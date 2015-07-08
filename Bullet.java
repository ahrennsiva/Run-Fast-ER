import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bullet is a Greenfoot Actor and a subclass of Projectile.
 * This is the bullet class utilized by the player in order to kill the enemy and beat a level.
 * 
 * @author Ahrenn Sivananthan, Hanson Ng
 * @version January 2015
 */
public class Bullet extends Projectile
{
    int xSpeed;// Speed of bullet in x direction
    boolean rightDirection = true;//Determine direction bullet is travelling

    Player player;//Instance of player
    
    /**
     * This constructor is utilized to find the direction to shoot the buller, based on the position of the character.
     */
    public Bullet(Player main){
        player = main;//instance of player

        if (player.findLastDir().equals( "l")){//Based on direction found, shoot in that direction.
            rightDirection = false;

            xSpeed = -5;
        }else{//Based on direction found, shoot in that direction.
            rightDirection = true;

            xSpeed = 5;
        }
    }

    /**
     * Act method is used in each step of the bullet's movement.
     * It consistently moves at the xSpeed found earlier
     * If there is a detection for outOfBounds or collision, the object will be removed and potentailly take away a life from the enemy.
     *
     * 
     */
    public void act() 
    {
        move(xSpeed);//Move at xSpeed

        if(outOfBounds() || hasHit() ){//Checking if object is out of bounds or hit the enemy.

            if(hasHit()){//reduce enemy lives.
                enemy.decreaseLives();
            }
            getWorld().removeObject(this);//remove object from world
        }
    }

    /**
     * Checks if there is an object between the projectile to the vertical and horizontal velocity. If there is, it will point towards it and hit the enemy.
     * 
     * @return boolean  whether or not the bullet has hit an object.
     */
    public boolean hasHit () {
        if(getOneObjectAtOffset(0,0,Enemy.class)!= null){//Used to check if the enemy has been hit.
            enemy = (Enemy)getOneObjectAtOffset(0,0,Enemy.class);//This is used to determine contact    
            return true;
        }else{//If no contact it will return false.
            return false;
        }      
    }
}

