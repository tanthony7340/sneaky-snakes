/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import static SneakySnakes.Snake.SIZE_X;
import static SneakySnakes.Snake.SIZE_Y;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author tommy
 */
public class Food extends Graphic{
    private final Segment segment; //will only ever be one segment, but for dispay consistency i put it in an arraylist
    //public Rectangle hitbox;
    
    
    public Food(int x, int y, Color color, SneakySnakes sneakysnakes){
        super(x,y,sneakysnakes);        
        this.sneakysnakes=sneakysnakes;
        segment = new Segment(x, y, color, sneakysnakes);
        this.hitbox=new Rectangle(x,y,SIZE_X, SIZE_Y);
        addToGraphicsList();
    }
    
    
    @Override
    public void update() {}


    @Override
    public void render(Graphics g) {
        g.setColor(segment.color);
        g.fillRect(super.x*16, super.y*16, 16, 16);;
    }
    
    @Override
   public int getX(){
        return super.getX();
    }
   
    @Override
   public int getY(){
        return super.getY();
    }
   
    public ArrayList<Segment> getSegments(){
        ArrayList<Segment> segments= new ArrayList<>();
        segments.add(segment);
        return segments;
    }
    
    @Override
    public String toString(){
       return segment.toString();
    }
    
}