package com.globalscalingsoftware.fractions.mod3.internal;

import com.globalscalingsoftware.fractions.api.ContinuedFraction;
import com.globalscalingsoftware.fractions.mod3.api.Mod3ContinuedFractionFactory;
import com.globalscalingsoftware.fractions.mod3.api.Mod3ContinuedFractionFromValueFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

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
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<ContinuedFraction<?>>() {
				}, Mod3ContinuedFraction.class).build(
				Mod3ContinuedFractionFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<ContinuedFraction<?>>() {
				}, Mod3ContinuedFractionFromValue.class).build(
				Mod3ContinuedFractionFromValueFactory.class));
	}

}
