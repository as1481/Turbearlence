package states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

import stateContainer.Game;


public class ControlsState extends BasicGameState {
	
	private int pageNumber;
	private static Image controlsBackgroundPage1,controlsBackgroundPage2, backButton, nextPageButton, previousPageButton, menuButton, quitButton, backButtonHover;
    private static Image nextPageHover, previousPageHover, quitHover;
    
    //CONSTRUCTOR
	public ControlsState(int state){	
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	
		try{
			pageNumber = 1;
			
			controlsBackgroundPage1 = new Image("res/menu_graphics/controls1.png");
			controlsBackgroundPage2 = new Image("res/menu_graphics/controls2.png");
			backButton = new Image("res/menu_graphics/back.png");
			backButtonHover = new Image("res/menu_graphics/back_hover.png");
			nextPageButton = new Image("res/menu_graphics/next page.png");
			nextPageHover = new Image("res/menu_graphics/next page_hover.png");
			previousPageButton = new Image("res/menu_graphics/previous page.png");
			previousPageHover = new Image("res/menu_graphics/previous hover.png");
			quitButton = new Image("res/menu_graphics/quit_button.png");
			quitHover = new Image("res/menu_graphics/quit_hover.png");
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException{
		
		int posX=Mouse.getX();
		int flippedposY=Mouse.getY();
		//Fixing posY to reflect graphics coords
		int posY = 600 - flippedposY;
		
		if (pageNumber == 1) {
			controlsBackgroundPage1.draw(0,0);
			
			
			if ((posX > 1030 && posX < 1193) && (posY > 280 && posY < 315)){
				nextPageHover.draw(1030,280);
			} else {
				nextPageButton.draw(1030,280);
			}
			
			if ((posX > 1150 && posX < 1170) && (posY > 550 && posY < 580)){
				quitHover.draw(1148,556);
			} else {
				quitButton.draw(1148,556);
			}
			
		}
		
		else if (pageNumber == 2){
			controlsBackgroundPage2.draw(0,0);
			backButton.draw(20,20);

			if ((posX > 30 && posX < 241) && (posY > 280 && posY < 315)){
				previousPageHover.draw(30,280);
			} else {
				previousPageButton.draw(30,280);
			}
			
			if ((posX > 1150 && posX < 1170) && (posY > 550 && posY < 580)){
				quitHover.draw(1148,556);
			} else {
				quitButton.draw(1148,556);
			}
			
		}
		
		if ((posX > 20 && posX < 40) && (posY > 20 && posY < 40)){
			backButtonHover.draw(20,20);
		}else{
			backButton.draw(20,20);
		}

		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
	
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		posY = 600 - posY;
		
		if((posX > 20 && posX < 40) && (posY > 20 && posY < 40)) {
			if(Mouse.isButtonDown(0)) {
				pageNumber = 1;
				sbg.enterState(Game.MENUSTATE);
			}
			
		}
		
		if((posX > 1150 && posX < 1170) && (posY > 550 && posY < 580)) {
			if(Mouse.isButtonDown(0)) {
				System.exit(0);
			}
			
		}

		if (pageNumber == 1){
			
			if((posX > 1030 && posX < 1193) && (posY > 280 && posY < 315)) {
				if(Mouse.isButtonDown(0)) {
					pageNumber = 2;
				}	
			}
		}
		
		if (pageNumber == 2){
			if((posX > 30 && posX < 241) && (posY > 280 && posY < 315)) {
				if(Mouse.isButtonDown(0)) {
					pageNumber = 1;
				}
			}
		}
	
	}

	public int getID(){
		return 5;
	}
	
	
}
