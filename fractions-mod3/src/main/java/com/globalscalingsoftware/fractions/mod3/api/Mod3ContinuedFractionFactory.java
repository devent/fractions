/*
 * Copyright 2011-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-mod3.
 *
 * fractions-mod3 is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-mod3 is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-mod3. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.fractions.mod3.api;

import com.globalscalingsoftware.fractions.api.ContinuedFraction;
import com.globalscalingsoftware.fractions.api.ContinuedFractionFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new mod3 implementation of a {@link ContinuedFraction}.
 * The continued fraction is calculated from the given value.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface Mod3ContinuedFractionFactory extends ContinuedFractionFactory {

	/**
	 * Factory to create a new mod3 implementation of a
	 * {@link ContinuedFraction}. The continued fraction is calculated from the
	 * given value.
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
	 * @return the new created mod3 implementation of a
	 *         {@link ContinuedFraction}. The continued fraction is calculated
	 *         from the given value.
	 */
	@Override
	ContinuedFraction<?> create(@Assisted("value") double value,
			@Assisted("z") float z,
			@Assisted("maxDenominators") int maxDenominators);
}
