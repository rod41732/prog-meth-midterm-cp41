package character;

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
		if (getSpeed() != o.getSpeed())
			return Double.compare(getSpeed(), o.getSpeed());
		return Double.compare(o.getPriority(), getPriority());
	}
}
