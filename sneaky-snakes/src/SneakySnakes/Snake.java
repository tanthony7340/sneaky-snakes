/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author tommy
 */

abstract public class Snake extends Graphic {
    
    //
    static final int SIZE_Y=16;
    static final int SIZE_X=16;
    
    protected LinkedList<Segment> segments = new LinkedList<>();
    protected ArrayList<Point> foodList;
    protected ArrayList<Point> snakeList;
    protected Direction direction = Direction.NORTH;
    private int score = 0;
    private boolean eaten = false;
    public Animation anim = new Animation();
    public Animation animEnemy = new Animation(GraphicType.ENEMY);
    
    public Snake(int x, int y, int length, int id){
        super(x, y);
        this.x=x;
        this.y=y;
        this.ID=id;
        direction = Direction.NORTH;
        
        for(int i = 0; i < length; i++){
            segments.add(new Segment(x - i, y,direction));
        }       
    }
    
    @Override
    public void update(){
        processDirection();
        segments.addFirst(new Segment(this.x, this.y, this.direction));
        if(!eaten) segments.removeLast();
        if(segments.size()>1)
        {
            segments.get(1).updateSecond(this.direction);
            segments.getLast().getTail(segments.get(segments.size() - 2).direction);
        }
        eaten=false;
    }
    
    //TODO
    @Override
    public void render(Graphics g) {
         ListIterator<Segment> iterator = segments.listIterator();
        while(iterator.hasNext()){
            Segment next = iterator.next();
            if(type==GraphicType.FRIEND){
                anim.drawAnimation(g, next.x*SIZE_X, next.y*SIZE_Y, 0, next.frameX, next.frameY);
            }
            else{
                animEnemy.drawAnimation(g, next.x*SIZE_X, next.y*SIZE_Y, 0, next.frameX, next.frameY);
            }
        }
        
    }
    
    @Override
    public boolean isOverlapped(){       
        if(!segments.isEmpty()){
            ListIterator<Segment> iterator = segments.listIterator();
            Segment head = iterator.next();
            while(iterator.hasNext()){
                Segment current = iterator.next();
                if(head.x == current.x && head.y == current.y){
                    System.out.println("Self Overlap id="+getID());
                    return true;
                }
            }
        }
        return false;
    }   
    
    @Override
    public void handleOverlap(){
        if(type==GraphicType.ENEMY){
            processEvent(GraphicEvent.COLLISION);
        }
        if(type==GraphicType.FRIEND){
            //Do nothing for now. This keeps the snakes on the screen
        }
    }

    @Override
    public LinkedList<Point> getXYList(){       
        if(!segments.isEmpty()){
            LinkedList<Point> coordinates = new LinkedList<>();
            ListIterator<Segment> iterator = segments.listIterator();
            while(iterator.hasNext()){
                Segment current = iterator.next();
                coordinates.addFirst(new Point(current.x, current.y));                
            }            
            return coordinates;
        }
        
        //no segments so return nothing
        LinkedList<Point> coordinates = new LinkedList<>();
        coordinates.addFirst(new Point(-9999, -9999));
        return coordinates;
    }
    
    abstract Direction algorithm();
   
    @Override
    public int getX(){
        return super.getX();
    }
   
    @Override
    public int getY(){
        return super.getY();
    }
    
    public void addSegment()
    {
        eaten=true;
        //processDirection();
        //segments.add(new Segment(this.x, this.y, this.color));
    }
    
    public void influenceDirection(Direction directionIn){
        switch (direction){
            case NORTH:
                if(directionIn != Direction.SOUTH) this.direction=directionIn;
                break;
            case EAST:
                if(directionIn != Direction.WEST) this.direction=directionIn;
                break;
            case SOUTH:
                if(directionIn != Direction.NORTH) this.direction=directionIn;
                break;
            case WEST:
                if(directionIn != Direction.EAST) this.direction=directionIn;
                break;
        }
    }
    
    public LinkedList<Segment> getSegmentList()
    {
        return segments;
    }
    
    @Override
    public void processEvent(GraphicEvent event){
        if(event==GraphicEvent.SNAKE_GROW){
            addSegment();
            score++;
        }
        if(event==GraphicEvent.COLLISION)
            segments.removeAll(segments);
    }
    
    public void processDirection(){
        direction = this.algorithm();
        switch (direction){
            case NORTH:
                this.y -= 1;
                break;
            case EAST:
                this.x += 1;
                break;
            case SOUTH:
                this.y += 1;
                break;
            case WEST:
                this.x -= 1;
                break;
        }
    }
    
    @Override
    public void loadObstacle(ArrayList<Point> list){
        this.snakeList=list;
        for(Segment item:segments){            
            snakeList.add(new Point(item.x,item.y));
        }
    }
    
    @Override
    public void loadFood(ArrayList<Point> list){
        this.foodList=list;
    }
    
    public int getScore(){
        return score;
    }
}
