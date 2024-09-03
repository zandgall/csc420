package com.zandgall.csc420.common;

import java.io.PrintWriter;
import java.nio.charset.Charset;

public class Header {
	public static void print(String session, String assignment, String description) {
		String title = session + " - " + assignment;
		String signature = "zander gall - galla@csp.edu";
		int maxlen = Math.max(Math.max(title.length(), description.length()), signature.length());

		PrintWriter writer = new PrintWriter(System.out, true, Charset.defaultCharset());

		String titlePad = "", descPad = "";
		for(int i = 0; i < maxlen - title.length(); i+= 2)
			titlePad += " ";
		for(int i = 0; i < maxlen - description.length(); i+= 2)
			descPad += " ";

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
	}
}
