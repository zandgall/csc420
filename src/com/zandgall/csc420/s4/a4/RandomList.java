package com.zandgall.csc420.s4.a4;

import java.util.Random;

public class RandomList<E> {

	private Random rand;
	private Entry<E> root;
	private final Entry<E> NULL_ENTRY = new Entry<>(null);
	private int size = 0;

	public RandomList() {
		rand = new Random(System.nanoTime());
		root = null;
	}

	public RandomList(E entry) {
		rand = new Random(System.nanoTime());
		root = new Entry<>(entry);
	}

	public int size() {
		return size;
	}

	public void randomAdd(E value) {
		if(size == 0)
			root = new Entry<>(value);
		else {
			int index = rand.nextInt(size + 1);
			if(index == 0) // insert as new root
				root = new Entry<>(value, null, root);
			else // insert after previous element
				getEntry(index - 1).insert(value);
		}
		size++;
		// If nextInt were to return a value outside the bounds [0, size), we would have inserted at NULL_ENTRY
		// nextInt(size) does not return a value outside the bounds [0, size), so we do not have to worry about NULL_ENTRY being modified
	}

	public E randomGet() {
		return get(rand.nextInt(size));
	}

	public E randomRemove() {
		return remove(rand.nextInt(size));
	}

	public E get(int index) {
		return getEntry(index).value;
	}

	// Grab the entry that's removed, and deduct size
	public E remove(int index) {
		E out = getEntry(index).remove();
		size--;
		return out;
	}	

	private Entry<E> getEntry(int index) {
		// Bounds check, return a null-version of entry, rather than null itself
		// this allows us to call .value and .removeNext() without needing to check first
		if(index < 0 || index >= size)
			return NULL_ENTRY;
		// Select the proper entry
		Entry<E> current = root;
		for(int i = 0; i < index; i++)
			current = current.next;

		// For fancies, this could be written in one line as
		// for(int i = 0; i < index; i++, current = current.next);
		return current;
	}

	public void printList() {
		System.out.println("List:");
		Entry<E> current = root;
		int i = 0;
		while(current != null) {
			System.out.printf("\t%d: %s%n", i, current.value.toString(), current.last, current, current.next);
			i++;
			current = current.next;
		}
	}

	public void printReverseList() {
		System.out.println("Reverse List:");
		Entry<E> current = getEntry(size - 1);
		int i = size - 1;
		while(current != null) {
			System.out.printf("\t%d: %s%n", i, current.value.toString());
			i--;
			current = current.last;
		}
	}

	// class that holds a generic value, and references to next link in the chain 
	private static class Entry<E> {
		protected E value;
		protected Entry<E> last, next;
		
		protected Entry(E value) {
			this.value = value;
			next = null;
			last = null;
		}

		protected Entry(E value, Entry<E> last, Entry<E> next) {
			this.value = value;
			this.last = last;
			if(last!=null)
				last.next = this;
			this.next = next;
			if(next!=null)
				next.last = this;
		}

		// Add new link with given value, and current 'next' (either an entry or null) as its next chain
		protected void insert(E value) {
			next = new Entry<>(value, this, next);
		}

		// Break chain by removing next, and setting the following link to next
		protected E remove() {
			if(next == null) {
				if(this.last != null)
					last.next = null;
				return value;
			}
			E out = value;
			this.value = next.value;
			this.next = next.next;
			return out;
		}
	}
}
