import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.lang.NumberFormatException;
import java.awt.Color;
import java.util.List;
/**
 * WorldMap is a Greenfoot World.
 * Write a description of class WorldMap here.
 * 
 * @author Rhys Young, Etienne Yiu, Hanson Ng, Hyson Leung, Ahrenn Sivananthan
 * @version January 2015
 */
abstract class WorldMap extends World
{
    static Scanner file;
    static final int height =  10;
    static String[][] blocks;
    static int run = 0;

    int counterReset = 64;
    int counter = 32;
    int index = 0;
    int length = 253;
    long pauseTime;

    boolean spawnMap = true;

    Player player = new Player();
    Enemy enemy = new Enemy();
    ASImgBars healthBar;
    Timer timer;
    boolean pause = false;

    boolean mapScroll = false;
    int scrollSpeed;

    int level = 1;

    GreenfootSound backgroundMusic = new GreenfootSound ("BackgroundMusic.mp3");

    public WorldMap()
    {
        super(960, 640, 1);

        prepare();

        counterReset /= scrollSpeed;
        counter /= scrollSpeed;
        counter++;

        run = 0;

        setPaintOrder(MenuObjects.class, ASImgBars.class, Timer.class, Border.class, HealthUp.class, PowerUp.class, Player.class, Enemy.class, Bullet.class, Objects.class);

        addObject(player, 224, 351);

        healthBar = new ASImgBars(player.currentHealth(),player.maxHealth(),32.0);   
        addObject(healthBar, 122, 28);

        timer = new Timer();
        addObject(timer, 180, 64);

        addObject(new Border(), 480, 320);

        addObject(new Block(level), 32, 479);
        addObject(new BlockDirt(level), 32, 543);
        addObject(new BlockDirt(level), 32, 607);
        addObject(new Block(level), 96, 479);
        addObject(new BlockDirt(level), 96, 543);
        addObject(new BlockDirt(level), 96, 607);
        addObject(new Block(level), 160, 479);
        addObject(new BlockDirt(level), 160, 543);
        addObject(new BlockDirt(level), 160, 607);
        addObject(new Block(level), 224, 479);
        addObject(new BlockDirt(level), 224, 543);
        addObject(new BlockDirt(level), 224, 607);
        addObject(new Block(level), 288, 479);
        addObject(new BlockDirt(level), 288, 543);
        addObject(new BlockDirt(level), 288, 607);
        addObject(new Block(level), 352, 479);
        addObject(new BlockDirt(level), 352, 543);
        addObject(new BlockDirt(level), 352, 607);
        addObject(new Block(level), 416, 479);
        addObject(new BlockDirt(level), 416, 543);
        addObject(new BlockDirt(level), 416, 607);
        addObject(new Block(level), 480, 479);
        addObject(new BlockDirt(level), 480, 543);
        addObject(new BlockDirt(level), 480, 607);
        addObject(new Block(level), 544, 479);
        addObject(new BlockDirt(level), 544, 543);
        addObject(new BlockDirt(level), 544, 607);
        addObject(new Block(level), 608, 479);
        addObject(new BlockDirt(level), 608, 543);
        addObject(new BlockDirt(level), 608, 607);
        addObject(new Block(level), 672, 479);
        addObject(new BlockDirt(level), 672, 543);
        addObject(new BlockDirt(level), 672, 607);
        addObject(new Block(level), 736, 479);
        addObject(new BlockDirt(level), 736, 543);
        addObject(new BlockDirt(level), 736, 607);
        addObject(new Block(level), 800, 479);
        addObject(new BlockDirt(level), 800, 543);
        addObject(new BlockDirt(level), 800, 607);
        addObject(new Block(level), 864, 479);
        addObject(new BlockDirt(level), 864, 543);
        addObject(new BlockDirt(level), 864, 607);
        addObject(new Block(level), 928, 479);
        addObject(new BlockDirt(level), 928, 543);
        addObject(new BlockDirt(level), 928, 607);
    }
    
    /**
     * Method for level preparation
     */
    abstract void prepare();

    /**
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(Greenfoot.isKeyDown("escape")){
            if(getObjects(PauseGame.class).size() == 0) addObject(new PauseGame(), 480, 320);
            if(getObjects(Resume.class).size() == 0) addObject(new Resume(), 480, 255);
            if(getObjects(TryAgain.class).size() == 0){
                if(level == 1) addObject(new TryAgain(new LevelOne()), 480, 385);
                if(level == 2) addObject(new TryAgain(new LevelTwo()), 480, 385);
                if(level == 3) addObject(new TryAgain(new LevelThree()), 480, 385);
            }
            if(getObjects(Quit.class).size() == 0) addObject(new Quit(), 480, 515);

            pauseTime = System.nanoTime();
            backgroundMusic.pause();
            pause = true;
        }

        if(pause){
            mapScroll = false;
            spawnMap = false;
        }else{
            spawnMap = true;
            mapScroll = true;
            if(!backgroundMusic.isPlaying()){
                backgroundMusic.playLoop();
            }
        }

        if(run == 0){
            if(level == 1)loadMap("Level 1.txt", length);
            else if(level == 2)loadMap("Level 2.txt", length);
            else if(level == 3)loadMap("Level 3.txt", length);
            run = 1;
        }

        if(spawnMap == true){
            counter--;
            if(counter == 0){
                if(index < length){
                    spawnObject(height, index);
                    index++;
                    counter = counterReset;
                }
                if(index == length){
                    addObject(enemy, 800, 478);
                }
            }
        }

        if(counter <= -1){
            mapScroll = false;
            spawnMap = false;
        }

        //if the player has died
        if(player.currentHealth() == 0){
            removeObject(player); //removes the player from this world
            backgroundMusic.stop();

            //sets the screen to the lose screen
            if(level == 1) Greenfoot.setWorld(new GameOver(timer.getTimeDifference(timer.startTime), new LevelOne()));
            else if(level == 2) Greenfoot.setWorld(new GameOver(timer.getTimeDifference(timer.startTime), new LevelTwo()));
            else if(level == 3) Greenfoot.setWorld(new GameOver(timer.getTimeDifference(timer.startTime), new LevelThree()));
        }

        //if the final enemy has died 
        if(enemy.enemyLives == 0){
            this.removeObject(enemy); //removes the enemy from this world
            backgroundMusic.stop();
            Greenfoot.setWorld(new Menu()); //no end screen
        }
    }

    /**
     * Returns if the map is paused or not.
     * @return pause Returns a boolean either true or false if the map is paused.
     */
    public boolean isPause(){
        return pause;
    }

