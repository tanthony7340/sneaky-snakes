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
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author tommy
 */

abstract public class Snake extends Graphic {
    
    //
    static final int SIZE_Y=16;
    static final int SIZE_X=16;;
    
    LinkedList<Segment> segments = new LinkedList<>(); 

    Direction direction = Direction.NORTH;
    
    public Snake(){
        for(int i = 0; i < 2; i++){
            segments.add(new Segment(10 - i, 10 - i, Color.RED)); //TODO: checks to make sure we don't start snake off screen and add real values for coordinates
        }
    }
    public Snake(int x, int y, Color color, int length,SneakySnakes sneakysnakes){
        super(x, y, sneakysnakes);
        this.x=x;
        this.y=y;
        this.color=color;
        this.sneakysnakes=sneakysnakes;
        
        for(int i = 0; i < length; i++){
            segments.add(new Segment(x - i, y, color)); //TODO: checks to make sure we don't start snake off screen
        }
        direction = Direction.NORTH;
    }
    
    //TODO
    @Override
    public void update(){
        
        //collisions
        
        
        
        processDirection();
        segments.addFirst(new Segment(this.x, this.y, this.color));
        segments.removeLast();
    }
    
    //TODO
    @Override
    public void render(Graphics g) {
        
        //Nothing to render?
        
        g.setColor(this.color);
        ListIterator<Segment> iterator = segments.listIterator();
        while(iterator.hasNext()){
            Segment next = iterator.next();
            g.fillRect(next.x*SIZE_X, next.y*SIZE_Y, SIZE_X, SIZE_Y);
        }
    }
    
    //We only need to check if the head has hit another segment
    @Override
    public boolean isOverlapped(){
        
        if(!segments.isEmpty()){
            ListIterator<Segment> iterator = segments.listIterator();
            Segment head = iterator.next();
            while(iterator.hasNext()){
                Segment current = iterator.next();
                if(head.x == current.x && head.y == current.y){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    @Override
    public void handleOverlap(){
        segments.removeAll(segments);
    }

    @Override
    public LinkedList<Point> getXYList(){
        
        if(!segments.isEmpty()){
            LinkedList<Point> coordinates = new LinkedList<>();
            ListIterator<Segment> iterator = segments.listIterator();
            Segment head = iterator.next();
            while(iterator.hasNext()){
                Segment current = iterator.next();
                coordinates.addFirst(new Point(current.x, current.y));
                
            }
            
            return coordinates;
        }
        
        //no segments so return nothing
        LinkedList<Point> coordinates = new LinkedList<>();
        coordinates.addFirst(new Point(-9999, -9999));
        return coordinates;
    }
    
    abstract Direction algorithm();
   
    @Override
    public int getX(){
        return super.getX();
    }
   
    @Override
    public int getY(){
        return super.getY();
    }
    
    public void addSegment()
    {
        processDirection();
        segments.add(new Segment(this.x, this.y, this.color));
    }
    
    public void influenceDirection(Direction directionIn){
        this.direction=directionIn;
    }
    
    public LinkedList<Segment> getSegmentList()
    {
        return segments;
    }
    
    @Override
    public void processEvent(GraphicEvent event){
        if(event==GraphicEvent.SNAKE_GROW){
            addSegment();
        }
    }
    
    public void processDirection(){
        direction = this.algorithm();
        switch (direction){
            case NORTH:
                this.y -= 1;
                break;
            case EAST:
                this.x += 1;
                break;
            case SOUTH:
                this.y += 1;
                break;
            case WEST:
                this.x -= 1;
                break;
        }
    }
}
