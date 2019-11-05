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
public class Food extends Graphic{
    private ArrayList<Segment> segments = new ArrayList<>(); //will only ever be one segment, but for dispay consistency i put it in an arraylist
    
    public Food(int x, int y, Color color){
        segments.add(new Segment(x, y, color));
    }
    
    
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public Object render() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        return segments;
    }
    
    @Override
    public String toString(){
       return segments.toString();
    }
    
}
