import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BlockCornerDecline is a Greenfoot Actor and a subclass of NotSlopes.
 * Write a description of class blockCornerDecline here.
 * 
 * @author Rhys Young, Hyson Leung, Ahrenn Sivananthan
 * @version Jan 2014
 */
public class BlockCornerDecline extends NotSlopes
{
    public BlockCornerDecline(int level){
        super();
        if(level == 1)setImage("blockCornerDecline1.png");
        else if(level == 2)setImage("blockCornerDecline1.png");
        else if(level == 3)setImage("blockCornerDecline2.png");
    }
    
    /**
     * Act - do whatever the blockCornerDecline wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        objectFunctions();
    }    
}
