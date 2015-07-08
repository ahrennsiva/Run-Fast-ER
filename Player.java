import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.lang.ArrayIndexOutOfBoundsException;
/**
 * Write a description of class Player here.
 * 
 * @author Hyson, Hanson, Ahrenn 
 * @version (a version number or a date)
 */
public class Player extends Character
{
    private int hitboxY = 60; //character height hitbox
    private int hitboxX = 10; //character width hitbox

    private boolean invincibility = false;
    private int invincibilityCounter = 0;

    private boolean jumpBonus = false;
    private int jumpBonusCounter = 0;

    private int playerLives = 3; //amount of lives
    private int playerMaxLives = 5; //max lives

    private int speed[] = {0,1,1,1,1,2,2,2,3,3,4,5,6}; //actual speed
    private int speedIndex = 0;

    private int ySpeed[] = {16,8,8,8,4,4,4,4,2,2,2,2,2,2,1,1,1,1,1,1}; //normal jump
    private int ySpeedUpgrade[] = {19,11,11,11,11,7,7,7,7,5,5,5,3,3,3,2,2,1,1,1}; //for jump with power up
    private int jumpIndex = 0;

    private boolean jump = false; // is jumping
    private boolean canJump = false; //can jump
    private boolean fall = true; //is falling

    private boolean moving = false; // if moving left or right
    private boolean movingLeft = false; // detection for pivot
    private boolean movingRight = false; // detection for pivot
    private String lastDir = "r"; // last direction left or right

    private int shootDelay = 0;

    private int climbHeight = 7; //climb height (mostly for pixels)

    private JumpMarker jm = new JumpMarker(); //marks last land that is not falling or jumping

    private int runIndex = 4; //run animation frame counter

