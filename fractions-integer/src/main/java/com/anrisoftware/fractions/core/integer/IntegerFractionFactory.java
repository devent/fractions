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
	 *            the {@link Number} value.
	 * 
	 * @return the {@link ContinuedFraction}.
	 */
	ContinuedFraction<Number> fromValue(Number value);
}
