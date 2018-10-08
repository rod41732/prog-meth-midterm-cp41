package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.RacingManager;

import org.junit.Before;
import org.junit.Test;

import simInterface.ICharacter;
import character.Chocobo;
import character.JumperChocobo;
import character.Pikachu;
import character.RunnerChocobo;

public class TestRacingManager
{
	private static final double EPSILON = 1e-15;
	ICharacter characters[] = RacingManager.getCharacters();
	
	@Before public void setup() {
		RacingManager.initializeCharacter();
	}
	
	@Test public void test() {
		assertEquals("GOAL must be 100", 100.00, RacingManager.getGoal(),EPSILON);
		assertFalse("Winner must be false", RacingManager.hasWinner());
	}
	
	@Test public void testInitializeCharacter() {
		int count = 0;
		for(ICharacter characters : characters) {
			if(characters instanceof RunnerChocobo) count += 1;
			else if(characters instanceof JumperChocobo) count += 10;
			else if(characters instanceof Pikachu) count += 100;
		}
		assertEquals("All character types must created", 111, count);
	}

	@Test public void testSortCharacter() {
		ICharacter characters[] = null;
		
		// Distance check.
		
		// Runner < Jumper < Pikachu
		setDistance(1, 2, 3);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("1st Character must be RunnerChocobo", characters[0] instanceof RunnerChocobo);
		assertTrue("2nd Character must be JumperChocobo", characters[1] instanceof JumperChocobo);
		assertTrue("3rd Character must be Pikachu", characters[2] instanceof Pikachu);
		
		// Runner < Pikachu < Jumper
		setDistance(1, 3, 2);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("1st Character must be RunnerChocobo", characters[0] instanceof RunnerChocobo);
		assertTrue("2nd Character must be Pikachu", characters[1] instanceof Pikachu);
		assertTrue("3rd Character must be JumperChocobo", characters[2] instanceof JumperChocobo);
		
		
		// Jumper < Runner < Pikachu
		setDistance(2, 1, 3);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("1st Character must be JumperChocobo", characters[0] instanceof JumperChocobo);
		assertTrue("2nd Character must be RunnerChocobo", characters[1] instanceof RunnerChocobo);
		assertTrue("3rd Character must be Pikachu", characters[2] instanceof Pikachu);
		
		// Jumper < Pikachu < Runner
		setDistance(3, 1, 2);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("1st Character must be JumperChocobo", characters[0] instanceof JumperChocobo);
		assertTrue("2nd Character must be Pikachu", characters[1] instanceof Pikachu);
		assertTrue("3rd Character must be RunnerChocobo", characters[2] instanceof RunnerChocobo);
		
		// Pikachu < Runner < Jumper
		setDistance(2, 3, 1);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("1st Character must be Pikachu", characters[0] instanceof Pikachu);
		assertTrue("2nd Character must be RunnerChocobo", characters[1] instanceof RunnerChocobo);
		assertTrue("3rd Character must be JumperChocobo", characters[2] instanceof JumperChocobo);
		
		// Pikachu < Jumper < Runner
		setDistance(3, 2, 1);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("1st Character must be Pikachu", characters[0] instanceof Pikachu);
		assertTrue("2nd Character must be JumperChocobo", characters[1] instanceof JumperChocobo);
		assertTrue("3rd Character must be RunnerChocobo", characters[2] instanceof RunnerChocobo);
				
		// Priority check.
		
		// Priority: RunnerChocobo > JumperChocobo > Pikachu
		
		setDistance(0, 0, 0);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("1st Character must be RunnerChocobo", characters[0] instanceof RunnerChocobo);
		assertTrue("2nd Character must be JumperChocobo", characters[1] instanceof JumperChocobo);
		assertTrue("3rd Character must be Pikachu", characters[2] instanceof Pikachu);
	
		setDistance(0, 0, 1);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("ast Character must be RunnerChocobo", characters[0] instanceof RunnerChocobo);
		assertTrue("2nd Character must be JumperChocobo", characters[1] instanceof JumperChocobo);
		assertTrue("3rd Character must be Pikachu", characters[2] instanceof Pikachu);
		
		setDistance(0, 1, 0);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("1st Character must be RunnerChocobo", characters[0] instanceof RunnerChocobo);
		assertTrue("2nd Character must be PikachuChocobo", characters[1] instanceof Pikachu);
		assertTrue("3rd Character must be Jumper", characters[2] instanceof JumperChocobo);
		
		setDistance(1, 0, 0);
		RacingManager.sortCharacter();
		characters = RacingManager.getCharacters();
		assertTrue("1st Character must be JumperChocobo", characters[0] instanceof JumperChocobo);
		assertTrue("2nd Character must be Pikachu", characters[1] instanceof Pikachu);
		assertTrue("3rd Character must be RunnerChocobo", characters[2] instanceof RunnerChocobo);
		
	}
	
