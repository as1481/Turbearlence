package logicClasses;

public class Score {
	
	private int _flightsSuccessful;
	private double timePlayed;
	private double timeManual;
	private int SeparationViolated;
	private int difficultyLevel;
	public int scalePlus;
	public int scaleMinus; 

	
	public Score(){
		timePlayed = 0;
		timeManual = 0;
		SeparationViolated = 0;
		_flightsSuccessful = 0;
		difficultyLevel = 1; 
		scalePlus = 100;
		scaleMinus = 2; 
		

	}

	/**
	 * Add time to the time played variable
	 * @param dt the time to be added
	 */
	public void addTime(double dt) {
		timePlayed += dt;
	}
	
	/**
	 * Add time to the time a flight has been under manual control
	 * @param dt the time to be added
	 */
	public void addTimeManual(double dt) {
		timeManual += dt;
	}
	
	/**
	 * Add time to the time separation rules have been violated
	 * @param dt the time to add
	 */
	public void addSeparationViolated(int dt) {
		SeparationViolated += dt;
	}
	
	/**
	 * Increment the number of successful flights.
	 */
	public void addFlight() {
		_flightsSuccessful ++;
	}
	
	/**
	 * Set the difficulty modifier
	 * @param x the difficulty selected
	 */
	public void setDifficulty(int x){
		difficultyLevel = x; 
		if (x == 1){
			scalePlus = 100;
			scaleMinus = 3;
		}
		if (x == 2){
			scalePlus = 70;
			scaleMinus = 1;
		}
		if (x == 3){
			scalePlus = 40;
			scaleMinus = 1;
		}
	}
	
	
	/**
	 * Calculate the score from the current score variables
	 * @return the score at the current time
	 */
	public int calculate() {
		int score = 0;
		score += (int)(timePlayed/scalePlus);
		score += (100 * _flightsSuccessful);
		score -= (SeparationViolated/scaleMinus);
		score -= (int)(timeManual);
		return score;
	}
	
	/// ACCESSORS
	
	public double timePlayed() {
		return timePlayed;
	}

	public int flightsSuccessful() {
		return _flightsSuccessful;
	}
	
	public double manualTime()
	{
		return timeManual;
	}
	
	public int SeparationViolated()
	{
		return SeparationViolated;
	}
	

}