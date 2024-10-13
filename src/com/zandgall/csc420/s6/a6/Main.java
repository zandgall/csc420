package com.zandgall.csc420.s6.a6;

import java.io.PrintWriter;
import java.nio.charset.Charset;

import com.zandgall.csc420.common.Header;

public class Main {
	public static void main(String[] args) {
		/* !important! */
		// If you see weird characters and no color - especially on windows - uncomment the following line
		// AnsiEscape.RevokeEscapes();
		
		Header.print("Session 6", "Assignment 6", "Hashing");

		CarInfo car = new CarInfo("Zander Gall", "RAN150", CarInfo.State.MN, CarInfo.Make.Honda, CarInfo.Type.Sedan);
		System.out.println(car);

		PrintWriter out = new PrintWriter(System.out, false, Charset.defaultCharset());

		MyHashMap<CarInfo, Integer> map = new MyHashMap<>();
		for(int i = 0, n = (int)(Math.random()*90) + 10; i < n; i++) {
			map.put(new CarInfo(randomName(), randomLicense(), pickRandom(CarInfo.State.values()), pickRandom(CarInfo.Make.values()), pickRandom(CarInfo.Type.values())), i);
		}

		map.testPrint(out);
	}

	public static String randomName() {
		String[] first = {"Alice", "Bob", "Chanel", "Doug", "Edward", "Fred", "Grace", "Hugh", "Ian", "James", "Kai", "Liam", "Minnie", "Noah", "Oprah", "Pearl", "Queenie", "Rai", "Sara", "Tyler", "Unique", "Violetta", "Wyatt", "Xander", "Yianna", "Zach"};
		String[] last = {"Alban", "Briguette", "Coldstone", "Duffler", "Eyani", "Guestevve", "Hughman", "Ivan", "Jefferson", "Kaitlyn", "Larson", "McDonnel", "Nugent", "Offerman", "Peterson", "Quint", "Regali", "Sorenson", "Tyson", "Uve", "Vick", "Wyster", "Xianna", "Yoke", "Zolph"};
		return pickRandom(first) + " " + pickRandom(last);
	}

	public static String randomLicense() {
		String[] l = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		return pickRandom(l) + pickRandom(l) + pickRandom(l) + pickRandom(l) + pickRandom(l) + pickRandom(l);
	}

	public static <T> T pickRandom(T[] from) {
		int i = (int)(Math.random() * from.length);
		return from[Math.max(Math.min(i, from.length - 1), 0)];
	}
}
