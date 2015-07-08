import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BlockIncline is a Greenfoot Actor and a subclass of Blocks.
 * Write a description of class blockIncline here.
 * 
 * @author Rhys Young, Hyson Leung, Ahrenn Sivananthan
 * @version Jan 2014
 */
public class BlockIncline extends Blocks
{
    public BlockIncline(int level){
        super();
        
        if(level == 1)setImage("blockIncline1.png");
        else if(level == 2)setImage("blockIncline1.png");
        else if(level == 3)setImage("blockIncline2.png");
    }
    
    /**
     * Act - do whatever the blockIncline wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        objectFunctions();
    }    
}
