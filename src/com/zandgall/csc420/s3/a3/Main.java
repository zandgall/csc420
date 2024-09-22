package com.zandgall.csc420.s3.a3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

import com.zandgall.csc420.common.AnsiEscape;
import com.zandgall.csc420.common.Header;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		/* !important! */
		// If you see weird characters and no color - especially on windows - uncomment the following line
		// AsciiEscape.RevokeEscapes();

		Header.print("Session 3", "Assignment 3", "O(N) Searching on String");

		// Create a printwriter that allows unicode
		PrintWriter writer = new PrintWriter(System.out, false, Charset.defaultCharset());

		// Create 27 buckets
		ArrayList<ArrayDeque<String>> buckets = new ArrayList<>();
		for(int i = 0; i < 27; i++)
			buckets.add(new ArrayDeque<>());

		// Read all words and put in first bucket
		Scanner scanner = new Scanner(new File("words.txt"));
		while(scanner.hasNextLine()) {
			buckets.get(0).add(scanner.nextLine());
		}

		for(int i = 19; i >= 0; i--) {
			for(int b = 0; b < buckets.size(); b++)
				for(String s : buckets.get(b)) {
					int radix;
					if(s.length() <= i)
						radix = 0;
					else if(s.charAt(i) == ' ') // ' '=26
						radix = 26;
					else // 'a'-'a'=0, 'b'-'a'=1 ... 'z'-'a'=25
						radix = s.toLowerCase().charAt(i) - 'a';
					radix = clamp(radix, 0, buckets.size()-1); // clamp to 0..num buckets
			
					// Remove from current bucket and add to radix bucket
					buckets.get(b).remove(s);
					buckets.get(radix).add(s);
				}

			// Print sorting progress after each radix
			writer.print(colorFromRadix(i));
			writer.printf("After %d radix:%s%n", i, AnsiEscape.reset());
			for(int b = 0; b < buckets.size(); b++) {
				if(buckets.get(b).isEmpty()) // Dont print empty buckets
					continue;
				writer.printf("\t%sRadix %2d%s: ", colorFromRadix(b), b, AnsiEscape.reset());
				for(String s : buckets.get(b))
					if(s.length() > i)
						if(s.charAt(i) == ' ')
							writer.printf("\"%s%s%c%s%s\" ", s.substring(0, i), colorFromRadix(b, AnsiEscape.BACKGROUND), s.charAt(i), AnsiEscape.reset(), s.substring(i+1));
						else
							writer.printf("\"%s%s%c%s%s\" ", s.substring(0, i), colorFromRadix(b), s.charAt(i), AnsiEscape.reset(), s.substring(i+1));
					else
						writer.printf("\"%s\" ", s);
				writer.println();
			}
			writer.flush();
		}
		
		// Print the full sorted string list
		System.out.println();
		System.out.println("Output:");
		for(ArrayDeque<String> bucket : buckets)
			for(String s : bucket)
				System.out.println(s);

		scanner.close();
	}

	// Clamp a value between the range (min, max) (inclusive)
	private static int clamp(int input, int min, int max) {
		return Math.min(Math.max(input, min), max);
	}

	// Create a Ansi RGB6 color with a radix, on a gradient
	// Not necessary to the actual function of the assignment, just color codes radices
	private static String colorFromRadix(int i) { return colorFromRadix(i, AnsiEscape.FOREGROUND); }
	
	private static String colorFromRadix(int i, String foregroundBackground) {
		return AnsiEscape.rgb6(AnsiEscape.NORMAL, foregroundBackground, 
				clamp((10-i)/2, 0, 5),
				clamp(5-Math.abs(10-i)/2, 0, 5),
				clamp((i-10)/2, 0, 5));
	}
}
