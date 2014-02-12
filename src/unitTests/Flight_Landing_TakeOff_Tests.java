package unitTests;

import static org.junit.Assert.*;
import logicClasses.*;

import org.junit.Test;
import org.junit.Before;

public class Flight_Landing_TakeOff_Tests {
	
	private Airspace airspaceTakeOff, airspaceLanding;
	private Flight flightTakeOff, flightLanding;
	
	@Before
	public void setUpTakeOff(){
		airspaceTakeOff = new Airspace();
    	//Waypoints
    	airspaceTakeOff.newWaypoint(350, 150, "A");
    	airspaceTakeOff.newWaypoint(400, 470, "B");
    	airspaceTakeOff.newWaypoint(700, 60,  "C");
    	airspaceTakeOff.newWaypoint(800, 320, "D");
    	airspaceTakeOff.newWaypoint(600, 418, "E");
    	airspaceTakeOff.newWaypoint(500, 220, "F");
    	airspaceTakeOff.newWaypoint(950, 188, "G");
    	airspaceTakeOff.newWaypoint(1050, 272,"H");
    	airspaceTakeOff.newWaypoint(900, 420, "I");
    	airspaceTakeOff.newWaypoint(240, 250, "J");
    	//entry point - just the airport takeOff point.
    	airspaceTakeOff.addEntryPoint(airspaceTakeOff.getAirport().getTakeOffPoint());
    	//exit points - just the ones which aren't a landing point.
		airspaceTakeOff.newExitPoint(800, 0, "1");
		airspaceTakeOff.newExitPoint(150, 250, "2");
		airspaceTakeOff.newExitPoint(1200, 350, "3");
		
		flightTakeOff = new Flight(airspaceTakeOff);
	}
	
	@Before
	public void setUpLanding(){
		airspaceLanding = new Airspace();
		//No waypoints, ie. go directly to exit point from entry
		//entry point - any which arent a take off point
    	airspaceLanding.newEntryPoint(150, 400);
		airspaceLanding.newEntryPoint(1200, 200);
		airspaceLanding.newEntryPoint(600, 0);
		//exit point - only the landing point
		airspaceLanding.addExitPoint(airspaceLanding.getAirport().getLandingPoint());
		
		flightLanding = new Flight(airspaceLanding);
	}
	
	@Test
	public void initialTakeOffConditions(){
		//since only entry point is the airport, the flight MUST have generated on the runway
		//Testing inital conditions for a flight which must take off
		assertTrue(flightTakeOff.getAltitude() == 0);
		assertTrue(flightTakeOff.isRequestingToTakeOff() == true);
		assertTrue(flightTakeOff.isPermittedToTakeOff() == false);
	}
	
	@Test
	public void permitTakeOff(){
		double oldX = flightTakeOff.getX();
		double oldY = flightTakeOff.getY();
		//permit flight to take off
		flightTakeOff.permitTakeOff();
		//update flight once
		flightTakeOff.update();
		//test conditions after one update - flight allowed to take off, no longer requesting to take off
		// flight should have ascended and moved from initial position.
		assertTrue(flightTakeOff.getAltitude() == flightTakeOff.changeAltitudeRate);
		assertTrue(flightTakeOff.isRequestingToTakeOff() == false);
		assertTrue(flightTakeOff.isPermittedToTakeOff() == true);
		assertTrue(oldX != flightTakeOff.getX() && oldY != flightTakeOff.getY());
	}
	
	@Test
	public void initialLandingConditiond(){
		//since only exit point is the airport, flight MUST need to land at some point in it's plan.
		ExitPoint lastWaypoint = flightLanding.getFlightPlan().getExitPoint();
		double airportX = airspaceLanding.getAirport().getLandingPoint().getX();
		double airportY = airspaceLanding.getAirport().getLandingPoint().getY();
		
		//last waypoint in flight plan should be the airport for landing.
		assertTrue(lastWaypoint.getX() == airportX && lastWaypoint.getY() == airportY);
	}
	
	@Test
	public void checkRequestToLand(){
		//update once. updateRequiredToLand() in update() should notice that target is the landing exit point.
		flightLanding.update();
		//since target is exit point, and target is the airport landing point, flight must be requesting to land
		assertTrue(flightLanding.isRequestingToLand() == true);
		//flight has not yet been permitted to land.
		assertTrue(flightLanding.isPermittedToLand() == false);
	}
	
	@Test
	public void permitToLand(){
		//permit flight to land.
		flightLanding.permitToLand();
		//flight is now permitted to land
		assertTrue(flightLanding.isPermittedToLand() == true);
		//flight should no longer be requesting to land
		assertTrue(flightLanding.isRequestingToLand() == false);
		//flight's target altitude should be zero.
		assertTrue(flightLanding.getTargetAltitude() == 0);
		
	}
	

}
