package com.globalscalingsoftware.fractions.internal;


public class Mod3Round {

	public int round(double value) {
		int n = (int) Math.abs(value);
		if (n % 3 != 0) {
			n++;
			if (n % 3 != 0 && frac(value) == 0) {
				n -= 2;
			}
		}
		if (value < 0) {
			n = -n;
		}
		return n;
	}

	/**
	 * Liefert die Nachkommastellen einer reelen Zahl. I.e. 1.1234 returns
	 * 0.1234.
	 */
	private double frac(double value) {
		int n = (int) value;
		return value - n;
	}

}
