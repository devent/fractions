package com.anrisoftware.fractions.calculator.app;

import static com.anrisoftware.fractions.calculator.app.AppLogger._.invalid_args;
import static com.anrisoftware.fractions.calculator.app.AppLogger._.invalid_args_message;

import java.util.Arrays;

import javax.inject.Singleton;

import com.anrisoftware.fractions.calculator.parser.ArgsException;
import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link App}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@Singleton
class AppLogger extends AbstractLogger {

	enum _ {

		invalid_args("Invalid command line arguments"),

		invalid_args_message("Invalid command line arguments: '{}'"),

		args("arguments");

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
	 * Creates a logger for {@link App}.
	 */
	public AppLogger() {
		super(App.class);
	}

	AppException errorParseArgs(ArgsException e, String[] args) {
		String argss = Arrays.toString(args);
		return logException(new AppException(invalid_args, e).add(args, argss),
				invalid_args_message, argss);
	}

}
