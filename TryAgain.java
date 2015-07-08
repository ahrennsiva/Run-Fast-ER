import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TryAgain is a Greenfoot Actor and a subclass of MenuObjects.
 * Write a description of class TryAgain here.
 * 
 * @author Hyson Leung
 * @version Jan 2014
 */
public class TryAgain extends MenuObjects
{
    World w;
    
    public TryAgain(World w){
        this.w = w;
    }
    
    /**
     * Act - do whatever the TryAgain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if (Greenfoot.mouseClicked(this)) Greenfoot.setWorld(w);
    }    
}
