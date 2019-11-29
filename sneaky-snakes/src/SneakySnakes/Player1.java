package SneakySnakes;

import java.awt.Graphics;

public class Player1 extends Snake{
    public Player1(int x, int y, int length, int id){
        super(x, y, length, id);
        this.direction=Direction.SOUTH;
        this.type=GraphicType.FRIEND;
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
