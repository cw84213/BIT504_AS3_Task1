package breakoutgame;

import java.awt.Graphics;

public class Ball extends Sprite {

    private int xVelocity = 1, yVelocity = -1;
    
    // Constructor
    public Ball() {
        setWidth(Settings.BALL_WIDTH); // Set width to Settings.BALL_WIDTH
        setHeight(Settings.BALL_HEIGHT); // Set height to Settings.BALL_HEIGHT
        resetPosition(); // Call reset position
    }
    
    /**
     * Resets the ball to the initial position
     * Uses Settings.INITIAL_BALL_X/Y to set the position of the ball
     */
    public void resetPosition() {
        setX(Settings.INITIAL_BALL_X); // Set the balls x by using the INITIAL_BALL_X
        setY(Settings.INITIAL_BALL_Y); // Set the balls y by using the INITIAL_BALL_Y
    }
    
    public void update() {
        x += xVelocity; // Increase the x variable by xVelocity
        y += yVelocity; // Increase the y variable by yVelocity
        
        // Bounce off left side of screen
        if(x <= 0) {
            x = 0; // Set x to 0 so it does not leave the screen
            xVelocity = 1; // Change the x velocity to make the ball go right
        }
        
        // Bounce off right side of screen
        if(x >= Settings.WINDOW_WIDTH - Settings.BALL_WIDTH) {
            x = Settings.WINDOW_WIDTH - Settings.BALL_WIDTH; // Set x to the right edge of the screen
            xVelocity = -1; // Change the x velocity to make the ball go left
        }
        
        // Bounce off top of screen
        if(y <= 0) {
            y = 0; // Set y to 0 so it does not leave the screen
            yVelocity = 1; // Change the y velocity to make the ball go downward
        }
        
    }
    
    public void setXVelocity(int x) {
        xVelocity = x; // Set the x velocity
    }
    public void setYVelocity(int y) {
        yVelocity = y; // Set the y velocity
    }
    
    public int getXVelocity() {
        return xVelocity; // Return the x velocity
    }
    public int getYVelocity() {
        return yVelocity; // Return the y velocity
    }
    
    public void paint(Graphics g) {
        g.fillOval(x, y, Settings.BALL_WIDTH, Settings.BALL_HEIGHT);
    }
}

