package com.zandgall.csc420.s2.a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
			Scanner s = new Scanner(new File("bandinfo.txt"));
		while(s.hasNextLine()) {
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

		printBands();
		populateByName();
		populateBySetTime();

		// Main menu
		while(true) {
			writer.println("Search by Band Name (1) or Set Time (2), or Examine List (3): ");
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

	public static void printBands() {
		writer.println("Bands - " + bands.size());
		for(Band b : bands)
			writer.println("▸ " + b);
	}

	public static void searchBandName() {
		writer.println("Enter name: ");

		Scanner input = new Scanner(System.in);
		String name = input.nextLine();

		int lower = 0, upper = bandsByName.size() - 1;
		while(lower != upper) {
			int mid = (lower + upper) / 2;
			Band midBand = bandsByName.get(mid);
			if(midBand.getName().equals(name)) {
				writer.println("▸ " + midBand);
				return;
			}
			else if(midBand.getName().compareTo(name) < 0)
				lower = (int)Math.ceil((lower + upper) / 2.0);
			else
				upper = mid;
		}
		writer.println("Could not find \"" + name + "\"");
	}

	public static void searchSetTime() {
		writer.println("Enter set time: (minute.second format)");

		Scanner input = new Scanner(System.in);
		float time = input.nextFloat();

		int lower = 0, upper = bandsByName.size() - 1;
		while(lower != upper) {
			int mid = (lower + upper) / 2;
			Band midBand = bandsByName.get(mid);
			if(midBand.getSetTime() == time) {
				lower = mid;
				upper = mid;
			}
			else if(midBand.getSetTime() < time)
				lower = (int)Math.ceil((lower + upper) / 2.0);
			else
				upper = mid;
		}
		writer.println("Closest Set Time is \"" + bandsBySetTime.get(lower) + "\"");
	}

	/* Populate the bandsByName array list */
	public static void populateByName() {
		bandsByName.addAll(bands);
		sortByName(bandsByName);

		writer.println("Bands by Name - " + bandsByName.size());
		for(Band b : bandsByName)
			writer.println("▸ " + b);
	}

	/* Populate the bandsBySetTime array list */
	public static void populateBySetTime() {
		bandsBySetTime.addAll(bands);
		sortBySetTime(bandsBySetTime);

		writer.println("Bands by Set Time - " + bandsBySetTime.size());
		for(Band b : bandsBySetTime)
			writer.println("▸ " + b);

	}

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
