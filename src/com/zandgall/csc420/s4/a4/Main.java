package com.zandgall.csc420.s4.a4;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Random;

import com.zandgall.csc420.common.AnsiEscape;
import com.zandgall.csc420.common.Header;

public class Main {
	public static void main(String[] args) {
		/* !important! */
		// If you see weird characters and no color - especially on windows - uncomment the following line
		// AnsiEscape.RevokeEscapes();

		Header.print("Session 4", "Assignment 4", "Random List with Generics");

		// Create a printwriter that allows unicode
		PrintWriter writer = new PrintWriter(System.out, true, Charset.defaultCharset());

		writer.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.BRIGHT_RED));
		writer.println("New RandomList with Strings");
		
		// Create random string list and randomly populate
		RandomList<String> stringlist = new RandomList<>();
		stringlist.randomAdd("One");
		stringlist.randomAdd("Two");
		stringlist.randomAdd("Three");
		stringlist.randomAdd("Four");
		stringlist.randomAdd("Five");
		stringlist.randomAdd("Six");
		stringlist.randomAdd("Seven");
		stringlist.randomAdd("Eight");
		stringlist.randomAdd("Nine");
		stringlist.randomAdd("Ten");

		// Print list forwards and backwards
		// writer.print(AnsiEscape.rgb6(AnsiEscape.NORMAL, AnsiEscape.FOREGROUND, 5, 0, 0));
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.NORMAL, AnsiEscape.RED));
		writer.flush();
		stringlist.printList();
		stringlist.printReverseList();

		// Remove an element, print the list
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.MAGENTA));
		writer.println("Removed: " + stringlist.randomRemove());
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.NORMAL, AnsiEscape.RED));
		writer.flush();
		stringlist.printList();

		// Add an element, print the list
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.MAGENTA));
		stringlist.randomAdd("Eleven");
		writer.println("Added: Eleven");
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.NORMAL, AnsiEscape.RED));
		writer.flush();
		stringlist.printList();

		// Get random and specific elements
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.MAGENTA));
		writer.println("Random get: " + stringlist.randomGet());
		writer.println("Specific get: " + stringlist.get(0));
		writer.println("Specific get: " + stringlist.get(9));
		writer.println("Specific get: " + stringlist.get(10));
		writer.println("Specific get: " + stringlist.get(8));
		writer.println("Specific get: " + stringlist.get(-1));

		writer.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.BRIGHT_GREEN));
		writer.println("New RandomList with Strings");
		
		// Create random string list and randomly populate
		RandomList<Integer> intlist = new RandomList<>();
		for(int i = 0; i < 10; i++)
			intlist.randomAdd(i);

		// Print list forwards and backwards
		// writer.print(AnsiEscape.rgb6(AnsiEscape.NORMAL, AnsiEscape.FOREGROUND, 5, 0, 0));
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.NORMAL, AnsiEscape.GREEN));
		writer.flush();
		intlist.printList();
		intlist.printReverseList();

		// Remove an element, print the list
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.CYAN));
		writer.println("Removed: " + intlist.randomRemove());
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.NORMAL, AnsiEscape.GREEN));
		writer.flush();
		intlist.printList();

		// Add an element, print the list
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.CYAN));
		intlist.randomAdd(10);
		writer.println("Added: 10");
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.NORMAL, AnsiEscape.GREEN));
		writer.flush();
		intlist.printList();

		// Get random and specific elements
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.CYAN));
		writer.println("Random get: " + intlist.randomGet());
		writer.println("Specific get: " + intlist.get(0));
		writer.println("Specific get: " + intlist.get(9));
		writer.println("Specific get: " + intlist.get(10));
		writer.println("Specific get: " + intlist.get(8));
		writer.println("Specific get: " + intlist.get(-1));

	}
}
