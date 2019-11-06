/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sneaky.snakes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 10160106
 */
public class Player1 extends Snake{
    public Player1(){
        super();
    }
    public Player1(int x, int y, Color color, int length){
        super(x, y, color, length);
    }
    
    @Override
    Direction algorithm() {
           return Direction.SOUTH;
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(super.x, super.y, 16, 16);
        
//        int xDir = 1, yDir = 1;
//        
//        switch(direction){
//            case NORTH:
//                xDir=0;
//                yDir=1;
//                break;
//            case SOUTH:
//                xDir=0;
//                yDir=-1;
//                break;
//            case EAST:
//                xDir=1;
//                yDir=0;
//                break;
//            case WEST:
//                xDir=-1;
//                yDir=0;
//                break;
//        }
        
//        for(int i = 0; i< segments.size();i++){
//            g.setColor(color);
//            g.fillRect(x +(16*i*xDir), y +(16*i*yDir), 15, 15);
//        }
    }
}
