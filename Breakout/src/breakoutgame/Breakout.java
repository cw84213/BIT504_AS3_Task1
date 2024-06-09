package breakoutgame;

import javax.swing.JFrame;

public class Breakout extends JFrame{
    
    static final long serialVersionUID = 1L;
    
    private BreakoutPanel panel;
    
    public Breakout() {
    	// Create an instance of BreakoutPanel and assign it to the 'panel' field
        panel = new BreakoutPanel(this);
        // Add the panel to the JFrame
        add(panel);
        // Adjust the size of the JFrame to fit the preferred size of its components
        pack(); // Ensures the JFrame is appropriately sized
        
        setTitle(Settings.WINDOW_NAME); // Set the title
        getContentPane().setBackground(java.awt.Color.WHITE); // Set the background as white
        setResizable(false); // Set the resizable to false
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Set default close operation to exit when the frame is closed
        
        setVisible(true); // Set visible to true
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 new Breakout();    
             }
        });
    }
}