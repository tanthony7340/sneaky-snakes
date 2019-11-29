package SneakySnakes;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

public class Food extends Graphic{
    static final int SIZE_Y=16;
    static final int SIZE_X=16;
    
    public Animation anim = new Animation();
    private final Segment segment; //will only ever be one segment, but for dispay consistency i put it in an arraylist
    protected ArrayList<Point> foodList;
    protected ArrayList<Point> snakeList;
    
    public Food(int x, int y, Direction d, int id){
        segment = new Segment(x, y, d);
        segment.setIcon(0, 3);
        this.type=Type.FOOD;
        this.ID=id;
        this.y=y;
        this.x=x;
    }
    
    @Override
    public void update() {}


    @Override
    public void render(Graphics g) {
        anim.drawAnimation(g, this.x*SIZE_X, this.y*SIZE_Y, 0, segment.frameX, segment.frameY);

    }
    
    @Override
   public int getX(){
        return super.getX();
    }
   
    @Override
   public int getY(){
        return super.getY();
    }
   
    public ArrayList<Segment> getSegments(){
        ArrayList<Segment> segments= new ArrayList<>();
        segments.add(segment);
        return segments;
    }
    
    @Override
    public String toString(){
       return segment.toString();
    }
    
    @Override
    public boolean isOverlapped(){
        return false; //Food has only one segment
    }
    
    @Override
    public void handleOverlap(){
        
    }       
        
    @Override
    public LinkedList<Point> getXYList(){
        
        LinkedList<Point> coordinates = new LinkedList<>(); 
        coordinates.addFirst(new Point(getX(), getY()));
        return coordinates;
    }
    
    @Override
    public void processEvent(GraphicEvent event){
        if(event == GraphicEvent.FOOD_EATEN){
            this.x=(int) Math.round(Math.random() * SneakySnakes.WIDTH * SneakySnakes.SCALE /16);
            this.y=(int) Math.round(Math.random() * SneakySnakes.HEIGHT * SneakySnakes.SCALE/16);
            
            if(this.y==SneakySnakes.HEIGHT * SneakySnakes.SCALE/16){
                this.y-=1;
            }
            
            if(this.x==SneakySnakes.WIDTH * SneakySnakes.SCALE/16){
                this.x-=1;
            }
        }
    }
    
    @Override
    public void loadObstacle(ArrayList<Point> list){
        this.snakeList=list;        
    }
    
    @Override
    public void loadFood(ArrayList<Point> list){
        this.foodList=list; 
    }
}
