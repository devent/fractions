package com.anrisoftware.fractions.core;

import java.util.List;

/**
 * Calculate the denominators of a continued fraction from the value.
 * 
 * @param <Type>
 *            the value type of the denominators.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.3
 */
public interface EvaluateFractions<Type extends Number> {

	/**
	 * Calculate the denominators of a continued fraction from the specified
	 * value.
	 * 
	 * @param value
	 *            the value.
	 * 
	 * @param maxDenominators
	 *            the maximum count of denominators for the continued fraction.
	 * 
	 * @return a {@link List} of {@link Type} that are the denominators.
	 */
	List<Type> evaluate(double value, int maxDenominators);
}
