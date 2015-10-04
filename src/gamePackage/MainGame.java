package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;

import gamePackage.StartHeliGame.STATE;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGame extends JPanel implements ActionListener, KeyListener {
	//frame in which the panel will run in
	public JFrame f;
	
	public ArrayList<Sprite> sprites;// Keeps track of enemies(List of enemies)
	private Timer t = new Timer(5, this);// Timer for repaint(5), spawning
											// walls(1000), increase
											// score(5)(Milliseconds)
	int nx2 = 585, nx = 0, nnx = 0, nnx2 = 585, camX = 200, camSpdX = 1;// position on
															// background, camX
															// is the x position
															// of the "camera"
	public static double gravity = 0.2;// gravity constant 0.2
	public static double terminal_velocity = 20;// max falling speed
	public int score = 0;
	int rry;// height of enemy spawns
	int timeElapsed; // Milliseconds
	int gameStopTime;// time when game stops
	Image backgroundImg = new ImageIcon(this.getClass().getResource("/background122.png")).getImage();
	Image HeliImg = new ImageIcon(this.getClass().getResource("/Helitest.png")).getImage();
	Image scrback = new ImageIcon(this.getClass().getResource("/scoreback.png")).getImage();
	public Player mainP;
	public BorderEnemy topBorder;
	public BorderEnemy botBorder;
	private Rectangle crashPoint;
	private Graphics2D g2;
	
	public MainGame() {
		//parameters for jframe the panel will be in
		//Frame that the game will run in
		f = new JFrame();//new frame				
		f.setSize(600, 800);//size of frame X by Y
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//exits on close of window
        f.setTitle("Heli Game");
        f.add(this);//add jpanel of main game to frame
        f.setVisible(true);//set the frame to visible
		
		// parameters for JPanel

		addKeyListener(this);
		setFocusable(true);// main window focused
		setFocusTraversalKeysEnabled(false);// fixes broken keys

		StartHeliGame.gameState = STATE.GAME;
		sprites = new ArrayList<Sprite>();
		t.start();

		// TODO maybe add feature to change what the player can play as
		mainP = new Player(90, 150, 100, 70, HeliImg);// x start, y start, x
														// size, y size, img

		topBorder = new BorderEnemy(0, 0);
		botBorder = new BorderEnemy(0, 700);
	}

	// make walls at the semi-random specified location
	public void enemySpawn() {
		if (StartHeliGame.gameState == STATE.GAME) {
			int ry = 1 + (int) (Math.random() * ((4 - 1) + 1));// random #
																// between 1 and
																// 4 int

			if (ry == 1) {
				rry = 60;
			} else if (ry == 2) {
				rry = 600;
			} else
				// so that these two cant come one after another

			if (ry == 3 && rry != 150) {
				rry = 500;
			} else if (ry == 4 && rry != 500) {// since rry will be there on the
												// previous run through, it will
												// disallow them to follow, in
												// theory :P
				rry = 150;
			}

			AEnemy z = new AEnemy((camX + 400), rry);// implement AEnemy and
														// will work
			sprites.add(z);
		}
	}

	public void paint(Graphics g) {

		super.paint(g);
		g2 = (Graphics2D) g;

		// t.start();
		// fix background
		// Background
		if ((camX - 2269) % 5310 == 0) {
			nx = 0;
		}
		if ((camX - 4924) % 5310 == 0) {// 600 size of image x, 800 is size +
										// pixels where it ends
			nx2 = 0;
		}
		System.out.println(camX);
		g2.drawImage(backgroundImg, 585 - nx2, 0, null);
		// so starts at 0 and moves to appear movement forward
		if (camX >= 2269) {// repaints when ends
			g2.drawImage(backgroundImg, 585 - nx, 0, null);
			// create new background at edge of frame
		}
		// Main guy painting
		g2.drawImage(mainP.img, mainP.posX, mainP.posY, null);

		// Enemies movement

		for (int w = 0; w < sprites.size(); w++) {
			// loop through array, get each element. Each element is an object of enemywall class
			Sprite m = (Sprite) sprites.get(w);// returns what is in array list
			g2.drawImage(m.getImage(), (int) m.getPosX(), (int) m.getPosY(), null);
			// draws all the enemies in the list
		}

		// draw borders
		g2.drawImage(topBorder.imgBorder, topBorder.posX, topBorder.posY, null);
		g2.drawImage(botBorder.imgBorder, botBorder.posX, botBorder.posY, null);

		// score
		g2.drawImage(scrback, 0, 0, null);
		g.setColor(Color.black);
		Font textFont = new Font("Arial", Font.BOLD, 20);
		g.setFont(textFont);
		g2.drawString(Integer.toString(score), 120, 38);
		
		//crashPoint
		if(StartHeliGame.gameState == STATE.GAMEOVER){
			g.setColor(Color.red);
			g2.drawRect(crashPoint.x-25, crashPoint.y-25, 50, 50);//the center of the square is the crashPoint
			Font endGameText =  new Font("Arial", Font.BOLD, 40);
			g.setFont(endGameText);
			String scoreString = "Your score: " + Integer.toString(score);
			g2.drawString("Game Over", getCenteredXString("Game Over"), 300);
			g2.drawString(scoreString,getCenteredXString(scoreString) , 350);
		}
		
		
	}
	
	public int getCenteredXString(String s) {
	    int stringLen = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
	    int start = f.getWidth()/2 - stringLen/2;
	    
	    return start;
	}
	  
	

	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP && StartHeliGame.gameState == STATE.GAME) {
			up();
		}
	}

	public void up() {
		mainP.setVelY(-7);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// just need the keyPressed method

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// on each tick, should check collisions, move heli, move background,
		// move border enemies, move enemies,
		// check if it should spawn enemies, increase score

		mainP.move();// I want the heli to still fall once the game ends
		camX = camX + camSpdX;// move camera 1 in x (camera still scroll after game over)
		nx2 += camSpdX;// change in background
		nx += camSpdX;// Change in background #2
		timeElapsed = timeElapsed + 5;// because this is called every 5
										// milliseconds
		
		// Enemies
		// Loop of enemies to move
		// move each enemy or remove them if off screen
		
		for (int w = 0; w < sprites.size(); w++) {// loop through array, get
			// each element. 	
			Sprite m = (Sprite) sprites.get(w);// returns what is in array list
			if (m.isVisible() == true) {
				m.move();
			} else {
				sprites.remove(w);// removes if not in visible and is in array list
			}

		}
		if (StartHeliGame.gameState == STATE.GAME) {
			checkCollisions();// check every 5 millisecond
			// increment score
			score = score + 11;
			// should happen every 1000 milliseconds
			if ((timeElapsed % 1000) == 0) {
				enemySpawn();
			}

		}

		// checks game state
		if (StartHeliGame.gameState == STATE.GAMEOVER) {
			if (gameStopTime == 0) {
				gameStopTime = timeElapsed;
			}
			//move crash point
			int crashPointX = crashPoint.x;
			crashPointX -= camSpdX;
			crashPoint.setLocation(crashPointX, crashPoint.y);
			//after 2500ms game will quit and go to menu
			if (timeElapsed - gameStopTime == 2500) {
				Menu menu = new Menu();
				menu.setVisible(true);
				t.stop();
				f.dispose();
			}
		}

		repaint();

	}

	public void checkCollisions() {
		Rectangle h = mainP.getBounds();
		for (int w = 0; w < sprites.size(); w++) {// loop through array, get
													// each element. Each
													// element is an object of
													// enemywall class
			Sprite m = (Sprite) sprites.get(w);// returns what is in array list
			Rectangle m1 = m.getBounds();// implement enemy and should work
			if (h.intersects(m1)) {
				StartHeliGame.gameState = STATE.GAMEOVER;
				crashPoint = (Rectangle) h.createIntersection(m1);
				System.out.println("hit");
			}

		}

		// finish collisions //
		if (mainP.posY <= topBorder.sizeY) {// top and bot collisions
			StartHeliGame.gameState = STATE.GAMEOVER;
			//crash representation
			crashPoint = (Rectangle) h.createIntersection(topBorder.getBounds());
			System.out.println("hit");
		}else if(mainP.posY >= botBorder.posY - botBorder.sizeY){
			StartHeliGame.gameState = STATE.GAMEOVER;
			crashPoint = (Rectangle) h.createIntersection(botBorder.getBounds());
			System.out.println("hit");
		}
	}
}
