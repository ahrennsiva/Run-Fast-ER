import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * LevelOne is a Greenfoot World and a subclass of WorldMap.
 * Write a description of class LevelOne here.
 * 
 * @author Hyson Leung, Etienne Yiu
 * @version January 2015
 */
public class LevelOne extends WorldMap
{
    public LevelOne()
    {
        super();
    }

    /**
     * Prepares world
     * 
     * @param sets values to use in parent world methods
     */
    public void prepare(){
        scrollSpeed = 4;
        level = 1;
    }

}
