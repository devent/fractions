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

import static com.anrisoftware.fractions.calculator.parser.CalculationArgsLogger._.max_set;
import static com.anrisoftware.fractions.calculator.parser.CalculationArgsLogger._.service_set;
import static com.anrisoftware.fractions.calculator.parser.CalculationArgsLogger._.value_format_set;
import static com.anrisoftware.fractions.calculator.parser.CalculationArgsLogger._.value_set;

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

		service_set("Continued fraction service '{}' set."),

		max_set("Maximum denominators {} set."),

        value_set("Continued fraction value '{}' set."),

        value_format_set("Value format '{}' set.");

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

	void serviceSet(String service) {
		debug(service_set, service);
	}

	void maxSet(int max) {
		debug(max_set, max);
	}

	void valueSet(String value) {
		debug(value_set, value);
	}

    void valueFormatSet(String format) {
        debug(value_format_set, format);
    }

}
