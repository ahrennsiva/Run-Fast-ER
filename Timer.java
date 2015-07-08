import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Timer is a Greenfoot Actor. A simple and easy to use timer class to keep track of the start time and the current time of the JVM.
 * 
 * @author Rhys Young
 * @version Jan 2014
 */
public class Timer extends Actor
{
    public long startTime;
    private long currentTime;
    private int counter = 0;
    private  Messages message; 
    
    /**
     * The constructor for the timer class
     */
    public Timer()
    {
        startTime = System.nanoTime();
        message = new Messages("", Color.black);
    }

    /**
     * Act method
     */
    public void act(){
        if(counter == 0){
            counter++;
        }
        if(counter == 1){
            getWorld().addObject(message, 269, 64); // adds the message (with the time) to the world
        }

        message.updateMessage(getTimeDifference(startTime), Color.black); // prints time
    }

    /**
     * Gets the time difference between the startTime and the current time
     * @param firstTime The starting time that you want to be subtracted from the current time
     * @return Returns a string with the time difference in seconds
     */
    public String getTimeDifference(long firstTime)
    {
        currentTime = System.nanoTime();
        return ((int)((double)(currentTime - firstTime) / 1000000000) + " sec(s)");
    }
}
