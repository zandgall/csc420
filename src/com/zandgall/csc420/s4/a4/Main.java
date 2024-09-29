package com.zandgall.csc420.s4.a4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

import com.zandgall.csc420.common.AnsiEscape;
import com.zandgall.csc420.common.Header;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
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

		testList(stringlist, "Eleven", writer, AnsiEscape.RED, AnsiEscape.MAGENTA);

		writer.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.BRIGHT_GREEN));
		writer.println("New RandomList with Integers");
		
		// Create random string list and randomly populate
		RandomList<Integer> intlist = new RandomList<>();
		for(int i = 0; i < 10; i++)
			intlist.randomAdd(i);

		testList(intlist, 10, writer, AnsiEscape.GREEN, AnsiEscape.CYAN);

		// Random lists using given file
		RandomList<String> file1 = new RandomList<>();
		RandomList<Integer> file2 = new RandomList<>(), file3 = new RandomList<>();
		RandomList<Float> file4 = new RandomList<>();
		Scanner fScanner = new Scanner(new File("ProcessList1.txt"));
		while(fScanner.hasNextLine()) {
			String content[] = fScanner.nextLine().split("\\|");
			if(content.length < 3) // Only parse lines with enough parameters for each list
				continue;
			file1.randomAdd(content[0]);
			file2.randomAdd(Integer.parseInt(content[1]));
			file3.randomAdd(Integer.parseInt(content[2]));
			file4.randomAdd(Float.parseFloat(content[3]));
		}
		fScanner.close();

		writer.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.BRIGHT_GREEN));
		writer.println("File List 1");
		testList(file1, "go", writer, AnsiEscape.YELLOW, AnsiEscape.RED);
		writer.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.BRIGHT_GREEN));
		writer.println("File List 2");
		testList(file2, 2, writer, AnsiEscape.YELLOW, AnsiEscape.RED);
		writer.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.BRIGHT_GREEN));
		writer.println("File List 3");
		testList(file3, 3, writer, AnsiEscape.YELLOW, AnsiEscape.RED);
		writer.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.BRIGHT_GREEN));
		writer.println("File List 4");
		testList(file4, 4.f, writer, AnsiEscape.YELLOW, AnsiEscape.RED);
	}

	private static <E> void testList(RandomList<E> list, E addElement, PrintWriter writer, String color, String strongColor) {
		// Print list forwards and backwards
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.NORMAL, color));
		writer.flush();
		list.printList();
		list.printReverseList();

		// Remove an element, print the list
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.BOLD, strongColor));
		writer.println("Removed: " + list.randomRemove().toString());
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.NORMAL, color));
		writer.flush();
		list.printList();

		// Add an element, print the list
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.BOLD, strongColor));
		list.randomAdd(addElement);
		writer.println("Added: " + addElement.toString());
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.NORMAL, color));
		writer.flush();
		list.printList();

		// Get random and specific elements
		writer.print(AnsiEscape.reset() + AnsiEscape.get(AnsiEscape.BOLD, strongColor));
		writer.println("Random get: " + list.randomGet());
		writer.println("Specific get: " + list.get(0));
		writer.println("Specific get: " + list.get(list.size()-1));
		writer.println("Specific get: " + list.get(list.size()));
		writer.println("Specific get: " + list.get(list.size()-2));
		writer.println("Specific get: " + list.get(-1));
	}
}
