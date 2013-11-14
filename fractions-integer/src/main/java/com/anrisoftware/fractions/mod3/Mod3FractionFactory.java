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
package com.anrisoftware.fractions.mod3;

/**
 * Factory to create a new mod3 continued fraction from the specified value.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface Mod3FractionFactory {

	/**
	 * Create a new mod3 continued fraction from the partial numerator and the
	 * denominators.
	 * 
	 * @param z
	 *            the partial numerator.
	 * 
	 * @param denos
	 *            the array of denominator values.
	 * 
	 * @return the {@link Mod3Fraction}.
	 */
	Mod3Fraction create(double z, int[] denos);

	/**
	 * Creates a new mod3 continued fraction from the specified value. The
	 * partial numerator for all denominators {@code z} of this continued
	 * fraction will be 1.
	 * 
	 * @param value
	 *            the value.
	 * 
	 * @param max
	 *            the maximum count of the denominators.
	 * 
	 * @return the {@link Mod3Fraction}.
	 */
	Mod3Fraction fromValue(double value, int max);

}