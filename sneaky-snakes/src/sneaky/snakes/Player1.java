/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sneaky.snakes;

import java.awt.Color;

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
    
}
