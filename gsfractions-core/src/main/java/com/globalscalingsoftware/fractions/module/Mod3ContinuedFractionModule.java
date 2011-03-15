package com.globalscalingsoftware.fractions.module;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.fractions.internal.Mod3ContinuedFraction;
import com.globalscalingsoftware.fractions.internal.Mod3ContinuedFraction.Mod3ContinuedFractionFactory;
import com.globalscalingsoftware.fractions.internal.Mod3ContinuedFractionFromValue;
import com.globalscalingsoftware.fractions.internal.Mod3ContinuedFractionFromValue.Mod3ContinuedFractionFromValueFactory;
import com.google.inject.AbstractModule;

public class Mod3ContinuedFractionModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Mod3ContinuedFractionFactory.class).toProvider(
				newFactory(Mod3ContinuedFractionFactory.class,
						Mod3ContinuedFraction.class));
		bind(Mod3ContinuedFractionFromValueFactory.class).toProvider(
				newFactory(Mod3ContinuedFractionFromValueFactory.class,
						Mod3ContinuedFractionFromValue.class));
	}

}
