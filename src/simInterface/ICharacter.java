package simInterface;

public interface ICharacter {
	void start();
	void run();
	double getSpeed();
	double getDistance();
	void setSpeed(double speed);
	void setDistance(double distance);
	int getPriority();
	int compareTo(ICharacter o);
}
