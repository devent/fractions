package com.anrisoftware.fractions.calculator.parser;

/**
 * Command line arguments parser factory.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface CalculationParserFactory {

	/**
	 * Creates the parser that parses the specified command line arguments.
	 * 
	 * @param args
	 *            the arguments array.
	 * 
	 * @return the {@link CalculationParser}.
	 */
	CalculationParser create(String[] args);
}
