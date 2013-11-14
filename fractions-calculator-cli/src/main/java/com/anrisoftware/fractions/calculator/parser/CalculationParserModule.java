package com.anrisoftware.fractions.calculator.parser;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the calculation command line arguments parser.
 * 
 * @see CalculationParserFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class CalculationParserModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(CalculationParser.class,
				CalculationParser.class).build(CalculationParserFactory.class));
	}

}
