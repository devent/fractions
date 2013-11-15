package com.anrisoftware.fractions.calculator.parser;

import java.text.Format;
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

	private Format valueFormat;

	private FractionService service;

	CalculationArgs() {
		this.valueFormat = NumberFormat.getInstance();
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
	 *            the {@link Format}.
	 */
	public void setValueFormat(Format format) {
		this.valueFormat = format;
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
	 * Parses the value of the continued fraction command line argument.
	 * 
	 * @param value
	 *            the continued fraction value.
	 * 
	 * @throws ArgsException
	 *             if the value could not be parsed for the specified command
	 *             line argument.
	 */
	@Argument(index = 0, required = true)
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
