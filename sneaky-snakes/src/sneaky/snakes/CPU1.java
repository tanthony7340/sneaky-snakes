/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sneaky.snakes;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author tommy
 */
public class CPU1 extends Snake{

    public CPU1(){
        super();
    }
    public CPU1(int x, int y, Color color, int length){
        super(x, y, color, length);
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
    
    public ArrayList<Segment> getSegments(){
        return super.segments;
    }
    
    @Override
    public String toString(){
       return super.segments.toString();
    }
}