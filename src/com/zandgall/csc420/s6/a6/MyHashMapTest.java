package com.zandgall.csc420.s6.a6;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

import com.zandgall.csc420.common.AnsiEscape;

public class MyHashMapTest {
	static MyHashMap<Integer, String> map;
	static PrintWriter out;

	public static void main(String[] args) {
		map = new MyHashMap<>();
		out = new PrintWriter(System.out, false, Charset.defaultCharset());
		map.put(0, "zero");
		map.put(1, "one");
		map.put(2, "two");
		Scanner in = new Scanner(System.in);
		while(true) {
			map.testPrint(out);
			out.println(AnsiEscape.get(AnsiEscape.ITALIC, AnsiEscape.GRAY) + "(p)ut, (g)et, (r)emove, (q)uit, (e)xpand" + AnsiEscape.reset());
			out.print(AnsiEscape.get(AnsiEscape.ITALIC, AnsiEscape.GRAY) + "pgrq > " + AnsiEscape.reset());
			out.flush();
			String input = in.nextLine().toLowerCase().strip();
			switch(input) {
			case "p":
				out.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.RED));
				out.print("key > ");
				out.flush();
				int key = Integer.parseInt(in.nextLine());
				out.print("value > ");
				out.flush();
				out.print(AnsiEscape.reset());
				map.put(key, in.nextLine());
				break;
			case "g":
				out.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.RED));
				out.print("key > ");
				out.flush();
				int g = Integer.parseInt(in.nextLine());
				out.println(g + " -> " + map.get(g));
				out.flush();
				out.print(AnsiEscape.reset());
				break;
			case "r":
				out.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.RED));
				out.print("key > ");
				out.flush();
				int r = Integer.parseInt(in.nextLine());
				out.println("Removed " + r + " -> " + map.remove(r));
				out.flush();
				out.print(AnsiEscape.reset());
				break;
			case "e":
				out.print(AnsiEscape.get(AnsiEscape.BOLD, AnsiEscape.RED));
				System.out.println("Expanding...");
				out.flush();
				map.expand();
				out.print(AnsiEscape.reset());
				break;
			case "q":
				in.close();
				return;
			}
			out.flush();
		}
	}
}
