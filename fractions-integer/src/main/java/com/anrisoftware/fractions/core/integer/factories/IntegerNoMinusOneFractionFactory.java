/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-integer.
 *
 * fractions-integer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-integer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-integer. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.core.integer.factories;

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
