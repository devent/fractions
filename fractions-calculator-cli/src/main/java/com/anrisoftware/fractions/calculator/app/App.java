package com.anrisoftware.fractions.calculator.app;

import javax.inject.Inject;

import com.anrisoftware.fractions.calculator.model.CalculationModel;
import com.anrisoftware.fractions.calculator.parser.ArgsException;
import com.anrisoftware.fractions.calculator.parser.CalculationParser;
import com.anrisoftware.fractions.calculator.parser.CalculationParserFactory;
import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.FractionFactory;
import com.anrisoftware.fractions.format.FractionFormatFactory;

/**
 * Parses the command line arguments and print the calculated continued
 * fraction.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class App {

	@Inject
	private AppLogger log;

	@Inject
	private CalculationParserFactory parserFactory;

	@Inject
	private FractionFormatFactory formatFactory;

	/**
	 * Calculate the continued fraction from the specified command line
	 * arguments.
	 * 
	 * @param args
	 *            the command line arguments.
	 * 
	 * @throws AppException
	 *             if there was an error calculate the continued fraction.
	 */
	public void doStart(String[] args) throws AppException {
		CalculationModel model = parseArgs(args);
		FractionFactory factory = model.getFractionFactory();
		ContinuedFraction fraction = calculateFraction(model);
		System.out.println(formatFactory.create(factory).format(fraction));
	}

	private ContinuedFraction calculateFraction(CalculationModel model) {
		double value = model.getValue();
		int max = model.getMax();
		ContinuedFraction fraction;
		fraction = model.getFractionFactory().fromValue(value, max);
		return fraction;
	}

	private CalculationModel parseArgs(String[] args) throws AppException {
		try {
			CalculationParser parser = parserFactory.create(args).parse();
			CalculationModel model = parser.getModel();
			return model;
		} catch (ArgsException e) {
			throw log.errorParseArgs(e, args);
		}
	}
}
