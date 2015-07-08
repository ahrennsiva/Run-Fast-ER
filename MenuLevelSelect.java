import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MenuLevelSelect is a Greenfoot World. The screen for selecting the different game levels.
 * 
 * @author Hyson Leung
 * @version January 2015
 */
public class MenuLevelSelect extends World
{
    /**
     * Constructor for objects of class MenuLevelSelect.
     * 
     */
    public MenuLevelSelect()
    {    
        super(960, 640, 1);
        addObject(new SelectOne(), 230, 320);
        addObject(new SelectTwo(), 480, 320);
        addObject(new SelectThree(), 730, 320);
        addObject(new Back(), 100, 590);
    }
}
