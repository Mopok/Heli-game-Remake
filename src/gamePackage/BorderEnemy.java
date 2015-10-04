package gamePackage;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class BorderEnemy implements Sprite{
	
	Image imgBorder = new ImageIcon(this.getClass().getResource("/walltop.png")).getImage();
	int posX, posY;
	int sizeX = 600;//size is 600 by 64
	int sizeY = 64;//
	int velX = 0;//no velY or velX for this enemy
	double velY = 0;
    boolean visible;
	
	public BorderEnemy(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		visible = true;
		velX = 1;
		
	}
	
	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public int getPosX() {
		return posX;
	}

	@Override
	public int getPosY() {
		return posY;
	}

	@Override
	public int getSizeX() {
		return sizeX;
	}

	@Override
	public int getSizeY() {
		return sizeY;
	}

	@Override
	public int getVelX() {
		return velX;
	}

	@Override
	public double getVelY() {
		return velY;
	}

	@Override
	public void setVelX(int x) {
		velX  = x;
	}

	@Override
	public void setVelY(double y) {
		velY = y;
	}

	@Override
	public Image getImage() {
		return imgBorder;
	}

	@Override
	public void move() {
		posX -= velX;//doesnt move
		posY -= velY;
        
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle(posX, posY, sizeX, sizeY);
	}

}
