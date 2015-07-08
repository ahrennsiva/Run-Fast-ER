import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Enemy is a Greenfoot Actor and a subclass of Character.
 * The enemy is considered the final boss for the end of each level, and it will perform controlled movement along with shooting at the player. ]
 * It must be defeated to win each level.
 * 
 * @author Ahrenn Sivananthan, Hanson Ng
 * @version January 2015
 */
public class Enemy extends Character
{
    int enemyLives = 10;//Number of lives of the enemy
    private int xSpeed;//Speed in the x direction
    private boolean moving = false; // If moving left or right
    private boolean movingLeft = false; // Detection for when to turn around
    private boolean movingRight = false; // Detection for when to turn around
    private String lastDir = "l"; // Was the last direction left or right

    private int shotDelay; //Used to slow down the firing rate of the bullets 
    private int rateOfFire; // Rate of fire for bullets, lower means faster firing. This is the amount that needs to be compared to shotDelay before shooting is allowed
    private int bulletSpeed; // speed of bullets to be fired
    private int shootRange; // shooting range  
    private int searchRange;//search range of character

    private int runIndex = 4;

    /**
     * The constructor for enemy, where movement speed, and other variables are set
     */
    public Enemy(){
        xSpeed = 1;//Speed in the x direction
        shotDelay = 0;//Used to slow down the firing rate of the bullets 
        rateOfFire = 150;// Rate of fire for bullets, lower means faster firing. This is the amount that needs to be compared to shotDelay before shooting is allowed
        bulletSpeed = 3;// speed of bullets to be fired
        searchRange = 800;//search range of character
        shootRange = 35;// shooting range    

    }

    /**
     * Act - do whatever the Enemy wants to do. This is used to cause its change in direction at certain coordinates, as well as set images 
     * based on the character's direction. This also controls shooting at set time intervals.
     */
    public void act() 
    {  
        if(!((WorldMap) getWorld()).isPause()){
            if(getX() > 800){//Based on coordinate will move this direction and turn around if need be.
                lastDir = "l";
            }
            if(getX() < 70) {//Based on coordinate will move this direction and turn around if need be.
                lastDir = "r";
            }

            if(lastDir.equals("l")){//Actual movement
                move(-xSpeed);
            }
            else {//Actual movement
                move(xSpeed);
            }

            if(runIndex > 28) runIndex = 4;
            if(lastDir.equals("r")) setImage("EnemyRun/RunR" + (runIndex/4) + ".png");
            else setImage("EnemyRun/RunL" + (runIndex/4) + ".png");
            runIndex++;

            if(findTarget()){//Will check if the player has been found. Will delay the shot slightly, and then procede to shoot.
                //Greenfoot.delay(600);
                shoot();
                shotDelay++;
            }
        }       
    }  

    /**
     * Shooting method that generates a bullet, then adds the bullet object to map. Based on math engine of Greenfoot.
     */
    private void shoot() {
        if (shotDelay >= rateOfFire) {
            EnemyBullet bullet = new EnemyBullet(bulletSpeed*Math.cos(this.getRadRotation()), bulletSpeed*Math.sin(this.getRadRotation()), getRotation());
            getWorld().addObject(bullet, getX(), getY());            
            shotDelay = 0;
        } 
    }

    /**
     * Finds player to try and kill within it's viewing range.
     * 
     * @return boolean   the value of true or false of finding a player.
     */
    private boolean findTarget () {
        List<Player> playerLocation = getObjectsInRange(searchRange, Player.class);//Searches for players.
        if (playerLocation.size() == 0) {//Will determine if players have been found or not and return the value.
            return false;
        } else {            
            return true;
        }
    }

    /**
     * Rotation method used by Greenfoot to find angle between current position and arc length to target.
     * 
     * @return double   the rotation in radians.
     */
    private double getRadRotation() {
        return Math.toRadians(getRotation());
    }

    /**
     * Simple mutator used to decrease the lives of the enemy in the world class or upon contact with bullets.
     */ 
    public void decreaseLives(){
        enemyLives--;
    }    
}
