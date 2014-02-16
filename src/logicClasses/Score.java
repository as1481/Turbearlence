package logicClasses;

public class Score {
	
	private int _flightsSuccessful;
	private double _timePlayed;
	private double _timeManual;
	private int _SeparationViolated;
	private int _difficultyLevel;
	public int _scalePlus;
	public int _scaleMinus; 

	
	public Score(){
		_timePlayed = 0;
		_timeManual = 0;
		_SeparationViolated = 0;
		_flightsSuccessful = 0;
		_difficultyLevel = 1; 
		_scalePlus = 100;
		_scaleMinus = 2; 
		

	}

	/**
	 * Add time to the time played variable
	 * @param dt the time to be added
	 */
	public void addTime(double dt) {
		_timePlayed += dt;
	}
	
	/**
	 * Add time to the time a flight has been under manual control
	 * @param dt the time to be added
	 */
	public void addTimeManual(double dt) {
		_timeManual += dt;
	}
	
	/**
	 * Add time to the time separation rules have been violated
	 * @param dt the time to add
	 */
	public void addSeparationViolated(int dt) {
		_SeparationViolated += dt;
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
		_difficultyLevel = x; 
		if (x == 1){
			_scalePlus = 100;
			_scaleMinus = 3;
		}
		if (x == 2){
			_scalePlus = 70;
			_scaleMinus = 1;
		}
		if (x == 3){
			_scalePlus = 40;
			_scaleMinus = 1;
		}
	}
	
	
	/**
	 * Calculate the score from the current score variables
	 * @return the score at the current time
	 */
	public int calculate() {
		int score = 0;
		score += (int)(_timePlayed/_scalePlus);
		score += (100 * _flightsSuccessful);
		score -= (_SeparationViolated/_scaleMinus);
		score -= (int)(_timeManual);
		return score;
	}
	
	/// ACCESSORS
	
	public double timePlayed() {
		return _timePlayed;
	}

	public int flightsSuccessful() {
		return _flightsSuccessful;
	}
	
	public double manualTime()
	{
		return _timeManual;
	}
	
	public int SeparationViolated()
	{
		return _SeparationViolated;
	}
	

}