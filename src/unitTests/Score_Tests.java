package unitTests;

import static org.junit.Assert.*;
import logicClasses.Score;
import org.junit.Test;

public class Score_Tests {
	
	// Test addTime Function
	@Test
	public void testAddTime() {
		Score testScore = new Score();
		testScore.addTime(100);
		testScore.addTime(50);
		assertTrue(150 == testScore.timePlayed());
	}
	// Test addManualTime function
	@Test
	public void testAddManualTime() {
		Score testScore = new Score();
		testScore.addTimeManual(100);
		testScore.addTimeManual(900);
		assertTrue(1000 == testScore.manualTime());
	}
	@Test
	// Test addSeparationViolated function
	public void testAddSeparationViolated() {
		Score testScore = new Score();
		testScore.addSeparationViolated(4);
		testScore.addSeparationViolated(10);
		assertTrue(14 == testScore.SeparationViolated());
	}
	@Test
	// Test addFlight function
	public void testAddFlight() {
		Score testScore = new Score();
		testScore.addFlight();
		testScore.addFlight();
		testScore.addFlight();
		assertTrue(3 == testScore.flightsSuccessful());
	}
	@Test
	// Test setDifficulty function 1
	public void testSetDifficulty1() {
		Score testScore = new Score();
		testScore.setDifficulty(3);
		testScore.addTime(400);
		int score = testScore.calculate();
		assertTrue(10 == score);
	}
	@Test
	// Test setDifficulty function 2
	public void testsetDifficulty2() {
		Score testScore = new Score();
		testScore.setDifficulty(1);
		testScore.addTime(300);
		int score = testScore.calculate();
		assertTrue(3 == score);
	}
	@Test
	// Test Calculate 1
	public void testCalculate1() {
		Score testScore = new Score();
		testScore.setDifficulty(1);
		testScore.addTime(900);
		testScore.addTimeManual(300);
		testScore.addSeparationViolated(300);
		testScore.addFlight();
		testScore.addFlight();
		testScore.addFlight();
		testScore.addFlight();
		testScore.addFlight();
		testScore.addFlight();
		
		int score = testScore.calculate();		
		assertTrue(209 == score);
	}
	@Test
	// Test Calculate 2
	public void testCalculate2() {
		Score testScore = new Score();
		testScore.setDifficulty(2);
		testScore.addTime(7000);
		testScore.addSeparationViolated(10);
		testScore.addTimeManual(10);
		testScore.addFlight();
		testScore.addFlight();
		testScore.addFlight();
		
		int score = testScore.calculate();
		assertTrue(380 == score);
	}
	
	

}
