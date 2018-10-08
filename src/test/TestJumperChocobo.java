package test;

import org.junit.*;

import character.JumperChocobo;
import static org.junit.Assert.*;

public class TestJumperChocobo
{	
	private static final double EPSILON = 1e-15;
	
	@Test public void testConstructor() {
		JumperChocobo jumperChocobo = new JumperChocobo();
		assertEquals("obstructedDuration must be 0", 0, jumperChocobo.getObstructedDuration());
		assertEquals("JumperChocobo speed must be 7.5", 7.5, jumperChocobo.getSpeed(), EPSILON);
	}
	
	@Test public void testStart() {
		JumperChocobo jumperChocobo = new JumperChocobo();
		jumperChocobo.start();
		assertEquals("JumperChocobo start distance must be 0", 0, jumperChocobo.getDistance(), EPSILON);
	}
	
	@Test public void testRun() {
		JumperChocobo jumperChocobo = new JumperChocobo();
		jumperChocobo.start();
		
		// Prepare to jump
		double distance = jumperChocobo.getDistance();
		jumperChocobo.run();
		assertEquals("JumperChocobo distance must not be changed", distance, jumperChocobo.getDistance(), EPSILON);
		
		// Jump
		jumperChocobo.run();
		assertEquals("New distance must be old distance + speed", distance + 7.5, jumperChocobo.getDistance(), EPSILON);
		
		// Prepare to jump
		distance = jumperChocobo.getDistance();
		jumperChocobo.run();
		assertEquals("JumperChocobo distance must not be changed", distance, jumperChocobo.getDistance(), EPSILON);
		
		// Jump
		jumperChocobo.run();
		assertEquals("New distance must be old distance + speed", distance + 7.5, jumperChocobo.getDistance(), EPSILON);
	}
	
	@Test public void testObstructed() {
		JumperChocobo jumperChocobo = new JumperChocobo();
		jumperChocobo.obstructed();
		assertEquals("obstructedDuration must be 2", 2, jumperChocobo.getObstructedDuration());
		assertEquals("JumperChocobo speed must be 3.75", 3.75, jumperChocobo.getSpeed(), EPSILON);
	}

}
