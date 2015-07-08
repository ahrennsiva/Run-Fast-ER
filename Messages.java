import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Messages is a Greenfoot Actor. A class to make messages appear on the screen. 
 * 
 * @author Rhys Young
 * @version Jan 2014
 */
public class Messages extends Actor
{
    private String text;
    private Color color;
    /**
     * Constructor for the messages class.
     * @param message The text that you want to be displayed.
     * @param foreground The foreground color you want to be used for that text.
     */
    public Messages(String message, Color foreground){
        text = message;
        color = foreground;
        setImage(new GreenfootImage(text, 30, color, null));
    }
    
    /**
     * A simple method to update the message.
     * @param message The new text you want to be displayed.
     * @param foreground The new color you want to be used for that text.
     */
    public void updateMessage(String message, Color foreground)
    {
        text = message;
        color = foreground;
    }

    /**
     * Act - do whatever the Messages wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(new GreenfootImage(text, 30, color, null));
    }    
}
