/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ListIterator;
import static SneakySnakes.Snake.SIZE_Y;
import static SneakySnakes.Snake.SIZE_X;


class Segment{
    int x;
    int y;
    Color color; 
    SneakySnakes sneakysnakes;
    
    public Segment(int x, int y, Color color,SneakySnakes sneakysnakes){
        this.x = x;
        this.y = y;
        this.color = color;
        this.sneakysnakes=sneakysnakes;
    }
    
    @Override
    public String toString(){
        return "X Coord is: " + x + " Y Coord is: " + y + "\n";
    }
}
