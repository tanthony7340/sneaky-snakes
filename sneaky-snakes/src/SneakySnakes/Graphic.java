/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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
    
    public Graphic(){
        this.x=0;
        this.y=0;
        ID=0;
    }
    
    public Graphic(int x, int y,SneakySnakes sneakysnakes){
        
        this.x = x;
        this.y = y;
        sneakysnakes.graphicAdded();
        ID = sneakysnakes.numObjects;
        this.sneakysnakes=sneakysnakes;
    }
    
    public Graphic(int x, int y,int hitbox_X_Size,int hitbox_Y_Size,SneakySnakes sneakysnakes){
        
        this.x = x;
        this.y = y;        
        sneakysnakes.graphicAdded();
        ID = sneakysnakes.numObjects;
        this.sneakysnakes=sneakysnakes;
        
    }
    
    //changes state on each tick (location, direction, powerup status, etc)
    public abstract void update();
    
    //returns some representation(we need to figure out what that is) of what the Graphic should look like on the screen
    public abstract void render(Graphics g);
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return x;
    }

    public int getID(){
        return ID;
    }
    abstract boolean isOverlapped();
}