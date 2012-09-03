package com.anrisoftware.fractions.core.integer;

import com.anrisoftware.fractions.core.ContinuedFraction;

/**
 * Factory to create a new integer continued fraction from the specified value.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface IntegerFractionFactory {

	/**
	 * Creates a new integer continued fraction from the specified value.
	 * 
	 * @param value
	 *            the value.
	 * 
	 * @param z
	 *            the partial numerator for all denominators of this continued
	 *            fraction.
	 * 
	 * @return the {@link ContinuedFraction}.
	 */
	ContinuedFraction<Number> fromValue(double value, Number z);
}
