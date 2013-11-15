package com.anrisoftware.fractions.calculator.app;

import com.anrisoftware.fractions.calculator.parser.CalculationParserModule;
import com.anrisoftware.resources.templates.maps.TemplatesDefaultMapsModule;
import com.anrisoftware.resources.templates.templates.TemplatesResourcesModule;
import com.anrisoftware.resources.templates.worker.STDefaultPropertiesModule;
import com.anrisoftware.resources.templates.worker.STWorkerModule;
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
		install(new STDefaultPropertiesModule());
		install(new STWorkerModule());
		install(new TemplatesResourcesModule());
		install(new TemplatesDefaultMapsModule());
	}

}
