package gamePackage;

import javax.swing.*;

import gamePackage.StartHeliGame.STATE;

import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
	
	//private JFrame menu;
	private JButton exitBtn;
	private JButton startBtn;
	
	
	
	public Menu(){
		
		StartHeliGame.gameState = STATE.MENU;
		//menu = new JFrame();
		super.setTitle("Heli Game Menu");
		setLayout(null);
		setSize(600, 400);
		//exit button
		exitBtn = new JButton ("Exit");
		exitBtn.setBounds(50, 50, 100, 30);
		exitBtn.addActionListener(this);
		add(exitBtn);
		//start button
		startBtn = new JButton ("Start");
		startBtn.setBounds(300, 200, 100, 30);
		startBtn.addActionListener(this);
		add(startBtn);
		
        
        
	}
	
	public void actionPerformed(ActionEvent e){
        if (e.getSource() == startBtn){//if button is pushed play this stuff
        	dispose();//of menu frame
            MainGame mainGame = new MainGame();//starts a game
            
            
        }
        
        if (e.getSource() == exitBtn){//if button is pushed exit
            System.exit(0);
        }
	}
	
	
	public void exit(){
		
	}
}
