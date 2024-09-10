package com.zandgall.csc420.common;

import java.io.PrintWriter;
import java.nio.charset.Charset;

public class Header {
	public static void print(String session, String assignment, String description) {
		String title = session + " - " + assignment;
		String signature = "zander gall - galla@csp.edu";

		// Find the longest string between title, description, and signature
		int maxlen = Math.max(Math.max(title.length(), description.length()), signature.length());

		String titlePad = "", descPad = "", sigPad = "";
		for(int i = 0; i < maxlen - title.length(); i+= 2)
			titlePad += " ";
		for(int i = 0; i < maxlen - description.length(); i+= 2)
			descPad += " ";
		for(int i = 0; i < maxlen - signature.length(); i+= 2)
			sigPad += " ";

		// Create a printwriter that allows unicode
		PrintWriter writer = new PrintWriter(System.out, true, Charset.defaultCharset());

		writer.print("◢█");
		for(int i = 0; i < maxlen; i++)
			writer.print("█");
		writer.println("█◣");
		writer.println("█ " + titlePad + title + titlePad.substring(0, (maxlen - title.length())/2) + " █");
		writer.print("█▀");
		for(int i = 0; i < maxlen; i++)
			writer.print("▀");
		writer.println("▀█");
		writer.println("█ " + descPad + description + descPad.substring(0, (maxlen - description.length())/2) + " █");
		writer.print("█▄");
		for(int i = 0; i < maxlen; i++)
			writer.print("▄");
		writer.println("▄█");
		writer.println("◥◣"+sigPad+signature+sigPad.substring(0, (maxlen - signature.length())/2) + "◢◤");
		writer.print(" ◥");
		for(int i = 0; i < maxlen; i++)
			writer.print("█");
		writer.println("◤ ");
		writer.println(AsciiEscape.ITALIC_WHITE + "I certify that this is my own work" + AsciiEscape.RESET);
	}
}
