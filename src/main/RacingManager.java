package main;

import java.util.Scanner;

import Provide.*;
import character.JumperChocobo;
import character.Pikachu;
import character.RunnerChocobo;
import simInterface.ICharacter;
import simInterface.IObstructable;
public class RacingManager {
	public static double GOAL;
	public static ICharacter characters[];
	public static boolean _hasWinner;
	
	
	public static double getGoal() {
		return GOAL;
	}
	
	public static ICharacter[] getCharacters() {
		return characters;
	}
	public static boolean hasWinner() {
		return _hasWinner;
	}
	
	public static void decreaseObstructedDuration() {
		for (int i=0; i<3; i++) {
			if (characters[i] instanceof IObstructable) {
				int d = Math.max(((IObstructable)characters[i]).getObstructedDuration()-1, 0);
				((IObstructable)characters[i]).setObstructedDuration(d);
			}
		}
	}
	
	public static void initializeCharacter() {
		GOAL = 100;
		characters = new ICharacter[3];
		characters[0] = new RunnerChocobo();
		characters[0].start();
		characters[1] = new JumperChocobo();
		characters[1].start();
		characters[2] = new Pikachu();
		characters[2].start();
	}
	
	public static void sortCharacter() {
		for (int i=0; i<3; i++) {
			for (int j=1; j<3; j++) {
				ICharacter t, a = characters[j-1], b = characters[j];
				if (a.compareTo(b) == 1) {
//					System.out.println(a);
//					System.out.println("is more than");
//					System.out.println(b);
					t = characters[j-1];
					characters[j-1] = characters[j];
					characters[j] = t;
				}
			}
		}
	}
	
	public static boolean checkWinner(ICharacter t) {
		if (Double.compare(t.getDistance(), GOAL) >= 0)
			return _hasWinner = true;
		return false;
	}
	
	public static void randomObstructedCharacters() {
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
		initializeCharacter();
		int i=0;
		while (!hasWinner()) {
			System.out.println(i++);
			sortCharacter();
			randomObstructedCharacters();
			for (ICharacter x: characters) {
				System.out.println(x);
				x.run();
				if (checkWinner(x)) {
					System.out.println("win");
					break;
				}
			}
			decreaseObstructedDuration();
//			if (i == 2) break;
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
