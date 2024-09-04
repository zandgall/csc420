package com.zandgall.csc420.s1.a1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import com.zandgall.csc420.common.AsciiEscape;
import com.zandgall.csc420.common.Header;

public class Main {
	public static void main(String[] args) throws FileNotFoundException { // Deferring errors for readability
		Header.print("Session 1", "Assignment 1", "Random Name Generator");

		/* !important! */
		// If you see weird characters - especially on windows - uncomment the following line
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

		System.out.println();
		System.out.println(AsciiEscape.BLUE);
		System.out.println(" ▁▂▂▃▃▃▄▄▄▄▅▅▅▅▅▆▆▆▆▆▆▇▇▇▇▇▇▇████████████████▇▇▇▇▇▇▇▆▆▆▆▆▆▅▅▅▅▅▄▄▄▄▃▃▃▂▂▁ ");
		System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀" + AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		System.out.println(" ◆ Names generated                                                        ");
		System.out.println(" ► They are printed in first-name order as a result of the data structure ");
		System.out.println(" No extra sorting is needed after generating and before printing in order ");
		System.out.println(" to achieve this                                                          ");
		System.out.println(AsciiEscape.RESET + AsciiEscape.BLUE + "▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
		System.out.println(AsciiEscape.RESET);

		for(Pair p : names)
			System.out.printf("▹ %s %s%n", firstList.get(p.a), lastList.get(p.b));

		System.out.println();
		System.out.println(AsciiEscape.BLUE);
		System.out.println(" ▁▂▂▃▃▃▄▄▄▄▅▅▅▅▅▆▆▆▆▆▆▇▇▇▇▇▇▇████████████████▇▇▇▇▇▇▇▆▆▆▆▆▆▅▅▅▅▅▄▄▄▄▃▃▃▂▂▁ ");
		System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀" + AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		System.out.println(" ◆ Ordered By Last Name                                                   ");
		System.out.println(" ► To do this, we will for-loop from 0 to the number of last names that   ");
		System.out.println(" exist. And for each last name index, we loop through and print any names ");
		System.out.println(" that exist with that last name index                                     ");
		System.out.println(" ▰ Here we require a nested loop, which sacrifices last-name sorting      ");
		System.out.println(" efficiency for incredibly quick and efficient first-name ordering and    ");
		System.out.println(" grouping                                                                 ");
		System.out.println(" ▰ If we wanted to sort by last name like we do for first name, we would  ");
		System.out.println(" move 'names' into it's own list (similar to firstList and lastList), and ");
		System.out.println(" sort it according to Point.b as opposed to Point.a, saving the order for ");
		System.out.println(" future use                                                               ");
		System.out.println(AsciiEscape.RESET + AsciiEscape.BLUE + "▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
		System.out.println(AsciiEscape.RESET);

		for(int i = 0; i < lastList.size(); i++)
			for(Pair p : names)
				if(p.b == i)
					System.out.printf("▹ %s %s%n", firstList.get(p.a), lastList.get(p.b));

		System.out.println();
		System.out.println(AsciiEscape.BLUE);
		System.out.println(" ▁▂▂▃▃▃▄▄▄▄▅▅▅▅▅▆▆▆▆▆▆▇▇▇▇▇▇▇████████████████▇▇▇▇▇▇▇▆▆▆▆▆▆▅▅▅▅▅▄▄▄▄▃▃▃▂▂▁ ");
		System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀" + AsciiEscape.BLUE_BACKGROUND + AsciiEscape.BOLD_YELLOW);
		System.out.println(" ◆ Grouped by First Name ► As our list is pre-sorted by first name, we    ");
		System.out.println(" just print the list, but only printing the first name if it changes      ");
		System.out.println(AsciiEscape.RESET + AsciiEscape.BLUE + "▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
		System.out.println(AsciiEscape.RESET);

		int lastFirst = -1;
		for(Pair p : names) {
			if(p.a != lastFirst) {
				System.out.printf("▹ %s%n", firstList.get(p.a));
				lastFirst = p.a;
			}
			System.out.printf("\t◦ %s%n", lastList.get(p.b));
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
