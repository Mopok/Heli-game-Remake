package gamePackage;

import java.awt.Image;
import java.awt.Rectangle;

public interface Sprite {
	//is this sprite on screen?
	public boolean isVisible();
	public int getPosX();
	public int getPosY();
	public int getSizeX();
	public int getSizeY();
	public int getVelX();
	public double getVelY();
	public void setVelX(int x);
	public void setVelY(double y);
	public Image getImage();
	//how the sprite moves
	public void move();
	//bounds of the sprite
	public Rectangle getBounds();
}
