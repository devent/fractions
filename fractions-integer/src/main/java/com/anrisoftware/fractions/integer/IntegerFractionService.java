/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.fractions.integer;

import org.mangosdk.spi.ProviderFor;

import com.anrisoftware.fractions.core.FractionFactory;
import com.anrisoftware.fractions.core.FractionService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Service for continued fraction with integer number denominators.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@ProviderFor(FractionService.class)
public class IntegerFractionService implements FractionService {

	private final Module[] modules;

	public IntegerFractionService() {
		this.modules = new Module[] { new IntegerFractionsModule() };
	}

	@Override
	public Object getInfo() {
		return IntegerFraction.class.getSimpleName();
	}

	@Override
	public FractionFactory getFactory(Object... parent) {
		return createInjector(parent).getInstance(IntegerFractionFactory.class);
	}

	private Injector createInjector(Object[] parent) {
		return parent.length > 0 ? ((Injector) parent[0])
				.createChildInjector(modules) : Guice.createInjector(modules);
	}

}
