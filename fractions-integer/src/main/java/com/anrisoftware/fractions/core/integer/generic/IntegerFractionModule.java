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
package com.anrisoftware.fractions.core.integer.generic;

import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.integer.factories.IntegerFractionFactory;
import com.anrisoftware.fractions.core.integer.nominusone.IntegerNoMinusOneFractionModule;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the integer continued fraction factory. The factory is used with
 * Guice.
 * 
 * <pre>
 * injector = Guice.createInjector(new IntegerFractionModule());
 * factory = injector.getInstance(IntegerFractionFactory.class);
 * fraction = factory.fromValue(value, 9);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class IntegerFractionModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<ContinuedFraction<Integer>>() {
				}, IntegerFraction.class).build(IntegerFractionFactory.class));
		install(new IntegerNoMinusOneFractionModule());
	}
}
