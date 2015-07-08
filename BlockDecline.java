import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BlockDecline is a Greenfoot Actor and a subclass of Blocks.
 * Write a description of class blockDecline here.
 * 
 * @author Rhys Young, Hyson Leung, Ahrenn Sivananthan
 * @version Jan 2014
 */
public class BlockDecline extends Blocks
{
    public BlockDecline(int level){
        super();
        
        if(level == 1)setImage("blockDecline1.png");
        else if(level == 2)setImage("blockDecline1.png");
        else if(level == 3)setImage("blockDecline2.png");
    }
    
    /**
     * Act - do whatever the blockDecline wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        objectFunctions();
    }    
}
