/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sneaky.snakes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author tommy
 */

abstract public class Snake extends Graphic {
    
    LinkedList<Segment> segments = new LinkedList<>(); 

    Direction direction = Direction.NORTH;
    
    public Snake(){
        for(int i = 0; i < 2; i++){
            segments.add(new Segment(10 - i, 10 - i, Color.RED)); //TODO: checks to make sure we don't start snake off screen and add real values for coordinates
        }
    }
    public Snake(int x, int y, Color color, int length,SneakySnakes sneakysnakes){
     for(int i = 0; i < length; i++){
            segments.add(new Segment(x - i, y, color)); //TODO: checks to make sure we don't start snake off screen
        }
        direction = Direction.NORTH;
    
    this.x=x;
    this.y=y;
    this.color=color;
    
    }
    
    //TODO
    @Override
    public void update(){
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
        segments.addFirst(new Segment(this.x, this.y, Color.WHITE));
        segments.removeLast();
    }
    
    //TODO
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        ListIterator<Segment> iterator = segments.listIterator();
        while(iterator.hasNext()){
            Segment next = iterator.next();
            g.fillRect(next.x*16, next.y*16, 16, 16);
        }
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
    
    public void addSegment(int x, int y, Color color)
    {
        segments.add(new Segment(x, y, color));
    }
    
    public void influenceDirection(Direction directionIn){
        this.direction=directionIn;
    }
}
