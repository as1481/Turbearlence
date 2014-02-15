package states;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;



public class GameOverState extends BasicGameState {
	
	private static Image gameOverBackground, playAgainButton, quitButton, menuButton;
	private static Image playAgainHover, quitHover, menuHover;
	public File ScoreFile = new File("Scores.txt");
	int HighScore[] = new int[]{0,0,0};
	
	public GameOverState(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)
				throws SlickException {
		
		gameOverBackground = new Image("res/menu_graphics/gameover_screen.png");
		playAgainButton = new Image("res/menu_graphics/playagain_button.png");
		quitButton = new Image("res/menu_graphics/quit_button.png");
		menuButton = new Image("res/menu_graphics/menu_button.png");
		playAgainHover = new Image("res/menu_graphics/playagain_hover.png");
		quitHover = new Image("res/menu_graphics/quit_hover.png");
		menuHover = new Image("res/menu_graphics/menu_hover.png");
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
				throws SlickException{
		
		gameOverBackground.draw(0,0);
		
		int posX=Mouse.getX();
		int flippedposY=Mouse.getY();
		//Fixing posY to reflect graphics coords
		int posY = 600 - flippedposY;
		
		if (posX>728&&posX<844&&posY>380&&posY<426){
			menuHover.draw(728,380);
		} else {
			menuButton.draw(728,380);
		}
		
		if (posX>354&&posX<582&&posY>380&&posY<424){
			playAgainHover.draw(354,380);
		} else {
			playAgainButton.draw(354,380);
		}
		
		if ((posX > 1150 && posX < 1170) && (posY > 550 && posY < 580)){
			quitHover.draw(1148,556);
		} else {
			quitButton.draw(1148,556);
		}
		
		g.setColor(Color.white);
		g.drawString("Score:" + getScore(), 550, 500);
		updateHighScore(getScore());
		g.drawString("High Scores:", 100,100);
		g.drawString( "Beartacular: "+ HighScore[0], 100, 120);
		g.drawString( "Beary good: "+ HighScore[1], 100, 140);
		g.drawString( "Appawling: "+ HighScore[2], 100, 160);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		
		int posX=Mouse.getX();
		int posY=Mouse.getY();
		
		posY = 600 - posY;
		
		if(posX>354&&posX<582&&posY>380&&posY<424&&Mouse.isButtonDown(0)) {
			sbg.enterState(1);
		}
		
		if(posX>728&&posX<844&&posY>380&&posY<426&&Mouse.isButtonDown(0)) { // 116 46
			sbg.enterState(0);
		}
		
		if((posX > 1150 && posX < 1170) && (posY > 550 && posY < 580)) {
			if(Mouse.isButtonDown(0)) {
				System.exit(0);
			}
			
		}
		else {
		}
	}

	public int getID() {
			return 2;
	}
	
	public int getScore(){
		int c = 0;
		try{
			File inputFile = new File("Score.txt");
			FileInputStream fis= new FileInputStream(inputFile);
			 

	            
	              c = fis.read();
	               
	            
			fis.close();
		
		}
		catch(FileNotFoundException e){
			  System.err.println("Error: " + e);
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
		return c;
	}
	
	
	public void getHighScore(){
		try{
		
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ScoreFile));
		HighScore = (int[])inputStream.readObject();
		inputStream.close();
		}
		catch(FileNotFoundException e){
			  System.err.println("Error: " + e);
      } catch (IOException e) {
          System.err.println("Error: " + e);
      } catch (ClassNotFoundException e) {
    	  System.err.println("Error: "+ e);
      }
	}
	
	public void updateHighScore(int newS){
		getHighScore();	
		for (int i = 0; i < 3; i++){
			if (newS >= HighScore[i]){
				HighScore[i] = newS; 
				i = 4; 
			}
		}
			
		try{
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ScoreFile));
		outputStream.writeObject(HighScore);
		outputStream.close();
		
		}
		catch(FileNotFoundException e){
			  System.err.println("Error: " + e);
      } catch (IOException e) {
          System.err.println("Error: " + e);
      }
	}
}

