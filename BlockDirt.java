import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BlockDirt is a Greenfoot Actor and a subclass of NotSlopes.
 * Write a description of class blockDirt here.
 * 
 * @author Rhys Young, Hyson Leung
 * @version Jan 2014
 */
public class BlockDirt extends NotSlopes
{
    public BlockDirt(int level){
        super();
        
        if(level == 1)setImage("blockWall1.png");
        else if(level == 2)setImage("blockWall1.png");
        else if(level == 3)setImage("blockWall2.png");
    }
    
    /**
     * Act - do whatever the blockDirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        objectFunctions();
    }    
}
