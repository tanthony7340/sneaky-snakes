package SneakySnakes;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.blue;
import static java.awt.Color.gray;
import static java.awt.Color.red;
import static java.awt.Color.white;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author 10158564
 */
public class SneakySnakes extends Canvas implements Runnable, KeyListener {
   
    //contains 
    volatile ArrayList<Graphic> graphicsList = new ArrayList<>(); //changed name from graphics to graphicsList to avoid naming conflicts
    
    private boolean running = false;
    private Thread thread;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 4;
    private static SneakySnakes instance;
    public Player1 player;
    public int numObjects = 0;
    
    //private Menu menu;
    public final String TITLE = "Sneaky Snakes";
    public final String GAMEOVER = "Game Over!";
    private static JFrame frame;

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Key Pressed="+e.getKeyCode());
        switch(e.getKeyCode()){
            case 37:
                player.influenceDirection(Direction.WEST);
                break;
            case 38:
                player.influenceDirection(Direction.NORTH);
                break;
            case 39:
                player.influenceDirection(Direction.EAST);
                break;
            case 40:
                player.influenceDirection(Direction.SOUTH);
                break;
            case 65:
                player.influenceDirection(Direction.WEST);
                break;
            case 87:
                player.influenceDirection(Direction.NORTH);
                break;
            case 68:
                player.influenceDirection(Direction.EAST);
                break;
            case 83:
                player.influenceDirection(Direction.SOUTH);
                break;
            default:
                break;
                            
        }

    }

    @Override
    public void keyReleased(KeyEvent e) { 
        
    }
    
    public static enum STATE {
        MENU,
        GAME,
        DEAD
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
        frame = new JFrame(game.TITLE);
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
        final double amountOfTicks = 30.0; // 60 Ticks per second
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
                checkCollisions();
                update(); // Calculates
                updates++;
                delta--;
            }
            
            //This lets Andrew Computer run the program
            try {
                TimeUnit.MILLISECONDS.sleep(15);} 
            catch (InterruptedException ex) {}
            
            
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
    
    private synchronized void update()
    {
        switch(state){
            case MENU:
                break;
            case GAME:
                for(Graphic graphic: graphicsList){
                    graphic.update();
                }
                break;
            case DEAD:
                break;
            default:
                throw new AssertionError(state.name());
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
        g.setColor(black);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        
        if(state == STATE.GAME) {
            gameEvent(g);
            
        }else if(state == STATE.MENU) {
            //menu.render(g);
        }else if(state == STATE.DEAD){
            gameEvent(g); //keep snakes on screen
            deadEvent(g); // draw game over
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
        player = new Player1(5, 5, Color.GRAY, 5, this);
        graphicsList.add(player);
        Food food = new Food(50, 50, Color.BLUE, this);  
        graphicsList.add(food);
        Food food2 = new Food(6, 40, Color.BLUE, this);  
        graphicsList.add(food2);
        CPU1 cpu = new CPU1(22,22, Color.RED,4, this);
        graphicsList.add(cpu);
        
        System.out.println("player id="+player.getID());
        System.out.println("food id=" + food.getID() + " At:" + food.getX() + " " + food.getY());
        System.out.println("food2 id="+food2.getID());
        System.out.println("cpu id=" +cpu.getID());
        
        
        state=STATE.GAME;
        
        // Add input listeners
        addKeyListener(this);
        
    }

    public ArrayList<Graphic> getGraphicsList() {
        return graphicsList;
    }
    
    public void graphicAdded(){
        numObjects++;
    }
    
    
    public void checkCollisions()//only Snakes need this?
    {
        
        //Process the snakes segments against graphics
        for(Graphic item:graphicsList){ //Get all graphic items
            
            //Check if the Item is overlapping with itself
            if(item.isOverlapped()){
                if(item.getType()==Type.FRIEND){
                    item.handleOverlap();
                    state=STATE.DEAD;
                }
                //graphicsList.remove(item);
            } //do other actions. ENUM?
            
            //Check if the item has collided with another graphics items
            LinkedList<Point> coordinates = item.getXYList();
            
            
                int currentID=item.getID();
                for(Graphic nextItem:graphicsList){
                    if(currentID!=nextItem.getID()){
                        LinkedList<Point> coordinatesNext = nextItem.getXYList();
                        
                        //Now we have each graphics' coodinates
                        Point theCollision;
                        if((theCollision=checkPoints(coordinates, coordinatesNext))!=null){
                            System.out.println("Collision with ID:"+
                                    item.getID() +" and ID:"+nextItem.getID()+" at "+theCollision);
                        }
                        
                    }
                
            }
        }//get graphic
    }//check col
          
    
    public Point checkPoints(LinkedList<Point> coordinates,LinkedList<Point> coordinatesNext){
        //Now we have each graphics' coodinates
                    for(Point point:coordinates){
                        //Biggest pile of poop i've ever written
                        for(Point pointNext:coordinatesNext){
                            if(point.equals(pointNext)){
                                return point;
                            }
                        }
                    }
                    return null;
    }
    
    public void deadEvent(Graphics g){
        
        g.setColor(red);
        Font font = g.getFont().deriveFont( 200.0f );
        g.setFont(font);
        g.drawString(GAMEOVER, 0, HEIGHT*SCALE/2);
        
    }
    
    public void gameEvent(Graphics g){
        for(Graphic graphic: graphicsList){
                graphic.render(g);
            }
    }
}
