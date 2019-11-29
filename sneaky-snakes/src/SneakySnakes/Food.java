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
    protected Point foodPoint = new Point();
    protected FoodState foodstate = FoodState.NONE;
    
    public Food(int x, int y, Direction d, int id){
        segment = new Segment(x, y, d);
        segment.setIcon(0, 3);
        this.type=GraphicType.FOOD;
        this.ID=id;
        this.y=y;
        this.x=x;        
        foodPoint.setLocation(x, y);
    }
    
    @Override
    public void update(){
        switch(foodstate){
            case MAGNET:
                magnetState();
                break;
            case NONE:
                break;
            default:
                break;
        }
    }


    @Override
    public void render(Graphics g) {
        anim.drawAnimation(g, this.x*SIZE_X, this.y*SIZE_Y, 0, segment.frameX, segment.frameY);
        System.out.println("foodstate in food="+foodstate);
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
            
            // set food point
            foodPoint.setLocation(this.x, this.y);
            
            //Reset state from power ups
            if(foodstate==FoodState.MAGNET){
                foodstate=FoodState.NONE;
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
    
    private void magnetState(){
        for(Point point:snakeList){
            if(point.distance(foodPoint)<=3){
                //X distnance
                if(point.x-foodPoint.x>0){
                    this.x+=1;
                }
                else if(point.x-foodPoint.x<0){
                this.x-=1;
                }
                else{}
                
                //y distance
                if(point.y-foodPoint.y>0){
                    this.y+=1;
                }
                else if(point.y-foodPoint.y<0){
                this.y-=1;
                }
                else{}
                
                foodPoint.setLocation(this.x, this.y);
            }
        }
    }
}
