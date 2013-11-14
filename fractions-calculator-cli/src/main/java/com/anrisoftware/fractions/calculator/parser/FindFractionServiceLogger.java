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
