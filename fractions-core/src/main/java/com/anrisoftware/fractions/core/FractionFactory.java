package com.anrisoftware.fractions.core;

/**
 * Factory to create continued fraction.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface FractionFactory {

	/**
	 * Create continued fraction from the partial numerator and the
	 * denominators.
	 * 
	 * @param z
	 *            the partial numerator.
	 * 
	 * @param denos
	 *            the array of denominator values.
	 * 
	 * @return the {@link ContinuedFraction}.
	 */
	ContinuedFraction create(double z, int[] denos);

	/**
	 * Creates continued fraction from the specified value. The partial
	 * numerator for all denominators {@code z} of this continued fraction will
	 * be 1.
	 * 
	 * @param value
	 *            the value.
	 * 
	 * @param max
	 *            the maximum count of the denominators.
	 * 
	 * @return the {@link ContinuedFraction}.
	 */
	ContinuedFraction fromValue(double value, int max);
}
