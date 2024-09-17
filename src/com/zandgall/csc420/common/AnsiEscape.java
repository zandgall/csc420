package com.zandgall.csc420.common;

/* Taken from https://en.wikipedia.org/wiki/ANSI_escape_code#Colors */
public class AnsiEscape {
	private static String RESET = "\u001B[0m";
	public static final String UNCHANGED = "";

	/* Styles */
	public static final String NORMAL = "0;";
	public static final String BOLD = "1;";
	public static final String FAINT = "2;";
	public static final String ITALIC = "3;";

	/* Color mode */
	public static final String FOREGROUND = "38;5;";
	public static final String BACKGROUND = "48;5;";

	/* Colors */
	public static final String BLACK = "0;";
	public static final String RED = "1;";
	public static final String GREEN = "2;";
	public static final String YELLOW = "3;";
	public static final String BLUE = "4;";
	public static final String MAGENTA = "5;";
	public static final String CYAN = "6;";
	public static final String WHITE = "7;", LIGHT_GRAY = WHITE;
	public static final String BRIGHT_BLACK = "8;", GRAY = BRIGHT_BLACK;
	public static final String BRIGHT_RED = "9;";
	public static final String BRIGHT_GREEN = "10;";
	public static final String BRIGHT_YELLOW = "11;";
	public static final String BRIGHT_BLUE = "12;";
	public static final String BRIGHT_MAGENTA = "13;";
	public static final String BRIGHT_CYAN = "14;";
	public static final String BRIGHT_WHITE = "15;";

	private static boolean USE_ESCAPES = true;
	public static void RevokeEscapes() {
		USE_ESCAPES = false;
		RESET = "";
	}

	public static String reset() {
		return RESET;
	}

	public static String get(String style) {
		return get(style, "", "");
	}

	public static String get(String style, String foregroundColor) {
		return get(style, foregroundColor, "");
	}

	public static String get(String style, String foregroundColor, String backgroundColor) {
		if(!USE_ESCAPES)
			return "";
		return "\u001B["+style+(foregroundColor== "" ? FOREGROUND+foregroundColor : "") +(backgroundColor == "" ? BACKGROUND+backgroundColor : "")+"m";
	}

	public static String rgb6(String style, String foregroundBackground, int r, int g, int b) {
		if(r < 0 || r > 5 || g < 0 || g > 5 || b < 0 || b > 5)
			throw new RuntimeException("R, G, and B must be in between 0 and 5 (inclusive)!");

		return "\u001B["+style+foregroundBackground+(16+36*r+6*g+b)+"m";
	}

	public static String gray24(String style, String foregroundBackground, int grayness) {
		if(grayness < 0 || grayness > 23)
			throw new RuntimeException("Grayness must be in between 0 and 23 (inclusive)!");

		return "\u001B["+style+foregroundBackground+(grayness+232)+"m";
	}


}
