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

import java.util.List;

import com.globalscalingsoftware.fractions.api.ContinuedFraction;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new mod3 implementation of a {@link ContinuedFraction}
 * from the given list of denominators. The value is calculated from the given
 * denominators.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface Mod3ContinuedFractionFromValueFactory {

	/**
	 * Creates a new mod3 implementation of a {@link ContinuedFraction} from the
	 * given list of denominators. The value is calculated from the given
	 * denominators.
	 * 
	 * @param z
	 *            the Z variable of the continued fraction.
	 * 
	 * @param denominators
	 *            the list of {@link Integer} denominators.
	 * 
	 * @return the new a new mod3 implementation of a {@link ContinuedFraction}.
	 */
	ContinuedFraction<?> create(@Assisted float z,
			@Assisted List<Integer> denominators);
}