	@Test public void testDecreaseObstructedDuration() {
		
		//Set obstructedDuration
		for(ICharacter crt : RacingManager.getCharacters()){
			if(crt instanceof JumperChocobo) ((JumperChocobo) crt).setObstructedDuration(2);
			else if(crt instanceof Pikachu) ((Pikachu) crt).setObstructedDuration(1);
		}
		
		for(ICharacter crt : RacingManager.getCharacters()){
			if(crt instanceof JumperChocobo)
				assertEquals("JumperChocobo obstructedDuration must be 2", 2, ((JumperChocobo)crt).getObstructedDuration(), EPSILON);
			if(crt instanceof Pikachu)
				assertEquals("Pikachu obstructedDuration must be 1", 1, ((Pikachu)crt).getObstructedDuration(), EPSILON);
		}
		
		main.RacingManager.decreaseObstructedDuration();
		
		for(ICharacter crt : RacingManager.getCharacters()){
			if(crt instanceof JumperChocobo)
				assertEquals("JumperChocobo obstructedDuration must be 1", 1, ((JumperChocobo)crt).getObstructedDuration(), EPSILON);
			if(crt instanceof Pikachu)
				assertEquals("Pikachu obstructedDuration must be 0", 0, ((Pikachu)crt).getObstructedDuration(), EPSILON);
		}
		
		main.RacingManager.decreaseObstructedDuration();
		
		for(ICharacter crt : RacingManager.getCharacters()){
			if(crt instanceof JumperChocobo)
				assertEquals("JumperChocobo obstructedDuration must be 0", 0, ((JumperChocobo)crt).getObstructedDuration(), EPSILON);
			if(crt instanceof Pikachu)
				assertEquals("Pikachu obstructedDuration must be 0", 0, ((Pikachu)crt).getObstructedDuration(), EPSILON);
		}
	}
	
