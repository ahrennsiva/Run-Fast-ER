import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MenuQuit is a Greenfoot Actor and a subclass of MenuObjects.
 * Write a description of class MenuQuit here.
 * 
 * @author Hyson Leung
 * @version Jan 2014
 */
public class MenuQuit extends MenuObjects
{
    /**
     * Act - do whatever the MenuQuit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if (Greenfoot.mouseClicked(this)) System.exit(0);
    }    
}
