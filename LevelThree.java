import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * LevelThree is a Greenfoot World and a subclass of WorldMap.
 * Write a description of class LevelThree here.
 * 
 * @author Hyson Leung, Etienne Yiu
 * @version January 2015
 */
public class LevelThree extends WorldMap
{
    public LevelThree()
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
        level = 3;
        backgroundMusic = new GreenfootSound ("Level3.mp3");
    }
}
