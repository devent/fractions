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
