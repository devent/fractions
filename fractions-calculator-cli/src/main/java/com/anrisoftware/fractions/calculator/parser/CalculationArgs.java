package com.anrisoftware.fractions.calculator.parser;

import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.inject.Inject;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import com.anrisoftware.fractions.calculator.model.CalculationModel;
import com.anrisoftware.fractions.core.FractionFactory;
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
	private FindFractionService findService;

	@Inject
	private Injector injector;

	@Option(name = "-service", required = false)
	private final String service;

	@Argument(index = 0, required = true)
	private String value;

	private Format valueFormat;

	CalculationArgs() {
		this.valueFormat = NumberFormat.getInstance();
		this.service = DEFAULT_SERVICE;
	}

	/**
	 * Sets the format to parse the continued fraction value.
	 * 
	 * @param format
	 *            the {@link Format}.
	 */
	public void setValueFormat(Format format) {
		this.valueFormat = format;
	}

	/**
	 * @throws ArgsException
	 *             if the value could not be parsed for the specified command
	 *             line argument.
	 */
	@Override
	public double getValue() {
		try {
			return ((Number) valueFormat.parseObject(value)).doubleValue();
		} catch (ParseException e) {
			throw log.errorParseValue(e, value);
		}
	}

	/**
	 * @throws ArgsException
	 *             if the continued fraction service could not be found for the
	 *             specified command line argument.
	 */
	@Override
	public FractionFactory getFractionFactory() {
		return findService.findService(service).getFactory(injector);
	}

}
