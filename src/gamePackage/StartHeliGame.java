package gamePackage;



public class StartHeliGame {
	public enum STATE{
    	MENU,
    	GAME,
    	GAMEOVER
    };
    public static STATE gameState;
    
    public static void main(String args[]){
        
            Menu m = new Menu();
            m.setVisible(true);
            
    }
    
}
