import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SelectTwo is a Greenfoot Actor and a subclass of MenuObjects.
 * Write a description of class SelectTwo here.
 * 
 * @author Hyson Leung
 * @version Jan 2014
 */
public class SelectTwo extends MenuObjects
{
    /**
     * Act - do whatever the SelectTwo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)) Greenfoot.setWorld(new LevelTwo());
    }    
}
