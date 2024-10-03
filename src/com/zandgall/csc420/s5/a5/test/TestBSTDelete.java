package com.zandgall.csc420.s5.a5.test;

import com.zandgall.csc420.s5.a5.tree.BST;

public class TestBSTDelete {
	public static void main(String[] args) {
		BST<String> tree = new BST<>();
		tree.insert("George");
		tree.insert("Michael");
		tree.insert("Tom");
		tree.insert("Adam");
		tree.insert("Jones");
		tree.insert("Peter");
		tree.insert("Daniel");
		printTree(tree);
		System.out.println("\nAfter delete George:");
		tree.delete("George");
		printTree(tree);
		System.out.println("\nAfter delete Adam:");
		tree.delete("Adam");
		printTree(tree);
		System.out.println("\nAfter delete Michael:");
		tree.delete("Michael");
		printTree(tree);
	}

	public static void printTree(BST tree) {
		// Traverse tree
		System.out.print("Inorder (sorted): ");
		tree.inorder();
		System.out.print("\nPostorder: ");
		tree.postorder();
		System.out.print("\nPreorder: ");
		tree.preorder();
		System.out.print("\nThe number of nodes is " + tree.getSize());
		System.out.println();
	}
}
