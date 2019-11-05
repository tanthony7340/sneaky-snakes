/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sneaky.snakes;

import java.awt.Color;

/**
 *
 * @author tommy
 */
class Segment {
    int x;
    int y;
    Color color;
    
    public Segment(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    @Override
    public String toString(){
        return "X Coord is: " + x + " Y Coord is: " + y + "\n";
    }
}
