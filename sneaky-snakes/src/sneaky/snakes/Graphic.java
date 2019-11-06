/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sneaky.snakes;

import java.awt.Graphics;

/**
 *
 * @author tommy
 */
abstract class Graphic{
    protected int x;
    protected int y;
    protected int velX;
    protected int velY;
    
    
    public Graphic(){
        this.x=0;
        this.y=0;
    }
    
    public Graphic(int x, int y){
        this.x = x;
        this.y = y;
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
    
}