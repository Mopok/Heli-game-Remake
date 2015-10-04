package gamePackage;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class AEnemy implements Sprite{
	
	Image imgAEnemy = new ImageIcon(this.getClass().getResource("/wall1.png")).getImage();
	int posX, posY;
	int sizeX = 135;//size is 135 by 66
	int sizeY = 166;//
	int velX;//no velY for this enemy
	double velY = 0;
    boolean visible;
	
	public AEnemy(int posX, int posY){
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
		return imgAEnemy;
	}

	@Override
	public void move() {
		posX -= velX;//1 is movement in x, if want scaling difficulty, must have scaling in second and here
		posY -= velY;
        if( posX < -135 ){
            visible = false;
        }
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle(posX, posY, sizeX, sizeY);
	}

}
