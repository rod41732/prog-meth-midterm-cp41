package character;

import simInterface.ICharacter;
import simInterface.IObstructable;

public class Pikachu implements ICharacter, IObstructable {
	private int obstructedDuration;
	private double speed;
	private double distance;
	
	@Override
	public void obstructed() {
		if (obstructedDuration >0) return;
		obstructedDuration = 1;
		setSpeed(4);
	}

	@Override
	public void setObstructedDuration(int duration) {
		obstructedDuration = duration;
	}

	@Override
	public int getObstructedDuration() {
		return obstructedDuration;
	}

	public Pikachu() {
		super();
		this.speed = 4;
	}

	@Override
	public void start() {
		setDistance(0);
	}

	@Override
	public void run() {
		if (getObstructedDuration() == 0)
			setDistance(getDistance() + getSpeed());
	}

	@Override
	public double getSpeed() {
		if (obstructedDuration > 0)
			return 0;
		return speed;
	}

	@Override
	public double getDistance() {
		return distance;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 1;
	}
	public int compareTo(ICharacter o) {
		if (Double.compare(getDistance(), o.getDistance()) != 0)
			return Double.compare(getDistance(), o.getDistance()) < 0 ? -1 : 1;
		return Double.compare(o.getPriority(), getPriority()) < 0 ? -1 : 1;
	}
	
	public String toString() {
		return String.format("Pikachu Speed=%.2f, Dist=%.2f, Obstructed=%d", getSpeed(), getDistance(), getObstructedDuration());
	}
}
