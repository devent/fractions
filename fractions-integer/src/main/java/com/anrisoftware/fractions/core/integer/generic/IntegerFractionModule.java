/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-integer. All rights reserved.
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
