/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import static SneakySnakes.Type.NA;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author tommy
 */
abstract class Graphic{
    private int ID = 0;
    protected int x;
    protected int y;
    protected int velX;
    protected int velY;
    protected Color color;
    protected SneakySnakes sneakysnakes; //TODO Remove this and generate ID diff
    protected Type type;
    
    public Graphic(){
        this.x=0;
        this.y=0;
        ID=0;
        this.type=NA;
    }
    
    public Graphic(int x, int y,SneakySnakes sneakysnakes){        
        this.x = x;
        this.y = y;
        sneakysnakes.graphicAdded();
        ID = sneakysnakes.numObjects;
        this.sneakysnakes=sneakysnakes;
        this.type=NA;
    }
    
    //changes state on each tick (location, direction, powerup status, etc)
    public abstract void update();
    
    //returns some representation(we need to figure out what that is) of what the Graphic should look like on the screen
    public abstract void render(Graphics g);
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

    public int getID(){
        return ID;
    }

    public Type getType() {
        return type;
    }
    
    abstract boolean isOverlapped();
    
    abstract void handleOverlap();
    
    abstract LinkedList<Point> getXYList();
    
    //How do I handle that the snake needs to be told to grow
    //but not all items need to grow?
    //I create a method called Process event and we'll pass events
    //If it's not recognized it will defualt to do nothing
    //And if it's recognized it will perform the action
    
    abstract void processEvent(GraphicEvent event);
    
    abstract void loadObstacle(ArrayList<Point> list);
    
    abstract void loadFood(ArrayList<Point> list);
}