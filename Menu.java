import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main is a Greenfoot World. The main menu of the game.
 * 
 * @author Hyson Leung
 * @version January 2015
 */
public class Menu extends World
{

    /**
     * Constructor for the different buttons of class Menu.
     */
    public Menu()
    {    
        super(960, 640, 1); 
        addObject(new Start(), 700, 255);
        addObject(new Instructions(), 700, 385);
        addObject(new MenuQuit(), 700, 515);
    }
}
