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
	EntryPoint takeOffPoint;
	ExitPoint landingPoint;
	
	//CONSTRUCTOR
	
	Airport(float x, float y) {
		this.x = x;
		this.y = y;
		this.takeOffPoint = new EntryPoint(x,y);
		this.landingPoint = new ExitPoint(x,y, "Airport");
	}
	
	public void init(GameContainer gc) throws SlickException {
		airportImage = new Image("res/graphics/airport.png");
	}
	
	public void render(Graphics g, GameContainer gc) throws SlickException { 
		
		//airportImage.draw(this.x, this.y); // Airport image drawn with the origin of the image starting at the airport's x,y loc
		
		//draw airport accounting for image origin being at airport's x, y
		// this centres the airport image on the airport location.
		airportImage.draw(this.x - airportImage.getWidth()/2, this.y - airportImage.getHeight()/2);
		
	} 
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public EntryPoint getTakeOffPoint() {
		return takeOffPoint;
	}

	public ExitPoint getLandingPoint() {
		return landingPoint;
	}

}
