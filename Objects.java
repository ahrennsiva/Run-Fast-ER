import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.BufferedImage;

/**
 * Objects is a Greenfoot Actor.
 * Write a description of class Objects here.
 * 
 * @author Hyson Leung, Ahrenn Sivananthan
 * @version January 2015
 */
abstract class Objects extends Actor
{
    public boolean move = true;

    /**
     * The main functions that all objects do excluding power ups.
     */
    public void objectFunctions()
    {
        if(((WorldMap) getWorld()).isScrolling())move(-((WorldMap) getWorld()).scrollSpeed());
        if(getX() < 1){
            World world = getWorld();
            world.removeObject(this);
        }
    }

    /**
     * 
     */
    public int getPixel(int x, int y){
        BufferedImage bi = this.getImage().getAwtImage();

        return bi.getRGB(x, y);
    }
}
