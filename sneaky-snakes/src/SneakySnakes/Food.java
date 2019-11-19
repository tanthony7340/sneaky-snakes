/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author tommy
 */
public class Food extends Graphic{
    
    private final Segment segment; //will only ever be one segment, but for dispay consistency i put it in an arraylist
    
    public Food(int x, int y, Color color, SneakySnakes sneakysnakes){        
        super(x,y,sneakysnakes);        
        this.sneakysnakes=sneakysnakes;
        segment = new Segment(super.x, super.y, color);
        this.type=Type.FOOD;
    }
    
    @Override
    public void update() {}


    @Override
    public void render(Graphics g) {
        g.setColor(segment.color);
        g.fillRect(super.x*16, super.y*16, 16, 16);
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
    
    @Override
    public boolean isOverlapped(){
        return false; //Food has only one segment
    }
    
    @Override
    public void handleOverlap(){
        
    }
        
        
    public LinkedList<Point> getXYList(){
        
        LinkedList<Point> coordinates = new LinkedList<>(); 
        coordinates.addFirst(new Point(getX(), getY()));
        return coordinates;
    }
    
    @Override
    public void processEvent(GraphicEvent event){
        if(event == GraphicEvent.FOOD_EATEN){
            this.x=(int) (Math.random() * SneakySnakes.WIDTH * SneakySnakes.SCALE /16);
            this.y=(int) (Math.random() * SneakySnakes.HEIGHT * SneakySnakes.SCALE/16);
        }
    }
}
