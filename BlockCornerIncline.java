import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BlockCornerIncline is a Greenfoot Actor and a subclass of NotSlopes.
 * Write a description of class blockCornerIncline here.
 * 
 * @author Rhys Young, Hyson Leung, Ahrenn Sivananthan
 * @version Jan 2014
 */
public class BlockCornerIncline extends NotSlopes
{
    public BlockCornerIncline(int level){
        super();
        
        if(level == 1)setImage("blockCornerIncline1.png");
        else if(level == 2)setImage("blockCornerIncline1.png");
        else if(level == 3)setImage("blockCornerIncline2.png");
    }
    
    /**
     * Act - do whatever the blockCornerIncline wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        objectFunctions();
    }    
}
