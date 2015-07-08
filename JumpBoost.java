import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * JumpBoost is a Greenfoot Actor and a subclass of Objects. Used to display that the JumpBoost has been acquired from the PowerUp box.
 * 
 * @author Hanson Ng, Etienne Yiu
 * @version January 2015
 */
public class JumpBoost extends Objects
{
    private int counter = 180; //used for how long the image stays on screen
    private int actCounter = 0; //used for when to change the sprite
    private int frameCounter = 2; //counter used as index to change sprite 
    
    /**
     * The constructor for jump boost sets the first image to the jumpboost class.
     */
    public JumpBoost(){
        String firstImage = "JumpBoost/TiggerJump1.png"; //sets the first image 
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
     * Increases the size of the JumpBoost (Tigger) image
     */
    private void imageSizeIncrease(){
        if(frameCounter < 17){
            String location = "JumpBoost/TiggerJump" + frameCounter + ".png"; //asigns jumpBoost image to the larger version
            setImage(location);
            frameCounter++;
        }
    }
}
