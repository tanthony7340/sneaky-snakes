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
    protected SneakySnakes sneakysnakes;
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
    
    public abstract void update();
    
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
    
    abstract void processEvent(GraphicEvent event);
    
    abstract void loadObstacle(ArrayList<Point> list);
    
    abstract void loadFood(ArrayList<Point> list);
}