package breakoutgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BreakoutPanel extends JPanel implements ActionListener, KeyListener {
    
    static final long serialVersionUID = 2L;

    private boolean gameRunning = true;
    private int livesLeft = 3;
    private String screenMessage = "";
    private Ball ball;
    private Paddle paddle;
    private Brick bricks[];
    
    public BreakoutPanel(Breakout game) {
    	// Set the preferred size of the panel to match the window dimensions specified in the Settings class
        this.setPreferredSize(new Dimension(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT));
        // Add a key listener to the panel to listen for keyboard events
        addKeyListener(this);
        // Allow the panel to receive focus, enabling it to receive keyboard input
        setFocusable(true);
        
        Timer timer = new Timer(5, this);
        timer.start();
        
        ball = new Ball(); // A new ball object and assign it to the appropriate variable
        paddle = new Paddle(); // A new paddle object and assign it to the appropriate variable
        bricks = new Brick[Settings.TOTAL_BRICKS]; // A new bricks array using Settings.TOTAL_BRICKS)
        createBricks(); // Call the createBricks() method
    }
    
    private void createBricks() {
        int counter = 0;
        int x_space = 0;
        int y_space = 0;
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 5; y++) {
                bricks[counter] = new Brick((x * Settings.BRICK_WIDTH) + Settings.BRICK_HORI_PADDING + x_space, (y * Settings.BRICK_HEIGHT) + Settings.BRICK_VERT_PADDING + y_space);
                counter++;
                y_space++;
            }
            x_space++;
            y_space = 0;
        }
    }
    
    private void paintBricks(Graphics g) {
    	// Loop through the bricks and call the paint() method
        for (Brick brick : bricks) {
            brick.paint(g);
        }
    }
    
    private void update() {
        if(gameRunning) {
            ball.update(); // Update the ball 
            paddle.update(); // Update the paddle
            collisions();
            repaint();
        }
    }
    
    private void gameOver() {
        screenMessage = "Game Over"; // Set screen message
        stopGame();
    }
    
    private void gameWon() {
        screenMessage = "You Won!"; // Set screen message
        stopGame();
    }
    
    private void stopGame() {
        gameRunning = false;
    }
    
    private void collisions() {
        // Check for loss
        if(ball.getY() > 450) {
            // Game over
            livesLeft--;
            if(livesLeft <= 0) {
                gameOver();
                return;
            } else {
                ball.resetPosition();
                ball.setYVelocity(-1);
            }
        }
        
		// Check for win
		boolean bricksLeft = false;
		for(int i = 0; i < bricks.length; i++) {
			// Check if there are any bricks left
			if(!bricks[i].isBroken()) {
				// Brick was found, close loop
				bricksLeft = true;
				break;
			}
		}
        if(!bricksLeft) {
            gameWon();
            return;
        }
        
        // Check collisions
        if(ball.getRectangle().intersects(paddle.getRectangle())) {
            ball.setYVelocity(-1);
        }
        
		for(int i = 0; i < bricks.length; i++) {
			if (ball.getRectangle().intersects(bricks[i].getRectangle())) {
				int ballLeft = (int) ball.getRectangle().getMinX();
	            int ballHeight = (int) ball.getRectangle().getHeight();
	            int ballWidth = (int) ball.getRectangle().getWidth();
	            int ballTop = (int) ball.getRectangle().getMinY();

	            Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
	            Point pointLeft = new Point(ballLeft - 1, ballTop);
	            Point pointTop = new Point(ballLeft, ballTop - 1);
	            Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

	            if (!bricks[i].isBroken()) {
	                if (bricks[i].getRectangle().contains(pointRight)) {
	                    ball.setXVelocity(-1);
	                } else if (bricks[i].getRectangle().contains(pointLeft)) {
	                    ball.setXVelocity(1);
	                }

	                if (bricks[i].getRectangle().contains(pointTop)) {
	                    ball.setYVelocity(1);
	                } else if (bricks[i].getRectangle().contains(pointBottom)) {
	                    ball.setYVelocity(-1);
	                }
	                bricks[i].setBroken(true);
	            }
			}
		}
	}
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ball.paint(g);
        paddle.paint(g);
        paintBricks(g);
        
        // Draw lives left
        g.drawString("Lives: " + livesLeft, Settings.LIVES_POSITION_X, Settings.LIVES_POSITION_Y);
        
        // Draw screen message
        if(screenMessage != null) {
            g.setFont(new Font("Arial", Font.BOLD, 18));
            FontMetrics fm = g.getFontMetrics();
            int messageWidth = fm.stringWidth(screenMessage);
            g.drawString(screenMessage, (Settings.WINDOW_WIDTH / 2) - (messageWidth / 2), Settings.MESSAGE_POSITION);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	// Set the velocity of the paddle depending on whether the player is pressing left or right
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            paddle.setXVelocity(-1);
        }
        if (key == KeyEvent.VK_RIGHT) {
            paddle.setXVelocity(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	// Set the velocity of the paddle after the player has released the keys
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            paddle.setXVelocity(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        update();
    }

}