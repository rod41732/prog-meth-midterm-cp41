package character;

import simInterface.ICharacter;

public abstract class Chocobo implements ICharacter {
	private double speed; 
	private double distance;
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public abstract int getPriority();
	
	public int compareTo(ICharacter o) {
		if (Double.compare(getDistance(), o.getDistance()) != 0)
			return Double.compare(getDistance(), o.getDistance()) < 0 ? -1 : 1;
		return Double.compare(o.getPriority(), getPriority()) < 0 ? -1 : 1;
	}
	
}
