package psn.ph;

import java.util.Random;

public class Obfuscator {
	private static final String REFERENCE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+-*/,!\"?";
	private static final char WHITESPACE = ' ';
	private Random random = new Random();

	protected String encode(String string, String offsetChar) {
		StringBuilder sb = new StringBuilder();
		if (string != null && offsetChar != null) {
			int offset = REFERENCE_STRING.indexOf(offsetChar.toUpperCase());
			sb.append(offsetChar);
			int len = string.length();
			String ref2 = REFERENCE_STRING.substring(REFERENCE_STRING.length() - offset) + REFERENCE_STRING.substring(0, REFERENCE_STRING.length() - offset);
			for (int i = 0; i < len; i++) {
				if (string.charAt(i) == WHITESPACE) {
					sb.append(WHITESPACE);
				} else {
					int n1 = REFERENCE_STRING.indexOf(string.charAt(i));
					sb.append(ref2.charAt(n1));
				}
			}
		}
		return sb.toString();
	}

	public String encode(String string) {
		int rand = Math.abs(random.nextInt()) % REFERENCE_STRING.length();
		return encode(string, Character.toString(REFERENCE_STRING.charAt(rand)));
	}

	public String decode(String string) {
		StringBuilder sb = new StringBuilder();
		if (string != null) {
			char offsetChar = string.charAt(0);
			int offset = REFERENCE_STRING.indexOf(offsetChar);
			String ref2 = REFERENCE_STRING.substring(REFERENCE_STRING.length() - offset) + REFERENCE_STRING.substring(0, REFERENCE_STRING.length() - offset);
			int len = string.length();
			for (int i = 1; i < len; i++) {
				if (string.charAt(i) == WHITESPACE) {
					sb.append(WHITESPACE);
				} else {
					int n1 = ref2.indexOf(string.charAt(i));
					sb.append(REFERENCE_STRING.charAt(n1));
				}
			}
		}
		return sb.toString();
	}
}
