package com.globalscalingsoftware.fractions.internal

import org.junit.Test;

class Mod3RoundTest {
	
	@Test
	void test() {
		for (double i = -10.0; i <= 10.0; i+=0.1) {
			def round = new Mod3Round().round(i)
			println "$i;$round;"
		}
	}
}
