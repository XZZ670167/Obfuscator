package psn.ph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ObfuscatorTest {
	Obfuscator me = new Obfuscator();
	static final String CLEAR_TEXT = "HELLO WORLD";

	@Test
	public void testEncodeStringString() {
		assertEquals("BGDKKN VNQKC", this.me.encode(CLEAR_TEXT, "B"));
	}

	@Test
	public void testEncodeDecodeString() {
		assertEquals(CLEAR_TEXT, me.decode("BGDKKN VNQKC"));

		String s1 = null;
		String s2 = null;
		do {
			s1 = this.me.encode(CLEAR_TEXT);
			s2 = this.me.encode(CLEAR_TEXT);
		} while (s1.equals(s2));
		String d1 = me.decode(s1);
		String d2 = me.decode(s2);
		// System.out.println("s1 =" + s1 + ", s1 decode = " + this.me.decode(s1) + ", s2 = " + s2 + ", s2 decode = " + this.me.decode(s2));
		assertEquals(me.decode(s1), me.decode(s2));
		assertEquals(me.decode(s1), CLEAR_TEXT);
		assertEquals(me.decode(s2), CLEAR_TEXT);
	}

}
