import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Block is a Greenfoot Actor and a subclass of NotSlopes.
 * Write a description of class block here.
 * 
 * @author Rhys Young, Hyson Leung
 * @version Jan 2014
 */
public class Block extends NotSlopes
{
    public Block(int level){
        super();
        
        if(level == 1)setImage("block1.png");
        else if(level == 2)setImage("block1.png");
        else if(level == 3)setImage("block2.png");
    }
    
    /**
     * Act - do whatever the block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        objectFunctions();
    }    
}
