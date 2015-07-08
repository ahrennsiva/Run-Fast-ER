import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * JumpMarker is a Greenfoot Actor.
 * Write a description of class JumpMarker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JumpMarker extends Actor
{
    /**
     * Act - do whatever the JumpMarker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(((WorldMap) getWorld()).isScrolling() && getX() > 63) move(-((WorldMap) getWorld()).scrollSpeed());
    }    
}
