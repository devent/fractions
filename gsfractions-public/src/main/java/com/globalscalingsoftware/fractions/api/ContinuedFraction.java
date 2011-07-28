package com.globalscalingsoftware.fractions.api;

import java.util.List;

public interface ContinuedFraction<Type extends Number> extends List<Type> {

	/**
	 * Returns the original value of this continued fraction.
	 */
	double getValue();

	/**
	 * Returns the z value of this continued fraction.
	 */
	float getZ();

	/**
	 * Returns a calculated value from this continued fraction.
	 */
	float floatValue();

	/**
	 * Returns a calculated value from this continued fraction.
	 */
	double doubleValue();

}
