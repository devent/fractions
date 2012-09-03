package com.globalscalingsoftware.fractions.mod3.internal;

import java.io.Serializable;

/**
 * Helper class to calculate the round mod 3.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
@SuppressWarnings("serial")
class Mod3Round implements Serializable {

	/**
	 * Returns the rounded value of a given x that can be divided by 3.
	 */
	public int round(double x) {
		int n = (int) Math.abs(x);
		if (n % 3 != 0) {
			n++;
			if (n % 3 != 0 && frac(x) == 0) {
				n -= 2;
			}
		}
		if (x < 0) {
			n = -n;
		}
		return n;
	}

	/**
	 * Liefert die Nachkommastellen einer reelen Zahl. I.e. 1.1234 returns
	 * 0.1234.
	 */
	private double frac(double x) {
		int n = (int) x;
		return x - n;
	}

}
