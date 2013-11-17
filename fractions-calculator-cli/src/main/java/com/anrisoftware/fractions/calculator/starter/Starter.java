/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-calculator-cli.
 *
 * fractions-calculator-cli is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-calculator-cli is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-calculator-cli. If not, see <http://www.gnu.org/licenses/>.
 */
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
