package PowerUps;

import SneakySnakes.Direction;
import SneakySnakes.Food;
import java.awt.Point;

/**
 *
 * @author andrew
 */
public class MagnetFood extends Food{

    Point foodPoint = new Point();
    
    public MagnetFood(int x, int y, Direction d,int id) {
        super(x, y, d, id);
        foodPoint.setLocation(x, y);
    }
    
    @Override
    public void update() {
        for(Point point:snakeList){
            if(point.distance(foodPoint)<=3){
                System.out.println("food is within 3");
                System.out.println("food x="+foodPoint.x+"food y="+foodPoint.y);
                System.out.println("point x="+point.x+"point y="+point.y);
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
