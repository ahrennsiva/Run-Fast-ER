import greenfoot.*;  
import java.awt.Color;
/**
 * ASImgBars  is a Greenfoot Actor that serves the purpose of informing a player of his current number of lives.
 * Stats currently include how many lives a user has
 * The bar with images on top is designed for a 960*640 pixels scenario.
 *
 * The stats are displayed through the use of images.
 * <p>
 * Hearts represent lives
 * 
 * @author Ahrenn Sivananthan, Hanson Ng
 * @version Jan 2014
 */

public class ASImgBars extends Actor  
{  
    private int lives;//initial number of lives, set to the natural value of 3
    private int fullLives; // max lives allowed       
    private double height; // height of image for hearts   

    /**
     * Constructs a bar that displays number of lives using hearts. Initializes the values for lives and image height as well.
     *
     * @param intialLives The user sets the value for the initial amount of lives 
     * @param numLives The user sets the value for max lives to 5 here(different value can be set but this would require bar size changes). 
     * @param imgHeight The user sets the value for heart height to 32.0 here.(different value can be set when the number of hearts is set to higher than 5)   
     *      
     */
    public ASImgBars (int initialLives, int numLives, double imgHeight)  
    {          
        lives = initialLives; //sets the amount of lives 
        fullLives = numLives; //sets max lives permitted
        height = imgHeight; //saves height of hearts 
        updateImage(); //creates initial image
    }  

    /**
     * Checks to see if current number of lives is above zero or below five so that  
     * changing lives will change nothing if lives are equal to zero or five.
     * If lives are above zero then the number of lives can and will be decreased or increased (by specified amount) upon damage to the player or a power up.
     * 
     * Runs updateImage() method to reconfigure number of hearts to match number of lives.
     *
     * @param livesModified Needs to be determined in the world before running the method. This is equal to the number of current lives as determined in the world class.
     * @return int  Returns number of lives as an integer (after subtraction or addition), otherwise returns 0 or 5.
     *                      
     */
    // Method runs when lives are lost or gained
    public int update(int livesModified)  
    {  
        if (livesModified <= 0) {
            lives = 0; //makes sure lives do not go below zero.
        }
        else if (livesModified >= fullLives){
            lives = 5; //makes sure lives do not exceed five
        }
        else if (0<=livesModified && livesModified<=5){
            lives = livesModified;//sets the number of lives to the current number of hearts determined in the world class.
        }
        updateImage(); //updates the initial image of lives
        return lives;
    }  

    /**
     *  Creates a base image for the hearts (representing lives) to be placed onto
     *  Sets background and border (of bar) to white so it is invisible.
     * Creates a hearts object that stores the image for hearts.
     * Uses a for loop to draw the hearts onto the base image accurate to a properly formatted size.
     * Runs setImage() method (Greenfoot built in) to set the base image to have the right number of hearts on top of it.
     *
     *                      
     */
    // Method runs to update number of hearts on screen.
    private void updateImage()  
    {  
        int heightOne = (int)height;//value of height for hearts recasted
        GreenfootImage image = new GreenfootImage(fullLives*heightOne+64, heightOne); // create the image's object
        image.drawRect(255, 255, image.getWidth()-1, image.getHeight()-1); // frame added to the object
        GreenfootImage hearts = new GreenfootImage("hearts_Ahrenn.png"); // obtains image of heart  
        hearts.scale(4*heightOne/6, 4*heightOne/6); // resizes image of heart
        for (int i=0; i<lives; i++){
            image.drawImage(hearts, 4*+4+i*9*4,4); // adds a specific number of hearts to image based on number of lives remaining
        }
        setImage(image); //Actually makes the image look like how the values above desire it to be.
    }  
}  

