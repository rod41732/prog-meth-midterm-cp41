package test;

import org.junit.*;

import character.RunnerChocobo;
import static org.junit.Assert.*;

public class TestRunnerChocobo
{
	private static final double EPSILON = 1e-15;
	
	@Test public void testConstructor() {
		RunnerChocobo runnerChocobo = new RunnerChocobo();
		assertEquals("RunnerChocobo speed must be 3", 3, runnerChocobo.getSpeed(), EPSILON);
	}
	
	@Test public void testStart() {
		RunnerChocobo runnerChocobo = new RunnerChocobo();
		runnerChocobo.start();
		assertEquals("RunnerChocobo start distance must be 0", 0, runnerChocobo.getDistance(), EPSILON);
	}
	
	@Test public void testRun() {
		RunnerChocobo runnerChocobo = new RunnerChocobo();
		double distance = runnerChocobo.getDistance();
		runnerChocobo.run();
		assertEquals("RunnerChocobo speed must be 3", 3, runnerChocobo.getSpeed(), EPSILON);
		assertEquals("New distance must be old distance + speed", distance + 3, runnerChocobo.getDistance(), EPSILON);
	}

}
