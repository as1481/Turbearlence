package logicClasses;

public class Score {
	
	private int _flightsSuccessful;
	private double _timePlayed;
	private double _timeManual;
	private int _SeparationViolated;
	private int _gameOvers;
	
	public Score(){
		_timePlayed = 0;
		_timeManual = 0;
		_SeparationViolated = 0;
		_flightsSuccessful = 0;
		_gameOvers = 0;
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
	
	public int gameOvers()
	{
		return _gameOvers;
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
	
	public void addGameOver() {
		_gameOvers ++;
	}
	
	public String calculate() {
		int score = 0;
		score += (int)(_timePlayed);
		score += (100 * _flightsSuccessful);
		score -= (100 *_SeparationViolated);
		score -= (int)(_timeManual);
		score -= (10000 * _gameOvers);
		return " "+ score;
	}

}