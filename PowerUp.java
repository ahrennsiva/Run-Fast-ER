import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PowerUp is a Greenfoot Actor and a subclass of Objects. The PowerUp box for the player to gain power ups.
 * 
 * @author Hanson Ng, Etienne Yiu, Ahrenn Sivananthan
 * @version January 2015
 */
public class PowerUp extends Objects
{
    int imageNum = 1;
    int actNum = 0;
    /**
     * Act - do whatever the powerup wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!((WorldMap) getWorld()).isPause()){
            objectFunctions(); //moves the powerup block 
            if(actNum % 10 == 0){ //every 10 acts, change the image of the power up to a diff colour
                setImage("powerup" + String.valueOf(imageNum) + ".png");
                if(imageNum == 3)
                    imageNum = 1;
                else
                    imageNum++;
            }
            actNum++;
        }
    }    
}
