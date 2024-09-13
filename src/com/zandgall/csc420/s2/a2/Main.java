package com.zandgall.csc420.s2.a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import com.zandgall.csc420.common.AsciiEscape;
import com.zandgall.csc420.common.Header;

public class Main {

	// A place to keep arrays of bands which we will structure/sort ourselves
	private static final ArrayList<Band>
		bands = new ArrayList<>(), 
		bandsByName = new ArrayList<>(), 
		bandsBySetTime = new ArrayList<>();
	private static PrintWriter writer;

	public static void main(String[] args) throws FileNotFoundException {
		/* !important! */
		// If you see weird characters and no color - especially on windows - uncomment the following line
		// AsciiEscape.RevokeEscapes();

		Header.print("Session 2", "Assignment 2", "Band Coordinator");

		// Create a printwriter that allows unicode
		writer = new PrintWriter(System.out, true, Charset.defaultCharset());

		// Read file
		Scanner s = new Scanner(new File("bandinfo.txt"));
		while(s.hasNextLine()) {
			// Read every line, split it by "|" character
			// Put portion before "|" as band name, and
			// portion after "|" as a float that needs
			// to be parsed.

			String line = s.nextLine();
			if(line.isBlank())
				continue;

			String[] params = line.split("\\|");
			if(params.length != 2)
				continue;

			try {
				bands.add(new Band(params[0], Float.parseFloat(params[1])));
			} catch(NumberFormatException e) {
				System.err.println("Could not parse band \""+line+"\"");
			}
		}
		s.close();

		// Sort bands into bandsByName, and bandsBySetTime
		populateByName();
		populateBySetTime();

		// Main menu
		while(true) {
			writer.println();
			writer.println(AsciiEscape.ITALIC_GREY + "Search by Band Name (1) or Set Time (2), or Examine List (3): " + AsciiEscape.RESET);
			Scanner input = new Scanner(System.in);
			int opt = input.nextInt();
			if(opt == 1)
				searchBandName();
			else if(opt == 2)
				searchSetTime();
			else if(opt == 3)
				printBands();
		}
	}

	// Print bands
	public static void printBands() {
		writer.println(AsciiEscape.UNDERLINE_BLUE + "Bands - " + bands.size() + AsciiEscape.RESET + AsciiEscape.BLUE);
		for(Band b : bands)
			writer.println("▸ " + b);
		writer.println(AsciiEscape.RESET);
		writer.println(AsciiEscape.UNDERLINE_BLUE + "Bands by Set Time - " + bandsBySetTime.size() + AsciiEscape.RESET + AsciiEscape.BLUE);
		for(Band b : bandsBySetTime)
			writer.println("▸ " + b);
		writer.println(AsciiEscape.RESET);
		writer.println(AsciiEscape.UNDERLINE_BLUE + "Bands by Name - " + bandsByName.size() + AsciiEscape.RESET + AsciiEscape.BLUE);
		for(Band b : bandsByName)
			writer.println("▸ " + b);
		writer.println(AsciiEscape.RESET);
	}

	// Binary search - O(log n)F
	// While this is normall done via 
	public static void searchBandName() {
		writer.println(AsciiEscape.ITALIC_GREEN + "Enter name: " + AsciiEscape.RESET);

		Scanner input = new Scanner(System.in);
		String name = input.nextLine();

		Band midBand;
		int lower = 0, upper = bandsByName.size() - 1, mid;
		while(lower != upper) {
			mid = (lower + upper) / 2;
			midBand = bandsByName.get(mid);
			if(midBand.getName().equals(name)) {
				writer.println(AsciiEscape.BOLD_GREEN + "▸ " + midBand + AsciiEscape.RESET);
				return;
			}
			else if(midBand.getName().compareTo(name) < 0)
				lower = mid + 1;
			else
				upper = mid - 1;
		}
		writer.println(AsciiEscape.ITALIC_RED + "Could not find \"" + name + "\"" + AsciiEscape.RESET);
	}

	// Linear search - O(n)
	public static void searchSetTime() {
		writer.println(AsciiEscape.ITALIC_MAGENTA + "Enter set time: (minute.second format)" + AsciiEscape.RESET);

		Scanner input = new Scanner(System.in);
		float time = input.nextFloat(), mindist = Float.POSITIVE_INFINITY;
		Band result = bandsBySetTime.get(0);
		for(Band b : bandsBySetTime) {
			float dist = Math.abs(time - b.getSetTime());
			if(dist < mindist) {
				mindist = dist;
				result = b;
			}
		}
		writer.println(AsciiEscape.BOLD_MAGENTA + "Closest Set Time is \"" + result + "\"" + AsciiEscape.RESET);
	}

	/* Populate the bandsByName array list */
	public static void populateByName() {
		bandsByName.addAll(bands);
		sortByName(bandsByName);

	}

	/* Populate the bandsBySetTime array list */
	public static void populateBySetTime() {
		bandsBySetTime.addAll(bands);
		sortBySetTime(bandsBySetTime);
	}

	// SEE README.md
	private static void sortByName(ArrayList<Band> bands) {
		ArrayList<Band> lowerList = new ArrayList<>(), upperList = new ArrayList<>();
		
		// Remove a band from the list and use it as reference to sort the rest of the list
		Band middle = bands.removeFirst();
		for (Band b : bands)
			if(b.getName().compareTo(middle.getName()) < 0)
				lowerList.add(b);
			else
				upperList.add(b);

		if(lowerList.size() > 1)
			sortByName(lowerList);
		if(upperList.size() > 1)
			sortByName(upperList);

		bands.clear();
		bands.addAll(lowerList);
		bands.add(middle);
		bands.addAll(upperList);
	}

	// SEE README.md
	private static void sortBySetTime(ArrayList<Band> bands) {
		ArrayList<Band> lowerList = new ArrayList<>(), upperList = new ArrayList<>();
		
		// Remove a band from the list and use it as reference to sort the rest of the list
		Band middle = bands.removeFirst();
		for (Band b : bands)
			if(b.getSetTime() < middle.getSetTime())
				lowerList.add(b);
			else
				upperList.add(b);

		if(lowerList.size() > 1)
			sortBySetTime(lowerList);
		if(upperList.size() > 1)
			sortBySetTime(upperList);
		bands.clear();
		bands.addAll(lowerList);
		bands.add(middle);
		bands.addAll(upperList);
	}
}
