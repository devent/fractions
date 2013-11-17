package com.anrisoftware.fractions.calculator.starter;

import com.anrisoftware.fractions.calculator.app.App;
import com.anrisoftware.fractions.calculator.app.AppException;
import com.anrisoftware.fractions.calculator.app.AppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Starts the application.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class Starter {

	/**
	 * Starts the application with the specified command line arguments.
	 * 
	 * @param args
	 *            the command line arguments.
	 * 
	 * @throws AppException
	 *             if there was an error in the application.
	 */
	public static void main(String[] args) throws AppException {
		Injector injector = Guice.createInjector(new AppModule());
		App app = injector.getInstance(App.class);
		app.doStart(args);
	}
}
