import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Back is a Greenfoot Actor and a subclass of MenuObjects.
 * Write a description of class Back here.
 * 
 * @author Hyson Leung
 * @version Jan 2014
 */
public class Back extends MenuObjects
{
    /**
     * Act - do whatever the Back wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            Menu menu = new Menu();
            Greenfoot.setWorld(menu);
        }    
    }    
}
