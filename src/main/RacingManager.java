package main;

import java.util.Scanner;

import org.omg.CORBA.INITIALIZE;

import Provide.*;
import character.ICharacter;
import character.IObstructable;
import character.JumperChocobo;
import character.Pikachu;
import character.RunnerChocobo;
public class RacingManager {
	public double GOAL;
	public ICharacter characters[];
	public boolean hasWinner;
	
	
	public double getGoal() {
		return GOAL;
	}
	
	public ICharacter[] getCharacters() {
		return characters;
	}
	public boolean HasWinner() {
		return hasWinner;
	}
	
	public void initializeCharacter() {
		characters = new ICharacter[3];
		characters[0] = new RunnerChocobo();
		characters[0].start();
		characters[1] = new JumperChocobo();
		characters[1].start();
		characters[2] = new Pikachu();
		characters[2].start();
	}
	
	public void sortCharacter() {
		for (int i=0; i<characters.length; i++) {
			for (int j=i+1; j<characters.length; j++) {
				ICharacter t, a = characters[i], b = characters[j];
				if (a.compareTo(b) == 1)
					{t = a; a = b; b =t;}
			}
		}
	}
	
	public boolean checkWinner(ICharacter t) {
		if (t.getDistance() >= GOAL)
			return hasWinner = true;
		return false;
	}
	
	public void randomObstructedCharacters() {
		int res = Provide.Library.randomChance();
		boolean oc = false, op = false;
		if (res <= 20) oc = true;
		else if (res <= 40) op = true;
		else if (res <= 60) oc = op = true;
		for (int i=0; i<3; i++) {
			if (characters[i] instanceof JumperChocobo && oc == true)
				((IObstructable)characters[i]).obstructed();
			else if (characters[i] instanceof Pikachu && op == true)
				((IObstructable)characters[i]).obstructed();
		}
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to CHOCOBO RACING.\nThe goal at distance 100 m.\n");
		System.out.println("Press 'any key' to START.");
		kb.nextLine();
		kb.close();
		System.out.printf("Initialized Characters\n");
		RacingManager rm = new RacingManager();
		rm.initializeCharacter();
		while (!rm.HasWinner()) {
			
		}
	}

	public static void printRound(int round) {
		System.out.printf("\nRound %d\n", round);
	}

	public static void printCharacterDistance(String characterName, double characterDistance) {
		System.out.printf("- %s distance : %.2f\n", characterName, characterDistance);
	}

	public static void printJumperChocoboPreparingToJump(double jumperChocoboDistance) {
		System.out.printf("- JumperChocobo distance : %.2f and preparing to jump\n", jumperChocoboDistance);
	}

	public static void printJumperChocoboObstructed() {
		System.out.printf("+ JumperChocobo has obstructed > speed down for 2 seconds\n");
	}

	public static void printPikachuObstructed() {
		System.out.printf("+ Pikachu has obstructed > stop running for 1 second\n");
	}

	public static void printTheWinner(String characterName) {
		System.out.printf("\n%s is the WINNER!", characterName);
	}

}
