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
	


	public void addTime(double dt) {
		_timePlayed += dt;
	}
	
	public void addTimeManual(double dt) {
		_timeManual += dt;
	}
	
	public void addSeparationViolated(int dt) {
		_SeparationViolated += dt;
	}

	public void addFlight() {
		_flightsSuccessful ++;
	}
	
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
	
	
	
	public int calculate() {
		int score = 0;
		score += (int)(_timePlayed/_scalePlus);
		score += (100 * _flightsSuccessful);
		score -= (_SeparationViolated/_scaleMinus);
		score -= (int)(_timeManual);
		return score;
	}

}