	@Test public void testObstructedCharacter() {
		
		//Set obstructedDuration
		for(ICharacter crt : RacingManager.getCharacters()){
			if(crt instanceof JumperChocobo) {
				((JumperChocobo) crt).obstructed();
			}
			else if(crt instanceof Pikachu) {
				((Pikachu) crt).obstructed();
			}
		}
		
		for(ICharacter crt : RacingManager.getCharacters()){
			double distance = crt.getDistance();
			crt.run();
			if(crt instanceof JumperChocobo){
				//Prepare to jump
				assertEquals("JumperChocobo obstructedDuration must be 2", 2, ((JumperChocobo)crt).getObstructedDuration(), EPSILON);
				assertEquals("JumperChocobo Speed must be 3.75", 3.75, ((JumperChocobo)crt).getSpeed(), EPSILON);
				assertEquals("JumperChocobo distance must not be changed", distance, crt.getDistance(), EPSILON);
			}
			else if(crt instanceof Pikachu){
				assertEquals("Pikachu obstructedDuration must be 1", 1, ((Pikachu)crt).getObstructedDuration(), EPSILON);
				assertEquals("Pikachu Speed must be 0", 0, ((Pikachu)crt).getSpeed(), EPSILON);
				assertEquals("Pikachu distance must not be changed", distance, crt.getDistance(), EPSILON);
			}
			else{
				assertEquals("RunnerChocobo new distance must be old distance + speed", distance + 3, crt.getDistance(), EPSILON);
			}
		}
		
		RacingManager.decreaseObstructedDuration();
		
		for(ICharacter crt : RacingManager.getCharacters()){
			double distance = crt.getDistance();
			crt.run();
			if(crt instanceof JumperChocobo){
				//Jump
				assertEquals("JumperChocobo obstructedDuration must be 1", 1, ((JumperChocobo)crt).getObstructedDuration(), EPSILON);
				assertEquals("JumperChocobo Speed must be 3.75", 3.75, ((JumperChocobo)crt).getSpeed(), EPSILON);
				assertEquals("JumperChocobo new distance must be old distance + speed", distance + 3.75, crt.getDistance(), EPSILON);
			}
			else if(crt instanceof Pikachu){
				assertEquals("Pikachu obstructedDuration must be 0", 0, ((Pikachu)crt).getObstructedDuration(), EPSILON);
				assertEquals("Pikachu Speed must be 4", 4, ((Pikachu)crt).getSpeed(), EPSILON);
				assertEquals("Pikachu new distance must be old distance + speed", distance + 4, crt.getDistance(), EPSILON);
			}
			else{
				assertEquals("RunnerChocobo new distance must be old distance + speed", distance + 3, crt.getDistance(), EPSILON);
			}
		}
		
		RacingManager.decreaseObstructedDuration();
		
		for(ICharacter crt : RacingManager.getCharacters()){
			double distance = crt.getDistance();
			crt.run();
			if(crt instanceof JumperChocobo){
				//Prepare to jump
				assertEquals("JumperChocobo obstructedDuration must be 0", 0, ((JumperChocobo)crt).getObstructedDuration(), EPSILON);
				assertEquals("JumperChocobo Speed must be 7.5", 7.5, ((JumperChocobo)crt).getSpeed(), EPSILON);
				assertEquals("JumperChocobo distance must not be changed", distance, crt.getDistance(), EPSILON);
			}
			else if(crt instanceof Pikachu){
				assertEquals("Pikachu obstructedDuration must be 0", 0, ((Pikachu)crt).getObstructedDuration(), EPSILON);
				assertEquals("Pikachu Speed must be 4", 4, ((Pikachu)crt).getSpeed(), EPSILON);
				assertEquals("Pikachu new distance must be old distance + speed", distance + 4, crt.getDistance(), EPSILON);
			}
			else{
				assertEquals("RunnerChocobo new distance must be old distance + speed", distance + 3, crt.getDistance(), EPSILON);
			}
		}
		
		RacingManager.decreaseObstructedDuration();
		
		for(ICharacter crt : RacingManager.getCharacters()){
			double distance = crt.getDistance();
			crt.run();
			if(crt instanceof JumperChocobo){
				//Jump
				assertEquals("JumperChocobo obstructedDuration must be 0", 0, ((JumperChocobo)crt).getObstructedDuration(), EPSILON);
				assertEquals("JumperChocobo Speed must be 7.5", 7.5, ((JumperChocobo)crt).getSpeed(), EPSILON);
				assertEquals("JumperChocobo new distance must be old distance + speed", distance + 7.5, crt.getDistance(), EPSILON);
			}
			else if(crt instanceof Pikachu){
				assertEquals("Pikachu obstructedDuration must be 0", 0, ((Pikachu)crt).getObstructedDuration(), EPSILON);
				assertEquals("Pikachu Speed must be 4", 4, ((Pikachu)crt).getSpeed(), EPSILON);
				assertEquals("Pikachu new distance must be old distance + speed", distance + 4, crt.getDistance(), EPSILON);
			}
			else{
				assertEquals("RunnerChocobo new distance must be old distance + speed", distance + 3, crt.getDistance(), EPSILON);
			}
		}
	}
	