    /**
     * Returns the scrolling condition of the map.
     * @return mapScroll Returns a boolean either true or false if the map is currently scrolling.
     */
    public boolean isScrolling(){
        return mapScroll;
    }

    /**
     * Returns the scrolling speed of the map.
     * @return scrollSpeed Returns an integer of the speed that the map is scrolling.
     */
    public int scrollSpeed(){
        return scrollSpeed;
    }

    /**
     * Unpause game
     * 
     * @param fileName unpauses the game
     */
    public void unpause(){
        pause = false;

        if(getObjects(PauseGame.class).size() != 0) removeObjects(getObjects(PauseGame.class));
        if(getObjects(Resume.class).size() != 0)removeObjects(getObjects(Resume.class));
        if(getObjects(TryAgain.class).size() != 0)removeObjects(getObjects(TryAgain.class));
        if(getObjects(Quit.class).size() != 0)removeObjects(getObjects(Quit.class));
        timer.startTime = timer.startTime + (System.nanoTime() - pauseTime);
    }

    /**
     * Loads the specified map on to the screen. 
     * .
     * @param fileName The name of the textfill which this method will read from to load the map
     * @param length   The length of the map that will be loaded.
     */
    public void loadMap(String fileName, int length){
        blocks = new String[height][length];
        String blocksInLine[];
        String curLine;

        try{
            file = new Scanner (new File (fileName));

            for (int i = 0; i < height; i++){
                curLine = file.nextLine();
                blocksInLine = curLine.split(" ");
                blocks[i] = blocksInLine;
            }
        }
        catch(FileNotFoundException e)//checks to make sure the file is found
        {
            //System.out.println("File not found: " + e);
        }
        catch(NoSuchElementException e)//checks to make sure theres still another line
        {
            //System.out.println("Error:"  + e);
        }
        finally // closes the file
        {
            if(file != null) file.close();
        }
    }

    /**
     * Identifies the block based on its number and then uses the ySpawn of the block to add it into the world.
     * @param curBlock The number of the block as it appears in the text file.
     * @param ySpawn The ySpawn that is needed for that block after the ySpawn method has been run.
     */
    private void getBlock(int curBlock, int ySpawn){
        switch(curBlock){
            case 1:
            addObject(new Block(level), 959, ySpawn);
            break;
            case 2:
            addObject(new PowerUp(), 959, ySpawn);
            break;
            case 3:
            addObject(new BlockIncline(level), 959, ySpawn);
            break;
            case 4:
            addObject(new BlockDecline(level), 959, ySpawn);
            break;
            case 5:
            addObject(new BlockCornerIncline(level), 959, ySpawn);
            break;
            case 6:
            addObject(new BlockCornerDecline(level), 959, ySpawn);
            break;
            case 7:
            addObject(new WaterTop(), 959, ySpawn);
            break;
            case 8:
            addObject(new Spikes(), 959, ySpawn);
            break;
            case 9:
            addObject(new BlockDirt(level), 959, ySpawn);
            break;
            case 10:
            addObject(new WaterBottom(), 959, ySpawn);
            break;
        }
    }

    /**
     * Gets the ySpawn of a block based on the row that that block is in a text file.
     * @param num The row that the block is in, in the text file.
     */
    private int getYSpawn(int num)
    {
        switch(num){
            case 0:
            return 31;
            case 1:
            return 95;
            case 2:
            return 159;
            case 3:
            return 223;
            case 4:
            return 287;
            case 5:
            return 351;
            case 6:
            return 415;
            case 7:
            return 479;
            case 8:
            return 543;
            case 9:
            return 607;
        }

        return 0;
    }

    /**
     * Spawns each object in a column.
     * @param height The height of the column.
     * @param index The number of the column to be spawned.
     */
    public void spawnObject(int height, int index){
        for(int k = 0; k < height; k++){
            try{
                int curBlock = Integer.parseInt(blocks[k][index]);
                int ySpawn = getYSpawn(k);
                getBlock(curBlock, ySpawn);
            }catch( NumberFormatException e){
                //System.out.println("Error " + e);
            }
        }
    }
}
