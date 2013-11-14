package com.anrisoftware.fractions.calculator.parser;

import static com.anrisoftware.fractions.calculator.parser.CalculationParserLogger._.arguments;
import static com.anrisoftware.fractions.calculator.parser.CalculationParserLogger._.error_parse;
import static com.anrisoftware.fractions.calculator.parser.CalculationParserLogger._.error_parse_message;

import java.util.Arrays;

import javax.inject.Singleton;

import org.kohsuke.args4j.CmdLineException;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link CalculationParser}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@Singleton
class CalculationParserLogger extends AbstractLogger {

	enum _ {

		error_parse("Error parse command line arguments"),

		error_parse_message("Error parse command line arguments {}."),

		arguments("arguments");

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
	 * Creates a logger for {@link CalculationParser}.
	 */
	public CalculationParserLogger() {
		super(CalculationParser.class);
	}

	ArgsException errorParse(CmdLineException e, String[] args) {
		String argss = Arrays.toString(args);
		return logException(
				new ArgsException(error_parse, e).add(arguments, argss),
				error_parse_message, argss);
	}

}
