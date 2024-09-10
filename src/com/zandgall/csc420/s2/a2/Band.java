package com.zandgall.csc420.s2.a2;

public class Band {
	// Make band an immutable class
	private final String name;
	private final float setTime;

	public Band(String name, float setTime) {
		this.name = name;
		this.setTime = setTime;
	}

	public final String getName() {
		return name;
	}

	public final float getSetTime() {
		return setTime;
	}

	public final int getSetTimeMinutes() {
		return (int)Math.floor(setTime);
	}

	public final int getSetTimeSeconds() {
		return (int)Math.floor(setTime*100) % 100;
	}

	@Override
	public String toString() {
		return name + " - " + getSetTimeMinutes()+":"+getSetTimeSeconds();
	}
}
