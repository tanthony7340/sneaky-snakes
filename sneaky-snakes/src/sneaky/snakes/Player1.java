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
public class Player1 extends Snake {
    public Player1(){
        super();
    }
    public Player1(int x, int y, Color color, int length){
        super(x, y, color, length);
    }
    
    @Override
    Direction algorithm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        System.out.println("x="+x);
        System.out.println("super.x="+super.x);
        g.fillRect(super.x, super.y, 16, 16);
    }
}
