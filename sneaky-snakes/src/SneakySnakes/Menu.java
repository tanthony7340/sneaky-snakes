package SneakySnakes;

import static SneakySnakes.SneakySnakes.SCALE;
import static SneakySnakes.SneakySnakes.WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu implements MouseListener{
    
    public static Rectangle playButton = new Rectangle(SneakySnakes.WIDTH*SneakySnakes.SCALE/2 -50,SneakySnakes.HEIGHT,100,50);
    public static Rectangle helpButton = new Rectangle(SneakySnakes.WIDTH*SneakySnakes.SCALE/2 -50,SneakySnakes.HEIGHT+100,100,50);
    public static Rectangle quitButton = new Rectangle(SneakySnakes.WIDTH*SneakySnakes.SCALE/2 -50,SneakySnakes.HEIGHT+200,100,50);
    
    private int offset;
    protected boolean play = false;
    String title= "Sneaky Snakes";
    
    public Menu() {
        
    }
    
    public void update() {
    }
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        Font font = new Font("arial", Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.black);
        
        
        
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        offset = (metrics.stringWidth(title));
        
        g.drawString(title, (SneakySnakes.WIDTH*SneakySnakes.SCALE - offset)/2, 100);
        
        Font fnt1 = new Font("arial", Font.BOLD,30);
        g.setFont(fnt1);
        
        g.drawString("Play", playButton.x + 20, playButton.y + 35);
        g.drawString("Help", helpButton.x + 20, helpButton.y + 35);
        g.drawString("Quit", quitButton.x + 20, quitButton.y + 35);
        
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
        int mx = e.getX();
        int my = e.getY();
        
        if(playButton.contains(mx, my)){
            play=true;
        } else if(Menu.quitButton.contains(mx,my)) {
            // Pressed Quit Button
            System.exit(1);
        }        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isPlay() {
        return play;
    }
    
}
