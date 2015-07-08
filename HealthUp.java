import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HealthUp is a Greenfoot Actor and a subclass of Objects. Used to display that the HealthUp has been acquired from the PowerUp box.
 * 
 * @author Hanson Ng, Etienne Yiu, Ahrenn Sivananthan
 * @version January 2015
 */
public class HealthUp extends Objects
{
    private int counter = 180;  //used for how long the image stays on screen
    private int actCounter = 0; //used for when to change the sprite
    private int frameCounter = 2; //counter used as index to change sprite 
    
    /**
     * Constructor for HealthUp sets the first image in to healthUp.
     */
    public HealthUp(){
        String firstImage = "HealthUp/healthUp1.png"; //sets the first image 
        setImage (firstImage); 
    }
    
    /**
     * Act - do whatever the tempHealth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(counter > 0){
            turn(3);

            if(actCounter == 2){ //every 2 acts, increase the image size
                imageSizeIncrease();
                actCounter = 0; //set counter back to o
            }else{
                actCounter++; //increase counter
            }

            counter--;
        }else{ //when counter reaches 0, remove the image from the world
            World world = getWorld();
            world.removeObject(this);
        }
    }    

    /**
     * Increases the size of the healthUp image
     */
    private void imageSizeIncrease(){
        if(frameCounter < 17){
            String location = "HealthUp/healthUp" + frameCounter + ".png"; //asigns jumpBoost image to the larger version
            setImage(location);
            frameCounter++;
        }
    }
}
