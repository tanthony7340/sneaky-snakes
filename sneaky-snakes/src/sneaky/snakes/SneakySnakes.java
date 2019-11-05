package sneaky.snakes;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author 10158564
 */
public class SneakySnakes extends Canvas implements Runnable {
    //contains 
    ArrayList<Graphic> graphics = new ArrayList<>();
    
    private boolean running = false;
    private Thread thread;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    private static SneakySnakes instance;
    //private Menu menu;
    
    public final String TITLE = "Sneaky Snakes";
    
    public static enum STATE {
        MENU,
        GAME
    };
    private static STATE state = STATE.MENU;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Create 'game' canvas with predefined Dimension
        SneakySnakes game = SneakySnakes.getInstance();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        
        // Create Frame with 'game' canvas and size
        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Game starts here
        game.start();
    }
    
    
    /*
    * Main block for the game.
    * - initializes objects
    * - main loop for the game
    */
    @Override
    public void run() {
        init();
        
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0; // 60 Ticks per second
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while(running) {
            long now = System.nanoTime();
            
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1)
            {
                tick(); // Calculates
                updates++;
                delta--;
            }
            
            render(); // Shows
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){ // Waits
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        
        stop();
    }
    
    // Start method for Thread. Triggers the game to start and avoids
    // new instances if already playing.
    private synchronized void start() {
        if(running)
            return;
        
        running = true;
        
        thread = new Thread(this);
        thread.start();
    }
    
    // It performs stop actions when thread has finished
    public synchronized void stop() {
        if(!running)
            return;
        
        running = false;
        
        try {
            thread.join();
        } catch (InterruptedException e) {
        }
        
        System.exit(1);
    }
    
    private void tick()
    {
        if(state == STATE.GAME)
        {
            
            
        }
    }
    
    /*
    * Show game components in the screen from Buffers
    */
    private void render()
    {
        // Let's use 3 buffers strategy and access graphics to draw (g)
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        
        if(state == STATE.GAME) {
            
        }else if(state == STATE.MENU) {
            //menu.render(g);
        }
        
        /////////// END DRAWING //////////////////
        g.dispose();
        bs.show();
    }
    
    public static SneakySnakes getInstance() {
        if(instance==null)
            instance = new SneakySnakes();
        return instance;
    }
    
    public void init() {
        requestFocus(); // So the game gains focus just at starting point.
        
        
        // Add input listeners
        
    }
}
