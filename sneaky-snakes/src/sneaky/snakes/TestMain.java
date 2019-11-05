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
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Graphic> objects = new ArrayList<>();
        
        CPU1 test = new CPU1();
        CPU1 test2 = new CPU1(15, 34, Color.BLACK, 12);
        Food foodTest = new Food(5, 5, Color.BLUE);
        
        objects.add(test);
        objects.add(test2);
        objects.add(foodTest);
        
        System.out.print(objects);
    }
    
}
