package gamePackage;

import java.awt.Image;
import java.awt.Rectangle;

public class Player implements Sprite{
	Image img;
    int posX, posY, sizeX, sizeY, velX;
    double velY;
    boolean visible = true;
	
	public Player(int posX, int posY, int sizeX, int sizeY, Image img){
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeX;
		this.img = img;
		velX = 0;
		velY = 2;
		
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
	public Image getImage() {
		return img;
	}
	//TODO movements of the player model, 
	@Override
	public void move() {
        velY = velY + MainGame.gravity;//acceleration due to gravity

        if (velY > MainGame.terminal_velocity) {//You cant go too fast 
            velY = MainGame.terminal_velocity;
        }
        posX += velX;//placement x
        posY += velY;//placement y
		
	}

	@Override
	public Rectangle getBounds() {//get bounds of player
		return new Rectangle(posX, posY, sizeX, sizeY);
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
		velX = x;
	}

	@Override
	public void setVelY(double y) {
		velY = y;
	
	}

}
