/*
 * Copyright 2011-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-core.
 *
 * fractions-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.core;

import java.util.List;

/**
 * Represents a continued fraction with the denominators as {@link Number}s. You
 * can use the continued fraction as a {@link List} to access the denominators.
 * 
 * @param <Type>
 *            the value type of the denominators.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
public interface ContinuedFraction<Type extends Number> extends List<Type> {

	/**
	 * Returns the maximum count of denominators for the continued fraction.
	 * 
	 * @return the maximum count.
	 */
	int getMaxDenominators();

	/**
	 * Returns the original value of this continued fraction.
	 * 
	 * @return the original value.
	 */
	double getValue();

	/**
	 * Returns the partial numerator for all denominators of this continued
	 * fraction.
	 * 
	 * @return the partial numerator for all denominators.
	 */
	double getZ();

	/**
	 * Returns a calculated value from this continued fraction.
	 * 
	 * @return the float value.
	 */
	float floatValue();

	/**
	 * Returns a calculated value from this continued fraction.
	 * 
	 * @return the double value.
	 */
	double doubleValue();

}
