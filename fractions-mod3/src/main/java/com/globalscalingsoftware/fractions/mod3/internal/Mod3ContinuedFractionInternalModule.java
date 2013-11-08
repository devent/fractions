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

import com.globalscalingsoftware.fractions.api.ContinuedFraction;
import com.globalscalingsoftware.fractions.mod3.api.Mod3ContinuedFractionFactory;
import com.globalscalingsoftware.fractions.mod3.api.Mod3ContinuedFractionFromValueFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Internal {@link Module} for the mod3 continued fraction implementation.
 * 
 * @deprecated not for public use.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
@Deprecated
public class Mod3ContinuedFractionInternalModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<ContinuedFraction<?>>() {
				}, Mod3ContinuedFraction.class).build(
				Mod3ContinuedFractionFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<ContinuedFraction<?>>() {
				}, Mod3ContinuedFractionFromValue.class).build(
				Mod3ContinuedFractionFromValueFactory.class));
	}

}
