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


class Segment extends Graphic{
    int x;
    int y;
    Color color;
    Rectangle hitbox;    
    SneakySnakes sneakysnakes;
    
    public Segment(int x, int y, Color color,SneakySnakes sneakysnakes){
        super(x, y, SIZE_X, SIZE_Y,sneakysnakes);
        this.x = x;
        this.y = y;
        this.color = color;
        this.sneakysnakes=sneakysnakes;
        this.hitbox=new Rectangle(x,y,SIZE_X, SIZE_Y);
        //addToGraphicsList();
    }
    
    @Override
    public String toString(){
        return "X Coord is: " + x + " Y Coord is: " + y + "\n";
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x*SIZE_X, y*SIZE_Y, SIZE_X, SIZE_Y);
        
    }

    
    
}
