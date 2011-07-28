package com.globalscalingsoftware.fractions.mod3.internal;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.fractions.mod3.internal.Mod3ContinuedFraction.Mod3ContinuedFractionFactory;
import com.globalscalingsoftware.fractions.mod3.internal.Mod3ContinuedFractionFromValue.Mod3ContinuedFractionFromValueFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Module;

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
		bind(Mod3ContinuedFractionFactory.class).toProvider(
				newFactory(Mod3ContinuedFractionFactory.class,
						Mod3ContinuedFraction.class));
		bind(Mod3ContinuedFractionFromValueFactory.class).toProvider(
				newFactory(Mod3ContinuedFractionFromValueFactory.class,
						Mod3ContinuedFractionFromValue.class));
	}

}
