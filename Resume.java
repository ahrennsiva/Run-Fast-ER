import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Resume is a Greenfoot Actor and a subclass of MenuObjects.
 * Write a description of class Resume here.
 * 
 * @author Hyson Leung
 * @version Jan 2014
 */
public class Resume extends MenuObjects
{
    /**
     * Act - do whatever the Resume wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if (Greenfoot.mouseClicked(this)) ((WorldMap) getWorld()).unpause();
    }    
}
