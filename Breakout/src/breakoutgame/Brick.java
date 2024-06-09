package breakoutgame;

import java.awt.Graphics;

public class Brick extends Sprite {
    
    private boolean broken = false;
    
    public Brick(int x, int y) {
        setX(x); // Set x using the parameter
        setY(y); // Set y using the parameter
        setWidth(Settings.BRICK_WIDTH); // Set the width of the brick
        setHeight(Settings.BRICK_HEIGHT); // Set the height of the brick
    }

    public boolean isBroken() {
        return broken;
    }
    public void setBroken(boolean b) {
        broken = b; // Set the broken variable
    }
    
    public void paint(Graphics g) {
        if(!broken) {
            g.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }
}

