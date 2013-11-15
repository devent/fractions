package com.anrisoftware.fractions.calculator.app;

import javax.inject.Inject;

import com.anrisoftware.fractions.calculator.model.CalculationModel;
import com.anrisoftware.fractions.calculator.parser.ArgsException;
import com.anrisoftware.fractions.calculator.parser.CalculationParser;
import com.anrisoftware.fractions.calculator.parser.CalculationParserFactory;
import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.templates.api.TemplatesFactory;

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

	private Templates appTemplates;

	private TemplateResource fractionTemplate;

	@Inject
	void setTemplates(TemplatesFactory factory) {
		this.appTemplates = factory.create("AppTemplates");
		this.fractionTemplate = appTemplates
				.getResource("normal_continued_fraction");
	}

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
		int[] denos = calculateFraction(model);
		System.out.println(fractionTemplate.getText("fraction", "d", denos));
	}

	private int[] calculateFraction(CalculationModel model) {
		double value = model.getValue();
		int max = model.getMax();
		ContinuedFraction fraction;
		fraction = model.getFractionFactory().fromValue(value, max);
		return fraction.toArray();
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
