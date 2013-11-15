package com.anrisoftware.fractions.calculator.app;

import javax.inject.Inject;

import com.anrisoftware.fractions.calculator.model.CalculationModel;
import com.anrisoftware.fractions.calculator.parser.CalculationParser;
import com.anrisoftware.fractions.calculator.parser.CalculationParserFactory;
import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.templates.api.TemplatesFactory;

public class App {

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

	public void doStart(String[] args) {
		CalculationParser parser = parserFactory.create(args).parse();
		CalculationModel model = parser.getModel();
		double value = model.getValue();
		int max = model.getMax();
		ContinuedFraction fraction;
		fraction = model.getFractionFactory().fromValue(value, max);
		int[] denos = fraction.toArray();
		System.out.println(fractionTemplate.getText("fraction", "d", denos));
	}
}
