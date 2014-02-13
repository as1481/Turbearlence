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
		//Waypoints
		airspaceLanding.newWaypoint(350, 150, "A");
    	airspaceLanding.newWaypoint(400, 470, "B");
    	airspaceLanding.newWaypoint(700, 60,  "C");
    	airspaceLanding.newWaypoint(800, 320, "D");
    	airspaceLanding.newWaypoint(600, 418, "E");
    	airspaceLanding.newWaypoint(500, 220, "F");
    	airspaceLanding.newWaypoint(950, 188, "G");
    	airspaceLanding.newWaypoint(1050, 272,"H");
    	airspaceLanding.newWaypoint(900, 420, "I");
    	airspaceLanding.newWaypoint(240, 250, "J");
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
		
		//Flight must be at ground level ie on the runway
		assertTrue(flightTakeOff.getAltitude() == 0);
		//Flight must be requesting to take off
		assertTrue(flightTakeOff.isRequestingToTakeOff() == true);
		//Flight must not yet be permitted to take off
		assertTrue(flightTakeOff.isPermittedToTakeOff() == false);
		//The Airport should have raised the flight on runway flag.
		assertTrue(airspaceTakeOff.getAirport().isFlightOnRunway() == true);
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
		assertTrue(flightTakeOff.getAltitude() == Flight.changeAltitudeRate);
		assertTrue(flightTakeOff.isRequestingToTakeOff() == false);
		assertTrue(flightTakeOff.isPermittedToTakeOff() == true);
		//there should no longer be a flight on the runway
		assertTrue(airspaceTakeOff.getAirport().isFlightOnRunway() == false);
		
		//update a few more times to move flight further
		for (int i = 0; i<=10; i++){
			flightTakeOff.update();
		}
		//ensure that a flight permitted to take off moves from the airport.
		assertTrue(oldX != flightTakeOff.getX() && oldY != flightTakeOff.getY());
	}
	
	@Test
	public void initialLandingConditions(){
		//since only exit point is the airport, flight MUST need to land at some point in it's plan.
		ExitPoint lastWaypoint = flightLanding.getFlightPlan().getExitPoint();
		double airportX = airspaceLanding.getAirport().getLandingPoint().getX();
		double airportY = airspaceLanding.getAirport().getLandingPoint().getY();
		
		//last waypoint in flight plan should be the airport for landing.
		assertTrue(lastWaypoint.getX() == airportX);
	}
	
	@Test
	public void checkRequestToLand(){
		//update until target waypoint is the landing exit point
		while(flightLanding.getFlightPlan().getPointByIndex(0) != flightLanding.getFlightPlan().getExitPoint()){
			flightLanding.update();
		}
		//update once further, so flight notices the next target is the landing exit point via updateRequiredToLand
		flightLanding.update();
		//since target is exit point, and target is the airport landing point, flight must be requesting to land
		assertTrue(flightLanding.isRequestingToLand() == true);
		//flight has not yet been permitted to land.
		assertTrue(flightLanding.isPermittedToLand() == false);
	}
	
	@Test
	public void checkPermitToLand(){
		//update until target waypoint is the landing exit point
		while(flightLanding.getFlightPlan().getPointByIndex(0) != flightLanding.getFlightPlan().getExitPoint()){
			flightLanding.update();
		}
		//update once further, so flight notices the next target is the landing exit point via updateRequiredToLand
		flightLanding.update();
		
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
