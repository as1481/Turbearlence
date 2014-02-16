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
	public File scoreFile = new File("Scores.txt");
	int highScore[] = new int[]{0,0,0};
	int score;
	
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
		score = getScore();
		updateHighScore(score);
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
		g.drawString("Score:" + score, 550, 500);
		g.drawString("High Scores:", 100,100);
		g.drawString( "Beartacular: "+ highScore[0], 100, 120);
		g.drawString( "Beary good: "+ highScore[1], 100, 140);
		g.drawString( "Appawling: "+ highScore[2], 100, 160);
		
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
	
	/**
	 * Gets the score from the last game session. This is used to update the high score list
	 * @return the score from the last game session
	 */
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
	
	/**
	 * Gets and stores the list of current high scores in the highScore array.
	 */
	public void getHighScore(){
		try{
		
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(scoreFile));
		highScore = (int[])inputStream.readObject(); // read in the list of high scores
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
	
	/**
	 * Updates the list of high scores
	 * @param newS the new score to be checked against the list of high scores
	 */
	public void updateHighScore(int newS){
		getHighScore();	//get the high scores
		//check newS versus the high score list
		for (int i = 0; i < 3; i++){
			if (newS > highScore[i]){ // newS to be added to the high scores
				int temp = highScore[i]; // store the old high score temporarily
				highScore[i] = newS; //replace the old high score
				newS = temp;
				i = 0;  //check again with the old score, shuffling it down the list
			}
		}
			
		try{ // write the high scores back to the file
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(scoreFile));
		outputStream.writeObject(highScore);
		outputStream.close();
		
		}
		catch(FileNotFoundException e){
			  System.err.println("Error: " + e);
      } catch (IOException e) {
          System.err.println("Error: " + e);
      }
	}
}

