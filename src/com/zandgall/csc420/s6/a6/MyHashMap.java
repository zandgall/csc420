package com.zandgall.csc420.s6.a6;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MyHashMap<K, V> implements Map<K, V> {

	private ArrayList<Entry<K,V>> entries;

	private int size = 0;	

	// ONLY EVER POWER OF 2
	private int N = 8;

	public MyHashMap() {
		entries = new ArrayList<>(N);
		for(int i = 0; i < N; i++)
			entries.add(null);
	}

	public void testPrint(PrintWriter writer) {
		int lstr = 0;
		for(Entry<K, V> e : entries)
			if(e != null && lstr < e.toString().length())
				lstr = e.toString().length();
		writer.print("╔═══╤═══╤═");
		for(int i = 0; i < lstr; i++)
			writer.print("═");
		writer.println("═╗");
		for(int i = 0; i < N; i++) {
			int n = 0;
			Entry<K, V> e = entries.get(i);
			while(e != null) {
				n++;
				e = e.next;
			}
			if(entries.get(i) == null)
				writer.printf("║%3d│%3d│ %-"+lstr+"s ║%n", i, n, " ");
			else
				writer.printf("║%3d│%3d│ %-"+lstr+"s ║%n", i, n, entries.get(i));
		}
		
		writer.print("╚═══╧═══╧═");
		for(int i = 0; i < lstr; i++)
			writer.print("═");
		writer.println("═╝");
		writer.flush();
	}

	private int getIndexFor(Object key) {
		int h = key.hashCode();
		h ^= (h >>> 20) ^ (h >>> 12);
		h ^= (h >>> 7) ^ (h >>> 4);
		return h & N-1;
	}

	public void clear() {
		size = 0;
		N = 8;
		entries.clear();
		for(int i = 0; i < N; i++)
			entries.add(null);
	}

	// Public for the sake of MyHashMapTest.java
	public void expand() {
		// re-put all map entries after doubling capacity
		Set<Map.Entry<K,V>> last = entrySet();
		N *= 2;
		size = 0;
		entries = new ArrayList<>(N);
		for(int i = 0; i < N; i++)
			entries.add(null);
		for(Map.Entry<K, V> e : last)
			put(e.getKey(), e.getValue());
	}

	@Override
	public boolean containsKey(Object key) {
		// Get entry at supplemented index
		int i = getIndexFor(key);
		Entry<K,V> e = entries.get(i);
		while(e != null && !e.key.equals(key))
			e = e.next;
		return e != null;
	}

	// Just linear search for values
	@Override
	public boolean containsValue(Object value) {
		for(Entry<K,V> entry : entries)
			if(entry.value.equals(value))
				return true;
		return false;
	}

	public Set<Map.Entry<K,V>> entrySet() {
		ArrayList<Map.Entry<K,V>> allEntries = new ArrayList<>(size);
		for(Entry<K,V> e : entries) {
			while(e != null) {
				allEntries.add(e);
				e = e.next;
			}
		}
		return Set.copyOf(allEntries);
	}

	// Return true if other is a MyHashMap with all entries equal
	public boolean equals(Object other) {
		if(other instanceof MyHashMap otherM) {
			if(otherM.size != size)
				return false;
			for(int i = 0; i < entries.size(); i++) {
				if(!entries.get(i).equals(otherM.entries.get(i)))
					return false;
			}
			return true;
		}
		return false;
	}

	public int size() {
		return size;
	}

	private Entry<K,V> getEntry(Object key) {
		int index = getIndexFor(key);
		Entry<K,V> e = entries.get(index);
		while(e != null && !e.key.equals(key))
			e = e.next;
		return e;

	}

	public V get(Object key) {
		Entry<K,V> e = getEntry(key);
		if(e == null)
			return null;
		return e.value;
	}

	public int hashCode() {
		return super.hashCode();
	}

	public boolean isEmpty() {
		return size()==0;
	}

	public Set<K> keySet() {
		return entrySet().stream().map(x -> x.getKey()).collect(Collectors.toSet());
	}

	public V put(K key, V value) {
		// If the map is full, expand to avoid more collisions
		if(size == N)
			expand();

		// Check for an element at the base index
		// if there isn't one, create one
		// if there is one AND it's the right key, set its value
		int index = getIndexFor(key);
		Entry<K,V> e = entries.get(index);
		if(e == null) {
			entries.set(index, new Entry<>(key, value));
			size++;
			return value;
		} else if (e.key.equals(key))
			return e.setValue(value);

		// Collision!
		// As long as there are linked elements that aren't null and don't have the same key,
		// move to the next link
		while(e.next != null && !e.next.key.equals(key))
			e = e.next;
		// If there is no link in this index with this key, create a new one
		if(e.next == null) {
			e.next = new Entry<>(key, value);
			size++;
		// if there is a link with the right key, set its new value
		} else
			e.next.setValue(value);

		return value;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		for(Map.Entry<? extends K, ? extends V> entry : m.entrySet())
			put(entry.getKey(), entry.getValue());
	}

	public V remove(Object key) {
		// Check the first element at the base index, if it exists
		int index = getIndexFor(key);
		Entry<K,V> e = entries.get(index);
		if(e == null)
			return null;
		if(e.key.equals(key)) {
			entries.set(index, null);
			size--;
			return e.value;
		}

		// If key is not the first entry at the index but there are still other entries,
		while(e.next != null && !e.next.key.equals(key))
			e = e.next;

		if(e.next == null)
			return null; // We couldn't find the key
	
		// Cut 'e.next' out of the chain
		V v = e.next.value;
		e.next = e.next.next;
		size--;
		return v;
	}

	public Collection<V> values() {
		return entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {
		K key;
		V value;

		protected Entry<K, V> next = null;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() { return key; }
		public V getValue() { return value; }

		public int hashCode() { return key.hashCode() ^ value.hashCode(); }

		public V setValue(V value) {
			this.value = value;
			return this.value;
		}

		@Override
		public boolean equals(Object other) {
			if(other instanceof Entry e) {
				if(e.value != value || e.key != key)
					return false;
				if(e.next != null && next != null)
					return e.next.equals(next);
				if((e.next == null && next != null) || (e.next != null && next == null))
					return false;
				return true;
			}
			return false;
		}

		public String toString() {
			String out = "(" + key.toString() + ": " + value.toString() + ")";
			if(next!=null)
				out += " -> " + next.toString();
			return out;
		}
	}


}
