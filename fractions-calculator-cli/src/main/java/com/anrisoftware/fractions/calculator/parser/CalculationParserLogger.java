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
