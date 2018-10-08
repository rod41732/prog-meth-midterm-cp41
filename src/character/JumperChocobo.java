package character;

import simInterface.IObstructable;

public class JumperChocobo extends Chocobo implements IObstructable{
	private int turnCount;
	private int obstructedDuration;
	
	public JumperChocobo() {
		setSpeed(7.5);
		obstructedDuration = 0;
		turnCount = 0;
	}
	
	public void start() {
		setDistance(0);
		turnCount = 0;
	}
	
	public double getSpeed() {
		if (obstructedDuration > 0)
			return super.getSpeed()/2;
		return super.getSpeed();
	}
	
	public int getObstructedDuration() {
		return obstructedDuration;
	}
	
	
	public void setObstructedDuration(int obstructedDuration) {
		this.obstructedDuration = obstructedDuration;
	}

	public int getPriority() {
		return 2;
	}
	
	public void run() {
		turnCount++;
		if (turnCount%2 == 0) {
			setDistance(getDistance() + getSpeed());
		}
	}
	
	public void obstructed() {
		if (obstructedDuration > 0) return;
		setObstructedDuration(2);
	}
	public String toString() {
		return String.format("Jumper Chocobo Speed=%.2f, Dist=%.2f, Obstructed=%d", getSpeed(), getDistance(), getObstructedDuration());
	}
}
