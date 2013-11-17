package com.anrisoftware.fractions.calculator.model;

import java.text.NumberFormat;

import com.anrisoftware.fractions.core.FractionFactory;
import com.anrisoftware.fractions.format.FractionFormat;

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

	/**
	 * Returns the denominators of the continued fraction to calculate a value
	 * from.
	 * 
	 * @return the denominators or {@code null}.
	 * 
	 * @see FractionFormat#parse(String)
	 */
	String getDenominators();

	/**
	 * Returns the value number formatter.
	 * 
	 * @return the {@link NumberFormat}.
	 */
	NumberFormat getValueFormat();
}
