package com.globalscalingsoftware.fractions.api;


/**
 * Factory to create a new {@link ContinuedFraction}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface ContinuedFractionFactory {

	/**
	 * Creates a new {@link ContinuedFraction} that represents the given value.
	 * 
	 * @param value
	 *            the value of the continued fraction.
	 * 
	 * @param z
	 *            the Z variable of the continued fraction.
	 * 
	 * @param maxDenominators
	 *            the maximum count of denominators for the continued fraction.
	 * 
	 * @return the new created {@link ContinuedFraction} that represents the
	 *         given value.
	 */
	ContinuedFraction<?> create(double value, float z, int maxDenominators);
}
