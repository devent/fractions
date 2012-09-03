package com.anrisoftware.fractions.core.integer;

import com.anrisoftware.fractions.core.ContinuedFraction;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class IntegerFractionModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<ContinuedFraction<Number>>() {
				}, IntegerFraction.class).build(IntegerFractionFactory.class));
	}
}
