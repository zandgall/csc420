package com.zandgall.csc420.s1.a1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import com.zandgall.csc420.common.AsciiEscape;
import com.zandgall.csc420.common.Header;

public class Main {
	public static void main(String[] args) throws FileNotFoundException { // Deferring errors for readability
		Header.print("Session 1", "Assignment 1", "Random Name Generator");

		// Create a printwriter that allows unicode
		PrintWriter writer = new PrintWriter(System.out, true, Charset.defaultCharset());

		/* !important! */
		// If you see weird characters and no color - especially on windows - uncomment the following line
		// AsciiEscape.RevokeEscapes();

		// Use sets to avoid duplicates
		TreeSet<String> first = new TreeSet<>(), last = new TreeSet<>();
		// A list of int-int pairs, marking first name last name indices, to avoid string collision
		TreeSet<Pair> names = new TreeSet<>();

		// Read both 'firstNames.txt' and 'lastNames.txt', parsing lines into first and last
		Scanner reader;

		reader = new Scanner(new File("firstNames.txt"));
		while(reader.hasNextLine())
			first.add(reader.nextLine());
		reader.close();

		reader = new Scanner(new File("lastNames.txt"));
		while(reader.hasNextLine())
			last.add(reader.nextLine());
		reader.close();

		// Generate names until there are 20
		Random r = new Random();
		while(names.size() < 20) {
			names.add(new Pair(r.nextInt(first.size()), r.nextInt(last.size())));
		}

		// Retrieve an index-able list version of either set
		ArrayList<String> firstList = new ArrayList<>(first), lastList = new ArrayList<>(last);

		// Print all names

		writer.println();
		
		writer.println(AsciiEscape.BLUE);
		writer.println(" ▁▂▂▃▃▃▄▄▄▄▅▅▅▅▅▆▆▆▆▆▆▇▇▇▇▇▇▇████████████████▇▇▇▇▇▇▇▆▆▆▆▆▆▅▅▅▅▅▄▄▄▄▃▃▃▂▂▁ ");
		writer.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" ◆ Names generated                                                        " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" ► They are printed in first-name order as a result of the data structure " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" No extra sorting is needed after generating and before printing in order " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" to achieve this                                                          " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE);
		writer.println("▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
		writer.println(AsciiEscape.RESET);

		for(Pair p : names)
			writer.printf("▹ %s %s%n", firstList.get(p.a), lastList.get(p.b));

		writer.println();
		writer.println(AsciiEscape.BLUE);
		writer.println(" ▁▂▂▃▃▃▄▄▄▄▅▅▅▅▅▆▆▆▆▆▆▇▇▇▇▇▇▇████████████████▇▇▇▇▇▇▇▆▆▆▆▆▆▅▅▅▅▅▄▄▄▄▃▃▃▂▂▁ ");
		writer.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" ◆ Ordered By Last Name                                                   " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" ► To do this, we will for-loop from 0 to the number of last names that   " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" exist. And for each last name index, we loop through and print any names " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" that exist with that last name index                                     " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" ▰ Here we require a nested loop, which sacrifices last-name sorting      " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" efficiency for incredibly quick and efficient first-name ordering and    " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" grouping                                                                 " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" ▰ If we wanted to sort by last name like we do for first name, we would  " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" move 'names' into it's own list (similar to firstList and lastList), and " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" sort it according to Point.b as opposed to Point.a, saving the order for " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" future use                                                               " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE);
		writer.println("▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
		writer.println(AsciiEscape.RESET);

		for(int i = 0; i < lastList.size(); i++)
			for(Pair p : names)
				if(p.b == i)
					writer.printf("▹ %s %s%n", firstList.get(p.a), lastList.get(p.b));

		writer.println();
		writer.println(AsciiEscape.BLUE);
		writer.println(" ▁▂▂▃▃▃▄▄▄▄▅▅▅▅▅▆▆▆▆▆▆▇▇▇▇▇▇▇████████████████▇▇▇▇▇▇▇▆▆▆▆▆▆▅▅▅▅▅▄▄▄▄▃▃▃▂▂▁ ");
		writer.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" ◆ Grouped by First Name ► As our list is pre-sorted by first name, we    " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		writer.println(" just print the list, but only printing the first name if it changes      " + AsciiEscape.RESET);
		writer.print(AsciiEscape.BLUE);
		writer.println("▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
		writer.println(AsciiEscape.RESET);

		int lastFirst = -1;
		for(Pair p : names) {
			if(p.a != lastFirst) {
				writer.printf("▹ %s%n", firstList.get(p.a));
				lastFirst = p.a;
			}
			writer.printf("\t◦ %s%n", lastList.get(p.b));
		}
	}

	// Simple storage class, no need for mutability constraints or worrying about publicity in this simple example
	private static class Pair implements Comparable<Pair> {
		public final int a, b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(Pair other) {
			return other.a == a ? b - other.b : a - other.a;
		}
	}
}
