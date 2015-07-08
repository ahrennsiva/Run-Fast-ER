import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * LevelTwo is a Greenfoot World and a subclass of WorldMap.
 * 
 * @author Hyson Leung, Etienne Yiu
 * @version January 2015
 */
public class LevelTwo extends WorldMap
{

    public LevelTwo()
    {
        super();
    }
    
    /**
     * Prepares world
     * 
     * @param sets values to use in parent world methods
     */
    public void prepare(){
        scrollSpeed = 2;
        level = 2;
    }
}
