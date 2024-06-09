package breakoutgame;

import java.awt.Graphics;

public class Paddle extends Sprite {

    private int xVelocity;
    
    public Paddle() {
        setWidth(Settings.PADDLE_WIDTH); // Set width to Settings.PADDLE_WIDTH
        setHeight(Settings.PADDLE_HEIGHT); // Set height to Settings.PADDLE_HEIGHT
        resetPosition(); // Call resetPosition
    }
    
    public void resetPosition() {
    	// Set initial position x and y
        setX(Settings.INITIAL_PADDLE_X);
        setY(Settings.INITIAL_PADDLE_Y);
    }
    
    public void update() {
        x += xVelocity;
        
        // Prevent the paddle from moving outside of the screen
        if (x < 0) {
            x = 0;
        }
        if (x > Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH) {
            x = Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH;
        }
    }
    
    public void paint(Graphics g) {
        g.fillRect(x, y, Settings.PADDLE_WIDTH, Settings.PADDLE_HEIGHT);
    }
    
    public void setXVelocity(int vel) {
    	// Set x velocity
        xVelocity = vel;
    }
}