	@Test public void testObstructOverObstructedCharacter() {
		//Set obstructedDuration
		for(ICharacter crt : RacingManager.getCharacters()){
			if(crt instanceof JumperChocobo) {
				((JumperChocobo) crt).obstructed();
			}
			else if(crt instanceof Pikachu) {
				((Pikachu) crt).obstructed();
			}
		}
		
		for(ICharacter crt : RacingManager.getCharacters()){
			double distance = crt.getDistance();
			crt.run();
			if(crt instanceof JumperChocobo){
				//Prepare to jump
				assertEquals("JumperChocobo obstructedDuration must be 2", 2, ((JumperChocobo)crt).getObstructedDuration(), EPSILON);
				assertEquals("JumperChocobo Speed must be 3.75", 3.75, ((JumperChocobo)crt).getSpeed(), EPSILON);
				assertEquals("JumperChocobo distance must not be changed", distance, crt.getDistance(), EPSILON);
			}
			else if(crt instanceof Pikachu){
				assertEquals("Pikachu obstructedDuration must be 1", 1, ((Pikachu)crt).getObstructedDuration(), EPSILON);
				assertEquals("Pikachu Speed must be 0", 0, ((Pikachu)crt).getSpeed(), EPSILON);
				assertEquals("Pikachu distance must not be changed", distance, crt.getDistance(), EPSILON);
			}
			else{
				assertEquals("RunnerChocobo new distance must be old distance + speed", distance + 3, crt.getDistance(), EPSILON);
			}
		}
		
		RacingManager.decreaseObstructedDuration();
		
		for(ICharacter crt : RacingManager.getCharacters()){
			if(crt instanceof JumperChocobo) {
				((JumperChocobo) crt).obstructed();
			}
			else if(crt instanceof Pikachu) {
				((Pikachu) crt).obstructed();
			}
		}
		
		for(ICharacter crt : RacingManager.getCharacters()){
			double distance = crt.getDistance();
			crt.run();
			if(crt instanceof JumperChocobo){
				//Jump
				assertEquals("JumperChocobo obstructedDuration must be 1", 1, ((JumperChocobo)crt).getObstructedDuration(), EPSILON);
				assertEquals("JumperChocobo Speed must be 3.75", 3.75, ((JumperChocobo)crt).getSpeed(), EPSILON);
				assertEquals("JumperChocobo new distance must be old distance + speed", distance + 3.75, crt.getDistance(), EPSILON);
			}
			else if(crt instanceof Pikachu){
				assertEquals("Pikachu obstructedDuration must be 1", 1, ((Pikachu)crt).getObstructedDuration(), EPSILON);
				assertEquals("Pikachu Speed must be 0", 0, ((Pikachu)crt).getSpeed(), EPSILON);
				assertEquals("Pikachu distance must not be changed", distance, crt.getDistance(), EPSILON);
			}
			else{
				assertEquals("RunnerChocobo new distance must be old distance + speed", distance + 3, crt.getDistance(), EPSILON);
			}
		}
	}
	
	public void setDistance(double distance1,double distance2,double distance3) {
		for(ICharacter characters : RacingManager.getCharacters()) {
			if(characters instanceof RunnerChocobo) ((Chocobo)characters).setDistance(distance1);
			else if(characters instanceof JumperChocobo) ((Chocobo)characters).setDistance(distance2);
			else if(characters instanceof Pikachu) ((Pikachu)characters).setDistance(distance3);
		}
	}
	
