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
package com.anrisoftware.fractions.calculator.parser;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.inject.Inject;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import com.anrisoftware.fractions.calculator.model.CalculationModel;
import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.FractionFactory;
import com.anrisoftware.fractions.core.FractionService;
import com.anrisoftware.fractions.format.FractionFormat;
import com.anrisoftware.fractions.format.FractionFormatFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

/**
 * Parses the command line arguments.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class CalculationParser implements CalculationModel {

    private static final String DEFAULT_SERVICE = "IntegerFraction";

    private final String[] args;

    @Inject
    private CalculationParserLogger log;

    @Inject
    private CalculationArgs model;

    @Inject
    private Injector injector;

    @Inject
    private FractionFormatFactory fractionFormatFactory;

    private FindFractionService findService;

    private FractionService service;

    private Double value;

    private ContinuedFraction fraction;

    private ContinuedFraction fractionA;

    private ContinuedFraction fractionB;

    private NumberFormat valueFormat;

    /**
     * @see CalculationParserFactory#create(String[])
     */
    @Inject
    CalculationParser(@Assisted String[] args) {
        this.args = args;
    }

    @Inject
    void setDefaultFractionService(FindFractionService findService)
            throws ArgsException {
        this.findService = findService;
        this.service = findService.findService(DEFAULT_SERVICE);
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
            parseArgs();
            varifyArgs();
            return this;
        } catch (CmdLineException e) {
            throw log.errorParse(e, args);
        }
    }

    private void varifyArgs() throws ArgsException {
        if (value != null && fraction != null) {
            throw log.errorBothValueFraction(value, fraction);
        }
    }

    private void parseArgs() throws ArgsException {
        this.valueFormat = parseValueFormat(model.getValueFormat());
        this.value = parseValue(model.getValue());
        this.service = parseService(model.getService());
        this.fraction = parseFraction(model.getDenominators());
        this.fractionA = parseFraction(model.getFractionA());
        this.fractionB = parseFraction(model.getFractionB());
    }

    private NumberFormat parseValueFormat(String format) {
        if (format == null) {
            return this.valueFormat;
        } else {
            return new DecimalFormat(format);
        }
    }

    private FractionService parseService(String service) throws ArgsException {
        if (service == null) {
            return this.service;
        } else {
            return findService.findService(service);
        }
    }

    private Double parseValue(String value) throws ArgsException {
        if (value == null) {
            return null;
        }
        try {
            Number number = (Number) valueFormat.parseObject(value);
            return number.doubleValue();
        } catch (ParseException e) {
            throw log.errorParseValue(e, value);
        }
    }

    private ContinuedFraction parseFraction(String deno)
            throws ArgsException {
        if (deno == null) {
            return null;
        }
        try {
            FractionFactory factory = service.getFactory(injector);
            FractionFormat format = fractionFormatFactory.create(factory);
            ContinuedFraction v = format.parse(deno);
            return v;
        } catch (ParseException e) {
            throw log.errorParseDeno(e, deno);
        }
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public int getMax() {
        return model.getMax();
    }

    @Override
    public ContinuedFraction getFraction() {
        return fraction;
    }

    @Override
    public ContinuedFraction getFractionA() {
        return fractionA;
    }

    @Override
    public ContinuedFraction getFractionB() {
        return fractionB;
    }

    @Override
    public NumberFormat getValueFormat() {
        return valueFormat;
    }

    @Override
    public FractionService getService() {
        return service;
    }
}
