package com.globalscalingsoftware.fractions.api;

import java.util.List;

/**
 * Represents a continued fraction with the denominators as {@link Number}s. You
 * can use the continued fraction as a {@link List} and get, delete and add
 * denominators.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
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
