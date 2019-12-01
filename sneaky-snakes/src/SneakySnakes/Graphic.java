/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import static SneakySnakes.GraphicType.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author tommy
 */
public abstract class Graphic{
    protected int ID = 0;
    protected int x;
    protected int y;
    protected int velX;
    protected int velY;
    protected GraphicType type;
    
    public Graphic(){
        this.x=0;
        this.y=0;
        ID=0;
        this.type=NA;
    }
    
    public Graphic(int x, int y){        
        this.x = x;
        this.y = y;
        this.type=NA;
        this.ID=(int) (Math.random()*10000);
    }
    
    public Graphic(int x, int y, int id){        
        this.x = x;
        this.y = y;
        this.type=NA;
        this.ID=id;
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

    public GraphicType getType() {
        return type;
    }
    
    public abstract boolean isOverlapped();
    
    public abstract void handleOverlap();
    
    public abstract LinkedList<Point> getXYList();
    
    public abstract void processEvent(GraphicEvent event);
    
    public abstract void loadObstacle(ArrayList<Point> list);
    
    public abstract void loadFood(ArrayList<Point> list);
}