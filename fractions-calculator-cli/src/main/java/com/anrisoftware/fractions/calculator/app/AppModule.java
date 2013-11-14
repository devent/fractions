package com.anrisoftware.fractions.calculator.app;

import com.anrisoftware.fractions.calculator.parser.CalculationParserModule;
import com.google.inject.AbstractModule;

/**
 * Install every needed module of the application.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new CalculationParserModule());
	}

}
