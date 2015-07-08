import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Quit is a Greenfoot Actor and a subclass of MenuObjects.
 * Write a description of class Quit here.
 * 
 * @author Hyson Leung
 * @version Jan 2014
 */
public class Quit extends MenuObjects
{
    /**
     * Act - do whatever the Quit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if (Greenfoot.mouseClicked(this)) Greenfoot.setWorld(new Menu());  
    }    
}
