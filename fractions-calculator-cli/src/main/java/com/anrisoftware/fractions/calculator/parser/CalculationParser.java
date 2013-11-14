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
	public CalculationParser parse() {
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
