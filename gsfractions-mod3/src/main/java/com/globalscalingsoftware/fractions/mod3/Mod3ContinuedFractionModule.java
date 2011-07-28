package com.globalscalingsoftware.fractions.mod3;

import com.globalscalingsoftware.fractions.mod3.api.Mod3ContinuedFractionFactory;
import com.globalscalingsoftware.fractions.mod3.api.Mod3ContinuedFractionFromValueFactory;
import com.globalscalingsoftware.fractions.mod3.internal.Mod3ContinuedFractionInternalModule;
import com.google.inject.AbstractModule;
import com.google.inject.Module;

/**
 * {@link Module} for the mod3 continued fraction implementation. Binds
 * {@link Mod3ContinuedFractionFactory} and
 * {@link Mod3ContinuedFractionFromValueFactory} to an implementation.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
@SuppressWarnings("deprecation")
public class Mod3ContinuedFractionModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new Mod3ContinuedFractionInternalModule());
	}

}
