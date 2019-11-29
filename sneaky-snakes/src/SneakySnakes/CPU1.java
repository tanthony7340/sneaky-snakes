package SneakySnakes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class CPU1 extends Snake{
    boolean westObstacle;
    boolean eastObstacle;
    boolean northObstacle;
    boolean southObstacle;
  
    public CPU1(int x, int y, Color color, int length, int id){
        super(x, y, length, id);
        this.type=GraphicType.ENEMY;
    }
    
    private int iterations = -1;
    
    @Override
    Direction algorithm() {    
        return easyMode();       
    }
    
    private Direction circleMode(int circleSize){
        iterations++;
        if(iterations % circleSize == 0){
        
            switch(super.direction){
                case NORTH:
                    return Direction.EAST;
                case EAST:
                    return Direction.SOUTH;
                case SOUTH:    
                    return Direction.WEST;
                case WEST:
                    return Direction.NORTH;
            }
        }
        return super.direction;
    }
    
    @Override
    public int getX(){
        return super.getX();
    }
   
    @Override
    public int getY(){
        return super.getY();
    }
    
    public LinkedList<Segment> getSegments(){
        return super.segments;
    }
    
    @Override
    public String toString(){
       return super.segments.toString();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
    
    public boolean checkSides(){
        eastObstacle=false;
        westObstacle=false;
        northObstacle=false;
        southObstacle=false;
        Point meWest = new Point(this.x-1,this.y);
        Point meEast = new Point(this.x+1,this.y);
        Point meNorth = new Point(this.x,this.y-1);
        Point meSouth = new Point(this.x-1,this.y+1);
        
        for(Point snake:snakeList){
            if(meWest.equals(snake)){
                westObstacle=true;
            }
            if(meEast.equals(snake)){
                eastObstacle=true;
            }
            if(meNorth.equals(snake)){
                northObstacle=true;
            }
            if(meSouth.equals(snake)){
                southObstacle=true;
            }
        }        
        return false;
    }
    
    public Direction getIdealDirection(Point target){       
        int xDist = target.x-this.x;
        int yDist = target.y-this.y;
        
        //if x direction is bigger go that way
        if(Math.abs(xDist)>Math.abs(yDist)){
            if(xDist<0){
                direction=Direction.WEST;
            }else{
                direction=Direction.EAST;
            }
        }else{
            if(yDist<0){
                direction=Direction.NORTH;
            }else{
                direction=Direction.SOUTH;
            }
        }
        return direction;
    }
    
    public Direction easyMode(){
        Direction direction;
        
        //Check side of the snake
        checkSides();
        
        //Figure out direction
        if(!foodList.isEmpty()){
            Point target=foodList.get(0); //only the first food
            direction = getIdealDirection(target);
            if(!eastObstacle||!northObstacle||!southObstacle||!westObstacle){
                //
                for(int i = 0;i<=3;i++){
                    if(direction==Direction.EAST && eastObstacle){
                        direction=Direction.NORTH;
                    }
                    if(direction==Direction.NORTH && northObstacle){
                        direction=Direction.WEST;
                    }
                    if(direction==Direction.WEST && westObstacle){
                        direction=Direction.SOUTH;
                    }
                    if(direction==Direction.SOUTH && southObstacle){
                        direction=Direction.EAST;
                    }
                }
                return direction;
            }
            return direction;
        }
        //We don't have food to get, go int  circles regardless of obstacles
        else {
            return circleMode(10);
        }   
    }   
}