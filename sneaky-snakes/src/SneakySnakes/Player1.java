/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 10160106
 */
public class Player1 extends Snake{
    public Player1(int x, int y, Color color, int length, SneakySnakes sneakysnakes){
        super(x, y, color, length, sneakysnakes);
        this.direction=Direction.SOUTH;
        this.sneakysnakes=sneakysnakes;
        this.type=Type.FRIEND;
    }
    
    @Override
    Direction algorithm() {
           return direction;
    }
    
    @Override
    public void render(Graphics g) {
       super.render(g);
        
    }
    @Override
    public void update(){
        super.update();
    }
}
