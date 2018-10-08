package character;

public class RunnerChocobo extends Chocobo{
	
	public RunnerChocobo() {
		setSpeed(3);
	}
	
	public void start() {
		setDistance(0);
	}
	
	public void run() {
		setDistance(getDistance() + getSpeed());
	}
	
	public int getPriority() {
		return 3;
	}
	public String toString() {
		return String.format("Runner Speed=%.2f, Dist=%.2f,", getSpeed(), getDistance());
	}
}
