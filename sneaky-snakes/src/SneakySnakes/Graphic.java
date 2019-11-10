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
    protected int x;
    protected int y;
    protected int velX;
    protected int velY;
    protected Color color;
    protected SneakySnakes sneakysnakes;
    protected Rectangle hitbox;
    //protected int hitbox;
    
    
    public Graphic(){
        this.x=0;
        this.y=0;        
        //this.sneakysnakes=sneakysnakes;
        //addToGraphicsList();
        //Set null
        hitbox=new Rectangle(0, 0, 0, 0);
    }
    
    public Graphic(int x, int y,SneakySnakes sneakysnakes){
        
        this.x = x;
        this.y = y;
        this.sneakysnakes=sneakysnakes;
        //addToGraphicsList();
    }
    
    public Graphic(int x, int y,int hitbox_X_Size,int hitbox_Y_Size,SneakySnakes sneakysnakes){
        
        this.x = x;
        this.y = y;        
        this.hitbox=new Rectangle(x, y, hitbox_X_Size,hitbox_Y_Size);
        this.sneakysnakes=sneakysnakes;
        //addToGraphicsList();
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

    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void addToGraphicsList()
    {
        this.sneakysnakes.getGraphicsList().add(this);
    }
    
}