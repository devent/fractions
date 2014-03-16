/*
 * Copyright 2011-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.fractions.format;

import static com.google.inject.Guice.createInjector;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the duration format factory.
 * 
 * @see FractionFormatFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class FractionFormatModule extends AbstractModule {

	/**
	 * Returns the fraction format factory.
	 * 
	 * @return the {@link FractionFormatFactory}.
	 */
	public static FractionFormatFactory getFactory() {
		return InjectorInstance.factory;
	}

	private static class InjectorInstance {

		static final Injector injector = createInjector(new FractionFormatModule());

		static final FractionFormatFactory factory = injector
				.getInstance(FractionFormatFactory.class);
	}

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(FractionFormat.class,
				FractionFormat.class).build(FractionFormatFactory.class));
	}

}
