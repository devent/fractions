/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import javax.inject.Inject;

import com.anrisoftware.fractions.calculator.model.CalculationModel;
import com.anrisoftware.fractions.calculator.parser.ArgsException;
import com.anrisoftware.fractions.calculator.parser.CalculationParserFactory;
import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.FractionFactory;
import com.anrisoftware.fractions.core.FractionService;
import com.anrisoftware.fractions.format.FractionFormat;
import com.anrisoftware.fractions.format.FractionFormatFactory;
import com.google.inject.Injector;

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
    private Injector injector;

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
        String output = createOutput(model);
        this.output = output;
    }

    private String createOutput(CalculationModel model) {
        if (model.getValue() != null) {
            return outputFraction(model);
        }
        if (model.getFraction() != null) {
            return outputFractionValue(model);
        }
        if (model.getFractionA() != null && model.getFractionB() != null) {
            return compareFractions(model);
        }
        return null;
    }

    private String compareFractions(CalculationModel model) {
        ContinuedFraction a = model.getFractionA();
        ContinuedFraction b = model.getFractionB();
        NumberFormat format = model.getValueFormat();
        int comparison = a.compareTo(b);
        comparison = comparison < 0 ? -1 : comparison > 0 ? 1 : 0;
        return format.format(comparison);
    }

    private String outputFractionValue(CalculationModel model) {
        ContinuedFraction fraction = model.getFraction();
        NumberFormat format = model.getValueFormat();
        return format.format(fraction.doubleValue());
    }

    private String outputFraction(CalculationModel model) {
        Double value = model.getValue();
        int max = model.getMax();
        FractionService service = model.getService();
        FractionFactory factory = service.getFactory(injector);
        ContinuedFraction result;
        result = service.getFactory(injector).fromValue(value, max);
        NumberFormat format = model.getValueFormat();
        FractionFormat fractionFormat = formatFactory.create(factory);
        fractionFormat.setNumberFormat(format);
        return fractionFormat.format(result);
    }

    private CalculationModel parseArgs(String[] args) throws AppException {
        try {
            return parserFactory.create(args).parse();
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
