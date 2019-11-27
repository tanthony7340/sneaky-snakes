/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import java.awt.Color;

class Segment{
    int x;
    int y;
    Color color; 
    SneakySnakes sneakysnakes;
    Direction direction;
    int frameX;
    int frameY;
    
    public Segment(int x, int y, Color color,Direction direction){
        this.x = x;
        this.y = y;
        this.color = color;
        
        //head is here
        gethead(direction);
    }
    
    @Override
    public String toString(){
        return "X Coord is: " + x + " Y Coord is: " + y + "\n";
    }
    
    public void updateIcon(Direction d){
        // get next direction
    }
    
    public void gethead(Direction d){
        
        switch (direction){
            case NORTH:
                frameX=3;
                frameY=0;
                break;
            case EAST:
                frameX=4;
                frameY=0;
                break;
            case SOUTH:
                frameX=3;
                frameY=1;
                break;
            case WEST:
                frameX=4;
                frameY=1;
                break;
            default:
                frameX=0;
                frameY=3;
                break;
        }
    }
    
    //Pass in the next segments direction
    public void updateSecond(Direction nextDirection){
        
        switch (direction){
            case NORTH:
                frameX=3;
                frameY=0;
                break;
            case EAST:
                frameX=4;
                frameY=0;
                break;
            case SOUTH:
                frameX=3;
                frameY=1;
                break;
            case WEST:
                frameX=4;
                frameY=1;
                break;
        }
    }
}
