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

import com.anrisoftware.fractions.core.ContinuedFraction;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the mod3 continued fraction factory. The factory is used with Guice.
 *
 * <pre>
 * injector = Guice.createInjector(new Mod3FractionsModule());
 * factory = injector.getInstance(Mod3FractionFactory.class);
 * fraction = factory.fromValue(value, 9);
 * </pre>
 *
 * @see Mod3FractionFactory
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class Mod3FractionsModule extends AbstractModule {

	@Override
	protected void configure() {
        install(new FactoryModuleBuilder().implement(ContinuedFraction.class,
                Mod3Fraction.class).build(Mod3FractionFactory.class));
	}
}
