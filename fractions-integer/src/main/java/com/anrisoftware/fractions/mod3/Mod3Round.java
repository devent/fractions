/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-integer.
 *
 * fractions-integer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-integer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-integer. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.mod3;

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
