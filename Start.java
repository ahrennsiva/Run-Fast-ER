import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start is a Greenfoot Actor and a subclass of MenuObjects.
 * Write a description of class Start here.
 * 
 * @author Hyson Leung
 * @version Jan 2014
 */
public class Start extends MenuObjects
{
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) Greenfoot.setWorld(new MenuLevelSelect());
    }    
}
