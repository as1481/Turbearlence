package logicClasses;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Airport {
	
	//FIELDS
	
	static Image airportImage;
	public final static String name = "Airport";
	
	//location of Airport
	float x, y;
	EntryPoint takeOffPoint;
	ExitPoint landingPoint;
	
	private boolean flightOnRunway = false;
	
	//CONSTRUCTOR

	Airport(float x, float y) {
		this.x = x;
		this.y = y;
		this.takeOffPoint = new EntryPoint(x,y);
		this.landingPoint = new ExitPoint(x,y, name);
	}
	
	public void init(GameContainer gc) throws SlickException {
		airportImage = new Image("res/graphics/airport.png");
	}
	
	// RENDERER
	
	public void render(Graphics g, GameContainer gc) throws SlickException { 

		//draw airport accounting for image origin being at airport's x, y
		// this centres the airport image on the airport location.
		airportImage.draw(this.x - airportImage.getWidth()/2, this.y - airportImage.getHeight()/2);
		
	} 
	
	// MUTATORS AND ACCESSORS
	
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

	public boolean isFlightOnRunway() {
		return flightOnRunway;
	}

	public void setFlightOnRunway(boolean flightOnRunway) {
		this.flightOnRunway = flightOnRunway;
	}
}
