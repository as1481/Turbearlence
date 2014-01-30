package logicClasses;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Airport {
	
	//FIELDS
	
	static Image airportImage;
	
	//location of Airport
	float x, y;
	
	//CONSTRUCTOR
	
	Airport(float x, float y) {
		this.x = x;
		this.y = y;

	}
	
	public void init(GameContainer gc) throws SlickException {
		airportImage = new Image("res/graphics/airport.png");
	}
	
	public void render(Graphics g, GameContainer gc) throws SlickException { 
		
		airportImage.draw(x, y); // Airport image centred in middle of airspace
	} 
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	

}