	@Test public void testComparableTo() {
		RunnerChocobo runnerChocobo= new RunnerChocobo();
		JumperChocobo jumperChocobo = new JumperChocobo();
		Pikachu pikachu = new Pikachu();
		
		// Distance check.
		
		// Runner < Jumper
		runnerChocobo.setDistance(0.0);
		jumperChocobo.setDistance(1.0);
		assertEquals("runnerChocobo.compareTo(jumperChocobo)", -1, runnerChocobo.compareTo(jumperChocobo));
		assertEquals("jumperChocobo.compareTo(runnerChocobo)", 1, jumperChocobo.compareTo(runnerChocobo));
		
		// Runner > Jumper
		runnerChocobo.setDistance(1.0);
		jumperChocobo.setDistance(0.0);
		assertEquals("runnerChocobo.compareTo(jumperChocobo)", 1, runnerChocobo.compareTo(jumperChocobo));
		assertEquals("jumperChocobo.compareTo(runnerChocobo)", -1, jumperChocobo.compareTo(runnerChocobo));
		
		// Runner < Pikachu
		runnerChocobo.setDistance(0.0);
		pikachu.setDistance(1.0);
		assertEquals("runnerChocobo.compareTo(pikachu)", -1, runnerChocobo.compareTo(pikachu));
		assertEquals("pikachu.compareTo(runnerChocobo)", 1, pikachu.compareTo(runnerChocobo));
		
		// Runner > Pikachu
		runnerChocobo.setDistance(1.0);
		pikachu.setDistance(0.0);
		assertEquals("runnerChocobo.compareTo(pikachu)", 1, runnerChocobo.compareTo(pikachu));
		assertEquals("pikachu.compareTo(runnerChocobo)", -1, pikachu.compareTo(runnerChocobo));
		
		// Jumper < Pikachu
		jumperChocobo.setDistance(0.0);
		pikachu.setDistance(1.0);
		assertEquals("jumperChocobo.compareTo(pikachu)", -1, jumperChocobo.compareTo(pikachu));
		assertEquals("pikachu.compareTo(jumperChocobo)", 1, pikachu.compareTo(jumperChocobo));
		
		// Jumper > Pikachu
		jumperChocobo.setDistance(1.0);
		pikachu.setDistance(0.0);
		assertEquals("jumperChocobo.compareTo(pikachuChocobo)", 1, jumperChocobo.compareTo(pikachu));
		assertEquals("pikachu.compareTo(jumperChocobo)", -1, pikachu.compareTo(jumperChocobo));
		
		//Priority check.
		
		jumperChocobo.setDistance(0.0);
		pikachu.setDistance(0.0);
		runnerChocobo.setDistance(0.0);
		
		// Runner - Jumper
		runnerChocobo.compareTo(jumperChocobo);
		jumperChocobo.compareTo(runnerChocobo);
		assertEquals("runnerChocobo.compareTo(jumperChocobo)", -1, runnerChocobo.compareTo(jumperChocobo));
		assertEquals("jumperChocobo.compareTo(runnerChocobo)", 1, jumperChocobo.compareTo(runnerChocobo));
		
		// Runner - Pikachu
		runnerChocobo.compareTo(pikachu);
		pikachu.compareTo(runnerChocobo);
		assertEquals("runnerChocobo.compareTo(pikachu)", -1, runnerChocobo.compareTo(pikachu));
		assertEquals("pikachu.compareTo(runnerChocobo)", 1, pikachu.compareTo(runnerChocobo));
		
		// Jumper - Pikachu
		jumperChocobo.compareTo(pikachu);
		pikachu.compareTo(jumperChocobo);
		assertEquals("jumperChocobo.compareTo(pikachu)", -1, jumperChocobo.compareTo(pikachu));
		assertEquals("pikachu.compareTo(jumperChocobo)", 1, pikachu.compareTo(jumperChocobo));
	}
	
	@Test public void testCheckWinner() {
		//When the simulation starts, no one win
		assertFalse(RacingManager.hasWinner());
		
		((RunnerChocobo) characters[0]).setDistance(100.00);
		((JumperChocobo) characters[1]).setDistance(99.99);
		((Pikachu) characters[2]).setDistance(100.01);
		assertTrue(RacingManager.checkWinner(characters[0]));
		
		//There is a winner now
		assertTrue(RacingManager.hasWinner());
		
		//The remaining cases
		assertFalse(RacingManager.checkWinner(characters[1]));
		assertTrue(RacingManager.checkWinner(characters[2]));
	}
}

