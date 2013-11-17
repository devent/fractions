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
package com.anrisoftware.fractions.calculator.app;

import java.text.NumberFormat;
import java.text.ParseException;

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

	private String output;

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
		String out = null;
		if (model.getDenominators() != null) {
			out = calculateValue(model, factory);
		} else {
			out = calculateFraction(model, factory);
		}
		this.output = out;
		System.out.println(out);
	}

	private String calculateValue(CalculationModel model,
			FractionFactory factory) throws AppException {
		String d = model.getDenominators();
		NumberFormat format = model.getValueFormat();
		try {
			ContinuedFraction fraction = formatFactory.create(factory).parse(d);
			return format.format(fraction.doubleValue());
		} catch (ParseException e) {
			throw log.errorParseFraction(e, d);
		}
	}

	private String calculateFraction(CalculationModel model,
			FractionFactory factory) {
		ContinuedFraction fraction = calculateFraction(model);
		return formatFactory.create(factory).format(fraction);
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

	/**
	 * Returns the output of the application.
	 * 
	 * @return the output or {@code null}.
	 */
	public String getOutput() {
		return output;
	}
}
