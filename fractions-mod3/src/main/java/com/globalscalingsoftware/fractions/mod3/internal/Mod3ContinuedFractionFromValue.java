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
package com.globalscalingsoftware.fractions.mod3.internal;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
class Mod3ContinuedFractionFromValue extends AbstractContinuedFraction<Integer> {

	private final float z;

	@Inject
	public Mod3ContinuedFractionFromValue(@Assisted float z,
			@Assisted List<Integer> denominators) {
		super(denominators);
		this.z = z;
	}

	@Override
	public float getZ() {
		return z;
	}
}
