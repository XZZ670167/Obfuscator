package psn.ph;

import java.util.Random;

public class Obfuscator {
	private String ref = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+-*/,!\"?";
	private int offset;
	private static final char WHITESPACE = ' ';

	public String encode(String string, String offsetChar) {
		StringBuilder sb = new StringBuilder();
		if (string != null && offsetChar != null) {
			this.offset = string.indexOf(offsetChar.toUpperCase());
			sb.append(offsetChar);
			int len = string.length();
			for (int i = 0; i < len; i++) {
				if (string.charAt(i) == WHITESPACE) {
					sb.append(WHITESPACE);
				} else {
					sb.append(this.ref.charAt((this.ref.indexOf(string.charAt(i)) + this.offset) % this.ref.length()));
				}
			}
		}
		return sb.toString();
	}

	public String encode(String string) {
		Random random = new Random();
		int rand = random.nextInt() % this.ref.length();
		return this.encode(string, this.ref.substring(rand, rand + 1));
	}

	public String decode(String string) {
		StringBuilder sb = new StringBuilder();
		if (string != null) {
			char offsetChar = string.charAt(0);
			this.offset = string.indexOf(offsetChar) + 1;
			int len = string.length();
			for (int i = 1; i < len; i++) {
				if (string.charAt(i) == WHITESPACE) {
					sb.append(WHITESPACE);
				} else {
					sb.append(this.ref.charAt((this.ref.indexOf(string.charAt(i)) + this.offset) % this.ref.length()));
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Obfuscator me = new Obfuscator();
		String s = me.encode("HELLO WORLD", "B");
		System.out.println(s);
		s = me.decode(s);
		System.out.println(s);
		s = me.encode("HELLO WORLD");
		System.out.println(s);
		s = me.decode(s);
		System.out.println(s);
	}
}
