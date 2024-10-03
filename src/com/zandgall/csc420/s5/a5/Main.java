package com.zandgall.csc420.s5.a5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

import com.zandgall.csc420.common.*;

import com.zandgall.csc420.s5.a5.tree.AVLTree;

public class Main {

	public static final int TIME_MULT = 40;

	public static void main(String[] args) throws FileNotFoundException {
		/* !important! */
		// If you see weird characters and no color - especially on windows - uncomment the following line
		// AnsiEscape.RevokeEscapes();

		Header.print("Session 5", "Assignment 5", "Time Slices with AVL Tree");
		AVLTree<ProcessInfo> tree = new AVLTree<>();

		Scanner s = new Scanner(new File("processListMaster.txt"));
		while(s.hasNextLine()) {
			String content[] = s.nextLine().split("\\|");
			if(content.length < 3) // only parse lines with enough parameters for a process info object
				continue;
			ProcessInfo processInfo = new ProcessInfo(content[0], Integer.parseInt(content[1]), Integer.parseInt(content[2]), Integer.parseInt(content[3])*TIME_MULT);
			System.out.println("Adding " + processInfo);
			tree.add(processInfo);
		}
		s.close();

		System.out.println("In order:");
		for(ProcessInfo p : tree)
			System.out.println(p);

		ArrayDeque<ProcessInfo> completed = new ArrayDeque<>();
		while(!tree.isEmpty()) {
			for(ProcessInfo p : tree)
				if(p.executeProcess(10 - p.getPriority()))
					completed.add(p);
			while(!completed.isEmpty()) {
				ProcessInfo completedProcessInfo = completed.removeFirst();
				System.out.println(completedProcessInfo.displayCompletedInfo());
				tree.remove(completedProcessInfo);
			}
		}
	}
}
