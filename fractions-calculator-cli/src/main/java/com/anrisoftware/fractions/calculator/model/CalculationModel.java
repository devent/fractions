package com.anrisoftware.fractions.calculator.model;

import com.anrisoftware.fractions.core.FractionFactory;

/**
 * Calculation model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface CalculationModel {

	/**
	 * Returns the value of the continued fraction.
	 * 
	 * @return the value.
	 */
	double getValue();

	/**
	 * Returns the continued fraction factory.
	 * 
	 * @return the {@link FractionFactory}.
	 */
	FractionFactory getFractionFactory();

	/**
	 * Returns the maximum denominators for the continued fractions.
	 * 
	 * @return the maximum denominators.
	 */
	int getMax();
}
