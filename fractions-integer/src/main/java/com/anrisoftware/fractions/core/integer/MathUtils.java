/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of fractions-integer. All rights reserved.
 */
package com.anrisoftware.fractions.core.integer;

import static org.apache.commons.math3.util.FastMath.ceil;
import static org.apache.commons.math3.util.FastMath.floor;

/**
 * Various mathematical utilities.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class MathUtils {

	/**
	 * Rounds the specified value toward zero.
	 * 
	 * @param value
	 *            the value.
	 * 
	 * @return the rounded value.
	 */
	static public double fix(double value) {
		if (value < 0) {
			return ceil(value);
		}
		return floor(value);
	}

}
