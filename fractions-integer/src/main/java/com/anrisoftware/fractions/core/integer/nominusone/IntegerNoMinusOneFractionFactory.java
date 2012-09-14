/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-integer. All rights reserved.
 */
package com.anrisoftware.fractions.core.integer.nominusone;

import com.anrisoftware.fractions.core.ContinuedFraction;

/**
 * Factory to create a new integer continued fraction from the specified value.
 * The denominators of this continued fraction cannot be of value -1. The
 * partial numerator for all denominators of this continued fraction will be 1.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface IntegerNoMinusOneFractionFactory {

	/**
	 * Creates a new integer continued fraction from the specified value. The
	 * denominators of this continued fraction cannot be of value -1. The
	 * partial numerator for all denominators of this continued fraction will be
	 * 1.
	 * 
	 * @param value
	 *            the value.
	 * 
	 * @param maxDenominators
	 *            the maximum count of the denominators.
	 * 
	 * @return the {@link ContinuedFraction}.
	 */
	ContinuedFraction<Integer> fromValue(double value, int maxDenominators);
}
