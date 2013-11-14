package com.anrisoftware.fractions.calculator.parser;

import static com.anrisoftware.fractions.calculator.parser.CalculationArgsLogger._.error_parse_value;
import static com.anrisoftware.fractions.calculator.parser.CalculationArgsLogger._.error_parse_value_message;

import java.text.ParseException;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link CalculationArgs}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@Singleton
class CalculationArgsLogger extends AbstractLogger {

	enum _ {

		error_parse_value("Error parse value"),

		error_parse_value_message("Error parse value '{}'."),

		value("value");

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
	 * Creates a logger for {@link CalculationArgs}.
	 */
	public CalculationArgsLogger() {
		super(CalculationArgs.class);
	}

	ArgsException errorParseValue(ParseException e, String value) {
		return logException(
				new ArgsException(error_parse_value, e).add(value, value),
				error_parse_value_message, value);
	}

}
