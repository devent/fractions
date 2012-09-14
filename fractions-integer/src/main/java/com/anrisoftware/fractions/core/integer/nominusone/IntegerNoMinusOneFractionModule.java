/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-integer. All rights reserved.
 */
package com.anrisoftware.fractions.core.integer.nominusone;

import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.integer.factories.IntegerNoMinusOneFractionFactory;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the integer continued fraction factory. The factory is used with
 * Guice.
 * 
 * <pre>
 * injector = Guice.createInjector(new IntegerFractionModule());
 * factory = injector.getInstance(IntegerNoMinusOneFractionFactory.class);
 * fraction = factory.fromValue(value, 9);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public class IntegerNoMinusOneFractionModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<ContinuedFraction<Integer>>() {
				}, IntegerNoMinusOneFraction.class).build(
				IntegerNoMinusOneFractionFactory.class));
	}
}
