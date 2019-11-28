/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SneakySnakes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Animation {
        public Image image;
        public ImageIcon ii;
        public BufferedImage spriteSheet;
        public BufferedImage resizedSpriteSheet;
        public int frameX;
        public int frameY;
        
	public Animation(){
            
            try {
                spriteSheet = ImageIO.read(new File("src/SneakySnakes/res/sprite_sheet.png"));
                
                // The image is 320 by 256 with 5 by 4 frames.
                resizedSpriteSheet = resize(spriteSheet,320/4,256/4);
    
        } catch (IOException e) {}
            
	}
	
	public void drawAnimation(Graphics g, double x, double y, int offset,int frameX, int frameY){
            //sneaky-snakes/sneaky-snakes/src/SneakySnakes/res/sprite_sheet.png 
            //ublic BufferedImage getSubimage (int x, int y, int w, int h) {
            BufferedImage sprite = resizedSpriteSheet.getSubimage(
                    resizedSpriteSheet.getWidth()/5 * frameX, 
                    resizedSpriteSheet.getHeight()/4 * frameY, 
                    resizedSpriteSheet.getWidth()/5, 
                    resizedSpriteSheet.getHeight()/4);
            
            g.drawImage(sprite, (int)x - offset, (int)y, null);
	}
        
        public static BufferedImage resize(BufferedImage img, int newW, int newH) {
            Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
            
            return dimg;
        }  
}