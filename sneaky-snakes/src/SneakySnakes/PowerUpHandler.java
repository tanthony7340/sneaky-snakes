package SneakySnakes;

import SneakySnakes.*;
import static SneakySnakes.Food.SIZE_X;
import static SneakySnakes.PowerUpType.MAGNET;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author andrew
 */
public class PowerUpHandler extends Food {

    public FoodState foodStateSend = FoodState.NONE;
    private long now;
    private long delta=0;
    private long lastTime;
    private long ms = 1;
    private boolean generate;
    final static long spawnTime = 3000; //1 seconds
    private PowerUpType nextPowerUp;
    private FoodState foodState=FoodState.NONE;
    private PowerUpType lastPowerEaten= PowerUpType.NONE;
    public Animation anim = new Animation(GraphicType.POWERUP);
    public PowerUpHandler(int x, int y, Direction d, int id) {
        super(x, y, d, id);
        this.x=x;
        this.y=y;
        this.type=GraphicType.POWERUP;
        this.generate=false;
        this.nextPowerUp=PowerUpType.NONE;
        this.lastTime=System.currentTimeMillis();
        this.now=lastTime;
    }
    
    @Override
    public void render(Graphics g) {        
        if(generate){
            switch(nextPowerUp){
                case MAGNET:
                    //g.setColor(Color.red);
                    //g.fillRect(x*SIZE_X, y*SIZE_Y, SIZE_X, SIZE_Y);
                    anim.drawAnimation(g, this.x*SIZE_X, this.y*SIZE_Y);
                    break;
                case NONE:
                    break;
                default:
                    break;
            }
        }
    }
    
    @Override
    public void update(){
        now = System.currentTimeMillis();
        if(generateFood()){
        }
    }
    
    @Override
    public void processEvent(GraphicEvent event){
        if(event==GraphicEvent.POWERUP_EATEN){
            this.x=(int) Math.round(Math.random() * SneakySnakes.WIDTH * SneakySnakes.SCALE /16);
            this.y=(int) Math.round(Math.random() * SneakySnakes.HEIGHT * SneakySnakes.SCALE/16);
            if(this.y==SneakySnakes.HEIGHT * SneakySnakes.SCALE/16){
                this.y-=1;
            }
            
            if(this.x==SneakySnakes.WIDTH * SneakySnakes.SCALE/16){
                this.x-=1;
            }
            
            if(nextPowerUp==MAGNET){
                foodState=foodState.MAGNET;
            }
            else{
                foodState=foodState.NONE;
            }
            generate=false;
        }
    }
    
    public FoodState setFoodState(){
        return foodState; 
    }
    
    private boolean generateFood(){  
        delta = (now - lastTime) / ms;
        if(delta>=spawnTime){
            lastTime=System.currentTimeMillis();
            generate=true;
            delta=0;
            
            //make power up
            nextPowerUp=getRandomPowerup();
        }
        
        return generate;
    }
    
    public PowerUpType getRandomPowerup() {
        System.out.println("get random power up was run");
            Random random = new Random();
            return PowerUpType.values()[random.nextInt(PowerUpType.values().length)];
        }
    
}
