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
    public Player1(int x, int y, Color color, int length, SneakySnakes sneakysnakes){
        super(x, y, color, length);
        this.direction=Direction.SOUTH;
    }
    
    @Override
    Direction algorithm() {
           return direction;
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(super.x, super.y, 16, 16);
        
    }
}
