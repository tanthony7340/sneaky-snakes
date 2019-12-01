package SneakySnakes;

import static SneakySnakes.Food.SIZE_X;
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
    public Animation magnetAnim = new Animation(GraphicType.POWERUP, PowerUpType.MAGNET);
    public Animation growthAnim = new Animation(GraphicType.POWERUP, PowerUpType.GROWTH);
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
                    magnetAnim.drawAnimation(g, this.x*SIZE_X, this.y*SIZE_Y);
                    break;
                case GROWTH:
                    growthAnim.drawAnimation(g, this.x*SIZE_X, this.y*SIZE_Y);
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
            
            if(nextPowerUp==PowerUpType.MAGNET){
                foodState=foodState.MAGNET;
            }else if(nextPowerUp==PowerUpType.GROWTH){
                foodState=foodState.GROWTH;
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
            Random random = new Random();
            return PowerUpType.values()[random.nextInt(PowerUpType.values().length)];
        }
    
}
