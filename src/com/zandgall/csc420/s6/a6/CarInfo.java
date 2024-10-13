package com.zandgall.csc420.s6.a6;

public class CarInfo {
	// Restricted types
	private static final int NUM_MAKE_CODES = 5;
	public enum Make {
		Honda(0), Chevy(1), Toyota(2), Porsche(3), Ford(4);
		
		protected final int value;
		private Make(int value) {
			this.value = value;
		}
	}

	private static final int NUM_TYPE_CODES = 5;
	public enum Type {
		Truck(0), SUV(1), Sedan(2), Coupe(3), Minivan(4);

		protected final int value;
		private Type(int value) {
			this.value = value;
		}
	}

	private static final int NUM_STATE_CODES = 8;
	public enum State {
		FL(0), MN(1), TN(2), TX(3), MI(4), NY(5), KY(6), GA(7);

		protected final int value;
		private State(int value) {
			this.value = value;
		}
	}

	private static final int NUM_ALPHANUM_CODES = 37;
	private static int alphaNumericMap(char character) {
		if(character >= 'A' && character <= 'Z')
			return character - 'A'; // [0, 25]
		if(character >= 'a' && character <= 'z')
			return character - 'a'; // [0, 25]
		if(character >= '0' && character <= '9')
			return character - '0' + 26; // [26, 36]
		return (int)character;
	}

	// Info
	private Make make;
	private Type type;
	private State state;
	private String owner;
	private char[] license = new char[6];

	public CarInfo(String owner, String license, State state, Make make, Type type) {
		if(license.length() != 6)
			throw new RuntimeException("License must be 6 characters long!");
		this.owner = owner;
		this.license[0] = license.charAt(0);
		this.license[1] = license.charAt(1);
		this.license[2] = license.charAt(2);
		this.license[3] = license.charAt(3);
		this.license[4] = license.charAt(4);
		this.license[5] = license.charAt(5);
		this.state = state;
		this.make = make;
		this.type = type;
	}

	public CarInfo(String owner, char[] license, State state, Make make, Type type) {
		if(license.length != 6)
			throw new RuntimeException("License must be 6 characters long!");
		this.owner = owner;
		this.license = license;
		this.state = state;
		this.make = make;
		this.type = type;
	}

	public int hashCode() {
		int out = 0;
		// first apply all license characters as if they're base36 values
		for(char c : license) {
			out += alphaNumericMap(c);
			out *= NUM_ALPHANUM_CODES;
		}

		// apply type, make, and state using their given values and making space for the next digit
		out += type.value;
		out *= NUM_TYPE_CODES;
		out += make.value;
		out *= NUM_MAKE_CODES;
		out += state.value;
		out *= NUM_STATE_CODES;

		// xor in the hash code of the owner string
		out ^= owner.hashCode();
		return out;
	}

	public String toString() {
		return String.format("%s, %s - %s %s - %c%c%c%c%c%c", owner, state, make, type, license[0], license[1], license[2], license[3], license[4], license[5]);
	}
}