    //SOUNDS
    private GreenfootSound healthUpSound = new GreenfootSound ("HealthUp.mp3");
    private GreenfootSound jumpBoostSound = new GreenfootSound ("JumpBoost.mp3");
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if(!((WorldMap) getWorld()).isPause()){
            //spawns and removes jump marker as there is a block underneath the character
            if(getOneObjectAtOffset(0, hitboxY/2 + 1, Blocks.class) != null){
                getWorld().removeObject(jm);
                getWorld().addObject(jm, getX(), getY());
            }

            //move player with map
            if(((WorldMap) getWorld()).isScrolling()){
                move(-((WorldMap) getWorld()).scrollSpeed());
                if((getX() < 63 || getY() > 638) && invincibility == false){
                    playerDeath();
                }
                //moves player ahead of back death area
                if(getX() < 63){
                    setLocation(65, getY());
                }
                //moves player to before fell out of screen
                if(getY() > 638 && jm != null) setLocation(jm.getX(), jm.getY());
            }

            //Bullet delay time
            if(shootDelay > 0){
                shootDelay -= 1;
            }

            if(isTouching(PowerUp.class)){
                powerUp();
                removeTouching(PowerUp.class);
            }

            //invincibility on hitting spikes and water and moves player back to (probably) safest last location
            if(hazardCollision() && !invincibility){
                playerDeath();
                if(jm != null) setLocation(jm.getX(), jm.getY());
            }

            moving = false;

            jump();

            fall();

            playerKeys();

            //if character invincibility (after respawn from death)
            if(invincibility){
                tempInvincibility();
            }

            //if jumpBonus picked up
            if(jumpBonus){
                tempJumpBonus();
            }

            //movement speed tracked through speed array
            if(moving && speedIndex < speed.length-1){
                speedIndex++;
            }else if(!moving && speedIndex > 0){
                //sliding
                if(lastDir.equals("r")){
                    slideRight();
                }else{
                    slideLeft();
                }
                if(speedIndex > 0){
                    speedIndex--;
                }
            }

            if(speedIndex == 0){
                movingLeft = false;
                movingRight = false;
            }

            sideCollision();

            //set sprites
            if(jump || fall){
                if(lastDir.equals("r")) setImage("ReimuJumpR.png");
                else setImage("ReimuJumpL.png");
            }else{
                if(!moving){
                    if(lastDir.equals("r")) setImage("ReimuStandR.png");
                    else setImage("ReimuStandL.png");
                }else{
                    if(runIndex > 28) runIndex = 4;
                    if(lastDir.equals("r")) setImage("Run/RunR" + (runIndex/4) + ".png");
                    else setImage("Run/RunL" + (runIndex/4) + ".png");
                    runIndex++;
                }
            }

            if (invincibilityCounter%3 == 0){
                this.getImage().setTransparency(255); //bring back to full
            }else{
                this.getImage().setTransparency(75); //slightly invisible
            }
        }
    }

    /**
     * Checks if player collides with hazards
     * if there is an existing hazard within the amount of x/y it is going to displace and then check if it is going to collide with a visible pixel
     */
    private boolean hazardCollision(){
        Hazards hazard;
        
        //right
        for(int x = 0; x <= speed[speedIndex]; x++){
            for(int y = hitboxY/2; y >= -hitboxY/2; y--){
                hazard = (Hazards) getOneObjectAtOffset(hitboxX/2 + x, y, Hazards.class);

                if(hazard != null){
                    if(hazard.getPixel((getX() + hitboxX/2 + x) - (hazard.getX() - 32), (getY() + y) - (hazard.getY() - 32)) != 0){
                        return true;
                    }
                }
            }
        }

        //left
        for(int x = 0; x <= speed[speedIndex]; x++){
            for(int y = hitboxY/2 ; y >= -hitboxY/2; y--){
                hazard = (Hazards) getOneObjectAtOffset(-hitboxX/2 - x, y, Hazards.class);

                if(hazard != null){
                    if(hazard.getPixel((getX() - hitboxX/2 - x) - (hazard.getX() - 32), (getY() + y) - (hazard.getY() - 32)) != 0){
                        return true;
                    }
                }
            }
        }

        //up
        for(int y = 0; y <= ySpeed[jumpIndex]; y++){
            for(int x = -hitboxX/2; x <= hitboxX/2; x++){
                hazard = (Hazards) getOneObjectAtOffset(x, -hitboxY/2 - y, Hazards.class);

                if(hazard != null){
                    if(hazard.getPixel((getX() + x) - (hazard.getX() - 32), (getY() - hitboxY/2 - y) - (hazard.getY() - 32)) != 0){
                        return true;
                    }
                }
            }
        }

        //down
        for(int y = 0; y <= ySpeed[jumpIndex]; y++){
            for(int x = -hitboxX/2; x <= hitboxX/2; x++){
                hazard = (Hazards) getOneObjectAtOffset(x, -hitboxY/2 - y, Hazards.class);

                if(hazard != null){
                    if(hazard.getPixel((getX() + x) - (hazard.getX() - 32), (getY() - hitboxY/2 - y) - (hazard.getY() - 32)) != 0){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Player controls
     */
    private void playerKeys(){
        //right
        if(Greenfoot.isKeyDown("right") && !movingLeft && getX() < 895){
            pixelCollisionHR();
            movingRight = true;
            moving = true;
        }

        //left
        if(Greenfoot.isKeyDown("left") && !movingRight && getX() > 64){
            pixelCollisionHL();
            movingLeft = true;
            moving = true;
        }

        //up
        if(Greenfoot.isKeyDown("up")){
            if(canJump && !jump && !fall){
                jump = true;
            }
        }

        //shoot
        if (Greenfoot.isKeyDown("space")&& (shootDelay == 0)){
            getWorld().addObject(new Bullet(this), getX(), getY());
            shootDelay = 40;
        }
    }

    /**
     * Checks if there are blocks left/right of the player and move the player back away from the wall so they don't stick
     */
    private void sideCollision(){
        Blocks block;

        //check right
        for(int y = hitboxY/2 - 1; y > -hitboxY/2; y--){
            block = (Blocks) getOneObjectAtOffset(hitboxX/2 + 2, y, NotSlopes.class);

            if(block != null){
                if(block.getPixel((getX() + hitboxX/2 + 2) - (block.getX() - 32), (getY() + y) - (block.getY() - 32)) != 0){
                    setLocation(getX() - 2, getY());
                    break;
                }
            }
        }

        //check left
        for(int y = hitboxY/2 - 1; y > -hitboxY/2; y--){
            block = (Blocks) getOneObjectAtOffset(-hitboxX/2 - 2, y, NotSlopes.class);

            if(block != null){
                if(block.getPixel((getX() - hitboxX/2 - 2) - (block.getX() - 32), (getY() + y) - (block.getY() - 32)) != 0){
                    setLocation(getX() + 2, getY());
                    break;
                }
            }
        }
    }

    /**
     * Checks collision for blocks in the right direction
     */
    private void pixelCollisionHR(){
        boolean setLocation = true;

        Blocks block;

        //checks pixel collision for slope
        outerloop:
        for(int xSlope = speed[speedIndex]; xSlope > 0; xSlope--){
            block = (Blocks) getOneObjectAtOffset(hitboxX/2 + xSlope, hitboxY/2 - 1, Blocks.class);

            if(block != null){
                for(int climb = 1; climb <= climbHeight; climb++){
                    if(getOneObjectAtOffset(hitboxX/2 + xSlope, hitboxY/2 - 1 - climb, Blocks.class) == null){
                        setLocation(getX() + xSlope, getY() - climb);
                        setLocation = false;
                        break outerloop;
                    }

                    block = (Blocks) getOneObjectAtOffset(hitboxX/2 + xSlope, hitboxY/2 - 1 - climb, Blocks.class);

                    if(block.getPixel((getX() + hitboxX/2 + xSlope) - (block.getX() - 32), (getY() + hitboxY/2 - 1 - climb) - (block.getY() - 32)) == 0){
                        setLocation(getX() + xSlope, getY() - climb);
                        setLocation = false;
                        break outerloop;
                    }
                }
            }
        }

        //checks pixel collision for blocks if no slope
        if(setLocation){
            outerloop:
            for(int x = 0; x <= speed[speedIndex]; x++){
                for(int y = hitboxY/2 - 1; y > -hitboxY/2; y--){
                    block = (Blocks) getOneObjectAtOffset(hitboxX/2 + x, y, Blocks.class);

                    if(block != null){
                        if(block.getPixel((getX() + hitboxX/2 + x) - (block.getX() - 32), (getY() + y) - (block.getY() - 32)) != 0){
                            setLocation(getX() + x, getY());
                            setLocation = false;
                            break outerloop;
                        }
                    }
                }
            }
        }

        if(setLocation){
            setLocation(getX() + speed[speedIndex], getY());
        }

        lastDir = "r";
        setImage("ReimuStandR.png");
    }

    /**
     * Checks collision for blocks in the left direction
     */
    private void pixelCollisionHL(){
        boolean setLocation = true;

        Blocks block;

        //checks pixel collision for slope
        outerloop:
        for(int xSlope = speed[speedIndex]; xSlope > 0; xSlope--){
            block = (Blocks) getOneObjectAtOffset(-hitboxX/2 - xSlope, hitboxY/2 - 1, Blocks.class);

            if(block != null){
                for(int climb = 1; climb <= climbHeight; climb++){
                    if(getOneObjectAtOffset(-hitboxX/2 - xSlope, hitboxY/2 - 1 - climb, Blocks.class) == null){
                        setLocation(getX() - xSlope, getY() - climb);
                        setLocation = false;
                        break outerloop;
                    }

                    block = (Blocks) getOneObjectAtOffset(-hitboxX/2 - xSlope, hitboxY/2 - 1 - climb, Blocks.class);

                    if(block.getPixel((getX() - hitboxX/2 - xSlope) - (block.getX() - 32), (getY() + hitboxY/2 - 1 - climb) - (block.getY() - 32)) == 0){
                        setLocation(getX() - xSlope, getY() - climb);
                        setLocation = false;
                        break outerloop;
                    }
                }
            }
        }

        //checks pixel collision if no slope
        if(setLocation){
            outerloop:
            for(int x = 0; x <= speed[speedIndex]; x++){
                for(int y = hitboxY/2 - 1; y > -hitboxY/2; y--){
                    block = (Blocks) getOneObjectAtOffset(-hitboxX/2 - x, y, Blocks.class);

                    if(block != null){
                        if(block.getPixel((getX() - hitboxX/2 - x) - (block.getX() - 32), (getY() + y) - (block.getY() - 32)) != 0){
                            setLocation(getX() - x, getY());
                            setLocation = false;
                            break outerloop;
                        }
                    }
                }
            }
        }

        if(setLocation){
            setLocation(getX() - speed[speedIndex], getY());
        }

        lastDir = "l";
        setImage("ReimuStandL.png");
    }

    /**
     * Slide character right until no more speed/momentum allowing chracter to move in other direction
     */
    private void slideRight(){
        boolean setLocation = true;

        Blocks block;

        //check for slopes
        outerloop:
        for(int xSlope = speed[speedIndex]; xSlope > 0; xSlope--){
            block = (Blocks) getOneObjectAtOffset(hitboxX/2 + xSlope, hitboxY/2 - 1, Blocks.class);

            if(block != null){
                for(int climb = 1; climb <= climbHeight; climb++){
                    if(getOneObjectAtOffset(hitboxX/2 + xSlope, hitboxY/2 - 1 - climb, Blocks.class) == null){
                        setLocation(getX() + xSlope, getY() - climb);
                        setLocation = false;
                        break outerloop;
                    }

                    block = (Blocks) getOneObjectAtOffset(hitboxX/2 + xSlope, hitboxY/2 - 1 - climb, Blocks.class);

                    if(block.getPixel((getX() + hitboxX/2 + xSlope) - (block.getX() - 32), (getY() + hitboxY/2 - 1 - climb) - (block.getY() - 32)) == 0){
                        setLocation(getX() + xSlope, getY() - climb);
                        setLocation = false;
                        break outerloop;
                    }
                }
            }
        }

        //check for blocks
        if(setLocation){
            outerloop:
            for(int x = 0; x <= speed[speedIndex]; x++){
                for(int y = hitboxY/2 - 1; y > -hitboxY/2; y--){
                    block = (Blocks) getOneObjectAtOffset(hitboxX/2 + x, y, Blocks.class);

                    if(block != null){
                        if(block.getPixel((getX() + hitboxX/2 + x) - (block.getX() - 32), (getY() + y) - (block.getY() - 32)) != 0){
                            setLocation(getX() + x, getY());
                            setLocation = false;
                            break outerloop;
                        }
                    }
                }
            }
        }

        if(setLocation){
            setLocation(getX() + speed[speedIndex], getY());
        }
    }

    /**
     * Slide character left until no more speed/momentum allowing chracter to move in other direction
     */
    private void slideLeft(){
        boolean setLocation = true;

        Blocks block;

        //check for slopes
        outerloop:
        for(int xSlope = speed[speedIndex]; xSlope > 0; xSlope--){
            block = (Blocks) getOneObjectAtOffset(-hitboxX/2 - xSlope, hitboxY/2 - 1, Blocks.class);

            if(block != null){
                for(int climb = 1; climb <= climbHeight; climb++){
                    if(getOneObjectAtOffset(-hitboxX/2 - xSlope, hitboxY/2 - 1 - climb, Blocks.class) == null){
                        setLocation(getX() - xSlope, getY() - climb);
                        setLocation = false;
                        break outerloop;
                    }

                    block = (Blocks) getOneObjectAtOffset(-hitboxX/2 - xSlope, hitboxY/2 - 1 - climb, Blocks.class);

                    if(block.getPixel((getX() - hitboxX/2 - xSlope) - (block.getX() - 32), (getY() + hitboxY/2 - 1 - climb) - (block.getY() - 32)) == 0){
                        setLocation(getX() - xSlope, getY() - climb);
                        setLocation = false;
                        break outerloop;
                    }
                }
            }
        }

        //check for blocks
        if(setLocation){
            outerloop:
            for(int x = 0; x <= speed[speedIndex]; x++){
                for(int y = hitboxY/2 - 1; y > -hitboxY/2; y--){
                    block = (Blocks) getOneObjectAtOffset(-hitboxX/2 - x, y, Blocks.class);

                    if(block != null){
                        if(block.getPixel((getX() - hitboxX/2 - x) - (block.getX() - 32), (getY() + y) - (block.getY() - 32)) != 0){
                            setLocation(getX() - x, getY());
                            setLocation = false;
                            break outerloop;
                        }
                    }
                }
            }
        }

        if(setLocation){
            setLocation(getX() - speed[speedIndex], getY());
        }
    }

    /**
     * Allows for the character to jump
     */
    private void jump(){
        Blocks block;
        boolean setLocation = true;

        if(jumpIndex < ySpeed.length-1 && !fall && jump){
            //jump bonus jump
            if(jumpBonus){
                //check for blocks to bump head on
                outerloop:
                for(int y = 0; y <= ySpeed[jumpIndex]; y++){
                    for(int x = -hitboxX/2 - 1; x < hitboxX/2; x++){
                        block = (Blocks) getOneObjectAtOffset(x, -hitboxY/2 - y, Blocks.class);

                        if(block != null){
                            if(block.getPixel((getX() + x) - (block.getX() - 32), (getY() - hitboxY/2 - y) - (block.getY() - 32)) != 0){
                                setLocation(getX(), getY() - y);
                                jump = false;
                                fall = true;
                                break outerloop;
                            }
                        }
                    }
                }
                //no blocks found
                if(setLocation){
                    setLocation(getX(), getY() - ySpeedUpgrade[jumpIndex]);
                }
            }else{
                //normal jump
                
                //check for blocks to bump head on
                outerloop:
                for(int y = 0; y <= ySpeed[jumpIndex]; y++){
                    for(int x = -hitboxX/2 - 1; x < hitboxX/2; x++){
                        block = (Blocks) getOneObjectAtOffset(x, -hitboxY/2 - y, Blocks.class);

                        if(block != null){
                            if(block.getPixel((getX() + x) - (block.getX() - 32), (getY() - hitboxY/2 - y) - (block.getY() - 32)) != 0){
                                setLocation(getX(), getY() - y);
                                jump = false;
                                fall = true;
                                break outerloop;
                            }
                        }
                    }
                }
                //no blocks found
                if(setLocation){
                    setLocation(getX(), getY() - ySpeed[jumpIndex]);
                }
            }
            jumpIndex++;
        }else if(jumpIndex == ySpeed.length-1){
            jump = false;
            fall = true;
        }
    }

    /**
     * Makes character fall until found block underneath
     */
    private void fall(){
        if(!jump){
            boolean setLocation = true;

            Blocks block;

            //check if block underneath for y drop distance
            outerloop:
            for(int y = 0; y <= ySpeed[jumpIndex]; y++){
                for(int x = -hitboxX/2 - 1; x < hitboxX/2; x++){
                    block = (Blocks) getOneObjectAtOffset(x, hitboxY/2 + y, Blocks.class);

                    if(block != null){
                        if(block.getPixel((getX() + x) - (block.getX() - 32), (getY() + hitboxY/2 + y) - (block.getY() - 32)) != 0){
                            setLocation(getX(), getY() + y);
                            canJump = true;
                            fall = false;
                            jumpIndex = 0;
                            setLocation = false;
                            break outerloop;
                        }
                    }
                }
            }

            //no block found so normal drop
            if(setLocation){
                setLocation(getX(), getY() + ySpeed[jumpIndex]);
                canJump = false;
                fall = true;
            }

            if(jumpIndex != 0){
                jumpIndex--;
            }
        }
    }

    /**
     * When the player dies
     */
    private void playerDeath(){
        invincibility = true; //after health reduced, temporary invincibility
        playerLives--;
        ((WorldMap) getWorld()).healthBar.update(playerLives); 

        invincibilityCounter = 0;
    }

    /**
     * Randomize the powerup the player gets when colliding with powerup
     */
    private void powerUp(){
        Random rand = new Random();
        int choice = rand.nextInt((2 - 1) + 1) + 1;
        if(choice == 1){
            getWorld().addObject(new HealthUp(), 480, 320);
            playerLives++; //increases the player lives by 1
            ( (WorldMap) getWorld() ).healthBar.update(playerLives); //INSTEAD OF WORLD MAP NEEDS TO BE FROM A VARIABLE SO THAT CAN GET FROM THE DIFF WORLDS
            healthUpSound.play();
        } else if (choice == 2)
        {
            getWorld().addObject(new JumpBoost(), 480, 320);
            jumpBonus = true;
            jumpBoostSound.play();
        } 
    }

    /**
     * Player temp jump bonus after bonus pick up for a few seconds
     */
    private void tempJumpBonus(){
        jumpBonusCounter++;
        if (jumpBonusCounter == 2000){
            jumpBonus = false;
            jumpBonusCounter = 0;
        }
    }

    /**
     * Player temp invincibility after death for a few seconds
     */
    private void tempInvincibility(){
        invincibilityCounter++;
        if (invincibilityCounter == 200){
            invincibility = false;
            invincibilityCounter = 0;
        }
    }

    /**
     * Find last direction player was moving
     */
    public String findLastDir(){
        return lastDir;
    }

    /**
     * Increase player lives
     */
    public void healthIncrease(){
        playerLives++;
    }

    /**
     * Decrease player lives
     */
    public void healthDecrease(){
        playerLives--;
    }

    /**
     * Gives player's current amounf of lives
     * @return current amount of player lives
     */
    public int currentHealth(){
        return playerLives;
    }
    
    /**
     * Gives player's max amount of lives
     * @return max amount of player lives
     */
    public int maxHealth(){
        return playerMaxLives;
    }
}

