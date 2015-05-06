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

import javax.inject.Inject;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 * Parses command line arguments.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
class CalculationArgs {

    @Inject
    private CalculationArgsLogger log;

    private int max;

    private Integer d0value;

    private String value;

    private String valueFormat;

    private String service;

    private String denominators;

    private String fractionA;

    private String fractionB;

    CalculationArgs() {
        this.valueFormat = "#.#########";
        this.max = 10;
        this.d0value = null;
        this.denominators = null;
        this.fractionA = null;
        this.fractionB = null;
    }

    @Option(name = "-value-format", aliases = { "-f" }, required = false, metaVar = "FORMAT")
    public void setValueFormat(String format) {
        this.valueFormat = format;
        log.valueFormatSet(format);
    }

    public String getValueFormat() {
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
    public void setService(String service) {
        this.service = service;
        log.serviceSet(service);
    }

    public String getService() {
        return service;
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

    public int getMax() {
        return max;
    }

    /**
     * Sets the denominator n0 of the continued fraction from the command line
     * argument.
     *
     * @param d0value
     *            the denominator n0 {@link Integer} value.
     */
    @Option(name = "-d0", required = false)
    public void setD0value(Integer d0value) {
        this.d0value = d0value;
    }

    /**
     * Returns the denominator n0 of the continued fraction.
     *
     * @return the denominator n0 {@link Integer} value or {@code null}.
     */
    public Integer getD0value() {
        return d0value;
    }

    /**
     * Sets the denominators of the continued fraction from the command line
     * argument.
     *
     * @param denominators
     *            the denominators.
     */
    @Option(name = "-denominators", aliases = { "-d" }, required = false)
    public void setDenominators(String denominators) {
        this.denominators = denominators;
    }

    public String getDenominators() {
        return denominators;
    }

    /**
     * Sets the denominators of the first continued fraction from the command
     * line argument.
     *
     * @param denominators
     *            the fraction.
     */
    @Option(name = "-fraction-a", aliases = { "-a" }, required = false)
    public void setFractionA(String denominators) {
        this.fractionA = denominators;
    }

    public String getFractionA() {
        return fractionA;
    }

    /**
     * Sets the denominators of the second continued fraction from the command
     * line argument.
     *
     * @param denominators
     *            the fraction.
     */
    @Option(name = "-fraction-b", aliases = { "-b" }, required = false)
    public void setFractionB(String denominators) {
        this.fractionB = denominators;
    }

    public String getFractionB() {
        return fractionB;
    }

    /**
     * Sets the value of the continued fraction command line argument.
     *
     * @param value
     *            the continued fraction value.
     */
    @Argument(index = 0, required = false)
    public void setValue(String value) {
        this.value = value;
        log.valueSet(value);
    }

    public String getValue() {
        return value;
    }

}
