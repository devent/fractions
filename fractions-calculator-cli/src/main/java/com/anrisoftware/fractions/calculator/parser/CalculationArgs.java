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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.inject.Inject;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import com.anrisoftware.fractions.calculator.model.CalculationModel;
import com.anrisoftware.fractions.core.FractionFactory;
import com.anrisoftware.fractions.core.FractionService;
import com.google.inject.Injector;

/**
 * Parses command line arguments.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
class CalculationArgs implements CalculationModel {

	private static final String DEFAULT_SERVICE = "IntegerFraction";

	@Inject
	private CalculationArgsLogger log;

	@Inject
	private Injector injector;

	private FindFractionService findService;

	private Integer max;

	private double value;

	private NumberFormat valueFormat;

	private FractionService service;

	private String denominators;

	CalculationArgs() {
		this.valueFormat = NumberFormat.getInstance();
		this.max = 10;
		this.denominators = null;
	}

	@Inject
	void setDefaultFractionService(FindFractionService findService)
			throws ArgsException {
		this.findService = findService;
		this.service = findService.findService(DEFAULT_SERVICE);
	}

	/**
	 * Sets the format to parse the continued fraction value.
	 * 
	 * @param format
	 *            the format patterns.
	 */
	@Option(name = "-value-format", required = false, metaVar = "FORMAT")
	public void setValueFormat(String format) {
		this.valueFormat = new DecimalFormat(format);
	}

	@Override
	public NumberFormat getValueFormat() {
		return valueFormat;
	}

	/**
	 * Parses the service command line argument.
	 * 
	 * @param service
	 *            the service name.
	 * 
	 * @throws ArgsException
	 *             if the continued fraction service could not be found for the
	 *             specified command line argument.
	 */
	@Option(name = "-service", required = false)
	public void setService(String service) throws ArgsException {
		this.service = findService.findService(service);
		log.serviceSet(service);
	}

	@Override
	public FractionFactory getFractionFactory() {
		return service.getFactory(injector);
	}

	/**
	 * Parses the maximum denominators command line argument.
	 * 
	 * @param max
	 *            the maximum denominators.
	 */
	@Option(name = "-max", required = false)
	public void setMax(int max) {
		this.max = max;
		log.maxSet(max);
	}

	@Override
	public int getMax() {
		return max;
	}

	/**
	 * Sets the denominators of the continued fraction from the command line
	 * argument.
	 * 
	 * @param denominators
	 *            the denominators.
	 */
	@Option(name = "-denominators", required = false)
	public void setDenominators(String denominators) {
		this.denominators = denominators;
	}

	@Override
	public String getDenominators() {
		return denominators;
	}

	/**
	 * Parses the value of the continued fraction command line argument.
	 * 
	 * @param value
	 *            the continued fraction value.
	 * 
	 * @throws ArgsException
	 *             if the value could not be parsed for the specified command
	 *             line argument.
	 */
	@Argument(index = 0, required = false)
	public void setValue(String value) throws ArgsException {
		try {
			Number number = (Number) valueFormat.parseObject(value);
			this.value = number.doubleValue();
			log.valueSet(value);
		} catch (ParseException e) {
			throw log.errorParseValue(e, value);
		}
	}

	@Override
	public double getValue() {
		return value;
	}

}
