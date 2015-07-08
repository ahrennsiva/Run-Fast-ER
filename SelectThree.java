import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SelectThree is a Greenfoot Actor and a subclass of MenuObjects.
 * Write a description of class SelectThree here.
 * 
 * @author Hyson Leung
 * @version Jan 2014
 */
public class SelectThree extends MenuObjects
{
    /**
     * Act - do whatever the SelectThree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)) Greenfoot.setWorld(new LevelThree());
    }    
}
