import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MenuInstructions is a Greenfoot World. The instructions menu for the game.
 * 
 * @author Ahrenn Sivananthan
 * @version January 2015
 */
public class MenuInstructions extends World
{
    /**
     * Constructor for objects of class MenuInstructions.
     */
    public MenuInstructions()
    {
        super(960, 640, 1); 
        addObject(new Back(), 100, 590);
        addObject(new InstructionsPanel(),476,388);
        
    }
}
