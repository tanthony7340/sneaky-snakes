/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

class Segment{
    int x;
    int y;
    SneakySnakes sneakysnakes;
    Direction direction;
    int frameX;
    int frameY;
    
    public Segment(int x, int y,Direction direction){
        this.x = x;
        this.y = y;
        this.direction=direction;
        
        //head is here
        gethead();
    }
    
    @Override
    public String toString(){
        return "X Coord is: " + x + " Y Coord is: " + y + "\n";
    }
    
    public void setIcon(int frameX, int frameY){
        this.frameX = frameX;
        this.frameY = frameY;
    }
    
    private void gethead(){
        
        switch (this.direction){
            case NORTH:
                frameX=3;
                frameY=0;
                break;
            case EAST:
                frameX=4;
                frameY=0;
                break;
            case SOUTH:
                frameX=4;
                frameY=1;
                break;
            case WEST:
                frameX=3;
                frameY=1;
                break;
            default:
                frameX=0;
                frameY=3;
                break;
        }
    }
    
    //Pass in the next segments direction
    public void updateSecond(Direction nextDirection){
        
        switch (direction){
            case NORTH:
                if(nextDirection == Direction.NORTH){
                    frameX=2;
                    frameY=1;
                    
                }else if(nextDirection == Direction.EAST){
                    frameX=0;
                    frameY=0;
                    
                }else if(nextDirection == Direction.WEST){
                    frameX=2;
                    frameY=0;
                }else{
                    System.out.println("Illegal Direction");
                }
                break;
            case EAST:
                if(nextDirection == Direction.NORTH){
                    frameX=2;
                    frameY=2;
                    
                }else if(nextDirection == Direction.EAST){
                    frameX=1;
                    frameY=0;
                    
                }else if(nextDirection == Direction.SOUTH){
                    frameX=2;
                    frameY=0;
                }else{
                    System.out.println("Illegal Direction");                }
                break;
            case SOUTH:
                if(nextDirection == Direction.EAST){
                    frameX=0;
                    frameY=1;
                    
                }else if(nextDirection == Direction.SOUTH){
                    frameX=2;
                    frameY=1;
                    
                }else if(nextDirection == Direction.WEST){
                    frameX=2;
                    frameY=2;
                }else{
                    System.out.println("Illegal Direction");
                }
                break;
            case WEST:
                if(nextDirection == Direction.NORTH){
                    frameX=0;
                    frameY=1;
                    
                }else if(nextDirection == Direction.SOUTH){
                    frameX=0;
                    frameY=0;
                    
                }else if(nextDirection == Direction.WEST){
                    frameX=1;
                    frameY=0;
                }else{
                    System.out.println("Illegal Direction");
                }
                break;
        }
        
    }
    
    public void getTail(Direction nextDirection){   
        switch (nextDirection){
            case NORTH:
                frameX=3;
                frameY=2;
                break;
            case EAST:
                frameX=4;
                frameY=2;
                break;
            case SOUTH:
                frameX=4;
                frameY=3;
                break;
            case WEST:
                frameX=3;
                frameY=3;
                break;
            default:
                frameX=3;
                frameY=3;
                break;
        }
    }
}
