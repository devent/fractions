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
package com.globalscalingsoftware.fractions.mod3;

import com.globalscalingsoftware.fractions.mod3.api.Mod3ContinuedFractionFactory;
import com.globalscalingsoftware.fractions.mod3.api.Mod3ContinuedFractionFromValueFactory;
import com.globalscalingsoftware.fractions.mod3.internal.Mod3ContinuedFractionInternalModule;
import com.google.inject.AbstractModule;
import com.google.inject.Module;

/**
 * {@link Module} for the mod3 continued fraction implementation. Binds
 * {@link Mod3ContinuedFractionFactory} and
 * {@link Mod3ContinuedFractionFromValueFactory} to an implementation.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
@SuppressWarnings("deprecation")
public class Mod3ContinuedFractionModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new Mod3ContinuedFractionInternalModule());
	}

}
