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
package com.anrisoftware.fractions.calculator.parser;

import static com.anrisoftware.fractions.calculator.parser.FindFractionServiceLogger._.error_find_service;
import static com.anrisoftware.fractions.calculator.parser.FindFractionServiceLogger._.error_find_service_message;
import static com.anrisoftware.fractions.calculator.parser.FindFractionServiceLogger._.service;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link FindFractionService}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@Singleton
class FindFractionServiceLogger extends AbstractLogger {

	enum _ {

		error_find_service("Error find service"),

		error_find_service_message("Error find service '{}'."),

		service("service");

		private String name;

		private _(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	/**
	 * Creates a logger for {@link FindFractionService}.
	 */
	public FindFractionServiceLogger() {
		super(FindFractionService.class);
	}

	ArgsException errorFindService(Object info) {
		return logException(
				new ArgsException(error_find_service).add(service, info),
				error_find_service_message, info);
	}

}
