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

abstract public class Snake extends Graphic {
    
    ArrayList<Segment> segments = new ArrayList<>();
    Direction direction = Direction.NORTH;
    
    public Snake(){
        for(int i = 0; i < 2; i++){
            segments.add(new Segment(10 - i, 10 - i, Color.RED)); //TODO: checks to make sure we don't start snake off screen and add real values for coordinates
        }
    }
    public Snake(int x, int y, Color color, int length){}
    
    //TODO
    @Override
    public void update(){
        //do a bunch of stuff using:
        this.algorithm();
        
    }
    
    //TODO
    @Override
    public Object render() {
        throw new UnsupportedOperationException("Not supported yet.");
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
}
