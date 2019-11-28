package SneakySnakes;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SneakySnakes extends Canvas implements Runnable, KeyListener {
   
    //contains 
    volatile ArrayList<Graphic> graphicsList = new ArrayList<>(); //changed name from graphics to graphicsList to avoid naming conflicts
    volatile ArrayList<Graphic> removeList = new ArrayList<>();
    
    private boolean running = false;
    private Thread thread;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 4;
    private static SneakySnakes instance;
    public Player1 player;
    public int numObjects = 0;
    public boolean influenced = false; //need better solution if we ever have more than one player
    public BufferedImage backgroundImg;
    
    //private Menu menu;
    public final String TITLE = "Sneaky Snakes";
    public final String GAMEOVER = "Game Over!";
    private static JFrame frame;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(!influenced) switch(e.getKeyCode()){
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
        influenced=true;

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
        final double amountOfTicks = 20.0; // 60 Ticks per second
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
                processCPU();
                checkCollisions();
                deleteCPUs();
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
                //System.out.println(updates + " Ticks, Fps " + frames);
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
                influenced=false;
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
        
        //Background        
        g.drawImage(backgroundImg, 0, 0, null);
        
        if(state == STATE.GAME) {
            gameEvent(g);
            
        }else if(state == STATE.MENU) {
            //menu.render(g);
        }else if(state == STATE.DEAD){
            gameEvent(g); //keep snakes on screen
            deadEvent(g); //draw game over
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
        
        //Create background
        try {
            backgroundImg = ImageIO.read(new File("src/SneakySnakes/res/grassBest_1.png"));  
            backgroundImg = Animation.resize(backgroundImg, WIDTH*SCALE, HEIGHT*SCALE);
            
        } catch (IOException ex) {
            Logger.getLogger(SneakySnakes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        requestFocus(); // So the game gains focus just at starting point.
        player = new Player1(20, 40, Color.WHITE, 5, this);
        graphicsList.add(player);
        Food food = new Food(6, 49, Color.BLUE, this, Direction.NORTH);  
        graphicsList.add(food);
        
        
               //CPUs
        CPU1 cpu1 = new CPU1(8,1, Color.DARK_GRAY,6, this);
        graphicsList.add(cpu1);
//        CPU1 cpu2 = new CPU1(10,1, Color.DARK_GRAY,2, this);
//        graphicsList.add(cpu2);
//        CPU1 cpu3 = new CPU1(15,7, Color.DARK_GRAY,2, this);
//        graphicsList.add(cpu3);
//        CPU1 cpu4 = new CPU1(20,22, Color.DARK_GRAY,2, this);
//        graphicsList.add(cpu4);
//        CPU1 cpu5 = new CPU1(25,1, Color.DARK_GRAY,2, this);
//        graphicsList.add(cpu5);
//        CPU1 cpu6 = new CPU1(30,50, Color.DARK_GRAY,2, this);
//        graphicsList.add(cpu6);
     
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
                if(item.getType()==Type.ENEMY){
                    System.out.println("Enemy overlap ID:"+item.getID()+" x="+item.x+" y="+item.y);
                    item.handleOverlap();
                    //removeList.add(item);
                    //continue;
                }
            }
            
            //Check Wall collision
            if(checkWallCollision(item) && item.getType()==Type.FRIEND){
                state=STATE.DEAD;
            }
            
            //Check Wall collision
            if(checkWallCollision(item) && item.getType()==Type.ENEMY){
                item.processEvent(GraphicEvent.COLLISION);
            }
            
            //Check if the item has collided with another graphics items
            LinkedList<Point> coordinates = item.getXYList();
            
            
                int currentID=item.getID();
                for(Graphic nextItem:graphicsList){
                    if(currentID!=nextItem.getID()){
                        LinkedList<Point> coordinatesNext = nextItem.getXYList();
                        
                        //Now we have each graphics' coodinates
                        Point theCollision;
                        if((theCollision=checkPoints(coordinates, coordinatesNext))!=null){
                            
                            if((item.getType()==Type.FRIEND || item.getType()==Type.ENEMY) && nextItem.getType()==Type.FOOD){
                                item.processEvent(GraphicEvent.SNAKE_GROW);
                                nextItem.processEvent(GraphicEvent.FOOD_EATEN);
                            }
                            if(item.getType()==Type.FRIEND && nextItem.getType()==Type.ENEMY  && coordinates.getLast().equals(theCollision)){
                                state=STATE.DEAD;
                            }
                            if(item.getType()==Type.ENEMY && nextItem.getType()==Type.FRIEND  && coordinates.getLast().equals(theCollision)){
                                item.processEvent(GraphicEvent.COLLISION);
                                removeList.add(item);
                            }
                            if(item.getType()==Type.ENEMY && nextItem.getType()==Type.ENEMY){
                                item.processEvent(GraphicEvent.COLLISION);
                                removeList.add(item);
                                nextItem.processEvent(GraphicEvent.COLLISION);
                                removeList.add(nextItem);
                            }
                        }
                        
                    }
                
            }
        }
    }          
    
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
        
        //Get color
        g.setColor(Color.red);
        Font font = g.getFont().deriveFont( 200.0f ); //TODO set text size based on window size
        
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        Rectangle rect = null;
        int x = (WIDTH*SCALE - metrics.stringWidth(GAMEOVER)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y =((HEIGHT*SCALE - metrics.getHeight()) / 2) + metrics.getAscent();
        
        //draw
        g.setFont(font);
        g.drawString(GAMEOVER, x, y);
        g.drawString("Score:" + player.getScore(), x + 150, y + 200);
        
    }
    
    public void gameEvent(Graphics g){
        for(Graphic graphic: graphicsList){
                graphic.render(g);
        }
    }
    
    public boolean checkWallCollision(Graphic item)
    {
        LinkedList<Point> xyList= item.getXYList();        
        for(Point point:xyList){
            //Left Wall
            if(point.x<0){
                return true;
            }
            //Right wall
            if(point.x>(WIDTH-1)*SCALE/16){
                return true;
            }
            //Bottom
            if(point.y>(HEIGHT-1)*SCALE/16){
                return true;
            }
            //Top
            if(point.y<0){
                return true;
            }
        }        
        return false;
    }
    
    public void processCPU(){
        
        ArrayList<Graphic> enemyList = new ArrayList<>();
        ArrayList<Graphic> foodList = new ArrayList<>();
        ArrayList<Point> foodLocations = new ArrayList<>();
        ArrayList<Point> snakeLocations = new ArrayList<>();
        
        for(Graphic item:graphicsList){ //Get all graphic items
            
            //Record Enemies and their locations
            if(item.getType()==Type.ENEMY){
                enemyList.add(item);
                snakeLocations.add(new Point(item.x,item.y));
            }
            
            //Record food and their locations
            if(item.getType()==Type.FOOD){
                foodList.add(item);
                foodLocations.add(new Point(item.x,item.y));
            }
            
            //Record friend locations   
            if(item.getType()==Type.FRIEND){
                snakeLocations.add(new Point(item.x,item.y));                
            }
            
        }
        
        //Give food location to enemies
        for(Graphic item:enemyList){
            item.loadFood(foodLocations);
        }
        
        //Give snake locations to enemies
        for(Graphic item:enemyList){
            item.loadObstacle(snakeLocations);
        }
        //Give food location to food
        for(Graphic item:foodList){
            item.loadFood(foodLocations);
        }
        
        //Give snake locations to food
        for(Graphic item:foodList){
            item.loadObstacle(foodLocations);
        }
    }
    
    public void deleteCPUs(){
        for(Graphic item:removeList){
            graphicsList.remove(item);
        }
        
        removeList.removeAll(removeList);
    }
}
