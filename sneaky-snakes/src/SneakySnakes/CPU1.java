/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import static SneakySnakes.Snake.SIZE_X;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author tommy
 */
public class CPU1 extends Snake{

    public CPU1(){
        super();
    }
    public CPU1(int x, int y, Color color, int length, SneakySnakes sneakysnakes){
        super(x, y, color, length, sneakysnakes);
        this.hitbox=new Rectangle(x,y,SIZE_X, SIZE_Y);
    }
    
    private int iterations = -1;
    @Override
    Direction algorithm() {
        iterations++;
        if(iterations % 10 == 0){
        
            switch(super.direction){
                case NORTH:
                    return Direction.EAST;
                case EAST:
                    return Direction.SOUTH;
                case SOUTH:    
                    return Direction.WEST;
                case WEST:
                    return Direction.NORTH;
            }
        }
        return super.direction;
    }
    
    
    @Override
    public int getX(){
        return super.getX();
    }
   
    @Override
    public int getY(){
        return super.getY();
    }
    
    public LinkedList<Segment> getSegments(){
        return super.segments;
    }
    
    @Override
    public String toString(){
       return super.segments.toString();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}