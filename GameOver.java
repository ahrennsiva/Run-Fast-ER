import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * GameOver is a Greenfoot World. The screen for when the player loses all their lives.
 * 
 * @author Hyson Leung, Rhys Young
 * @version January 2015
 */
public class GameOver extends World
{
    private String time;
    private int counter = 0;
    private Messages message;
    
    /**
     * Constructor for objects of class GameOver.
     * 
     * @param string get string for time display
     */
    public GameOver(String n, WorldMap wm){
        super(960, 640, 1);
        addObject(new TryAgain(wm), 480, 300);
        addObject(new Quit(), 480, 480);
        message = new Messages("", Color.black);
        
        time = n;
    }

    public void act()
    {
        message.updateMessage(time, Color.black);
        addObject(message, 474, 538);
    }
}
