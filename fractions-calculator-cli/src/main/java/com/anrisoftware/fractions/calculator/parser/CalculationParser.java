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

import javax.inject.Inject;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import com.anrisoftware.fractions.calculator.model.CalculationModel;
import com.google.inject.assistedinject.Assisted;

/**
 * Parses the command line arguments.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class CalculationParser {

	private final String[] args;

	@Inject
	private CalculationParserLogger log;

	@Inject
	private CalculationArgs model;

	@Inject
	CalculationParser(@Assisted String[] args) {
		this.args = args;
	}

	/**
	 * Parse the command line arguments.
	 * 
	 * @return this {@link CalculationParser} parser.
	 * 
	 * @throws ArgsException
	 *             if the command line arguments could not be parsed.
	 */
	public CalculationParser parse() throws ArgsException {
		try {
			CmdLineParser parser = new CmdLineParser(model);
			parser.parseArgument(args);
			return this;
		} catch (CmdLineException e) {
			throw log.errorParse(e, args);
		}
	}

	/**
	 * Returns the calculation model.
	 * 
	 * @return the {@link CalculationModel}.
	 */
	public CalculationModel getModel() {
		return model;
	}
}
