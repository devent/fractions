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
package com.anrisoftware.fractions.calculator.model;

import java.text.NumberFormat;

import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.FractionService;

/**
 * Calculation model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface CalculationModel {

    /**
     * Returns the value of the continued fraction.
     * 
     * @return the {@link Double} value or {@code null}.
     */
    Double getValue();

    /**
     * Returns the maximum denominators for the continued fractions.
     * 
     * @return the maximum denominators.
     */
    int getMax();

    /**
     * Returns the fraction service.
     * 
     * @return the {@link FractionService}.
     */
    FractionService getService();

    /**
     * Returns the the continued fraction to calculate a value from.
     * 
     * @return the {@link ContinuedFraction} or {@code null}.
     */
    ContinuedFraction getFraction();

    /**
     * Returns the first continued fraction for comparison.
     * 
     * @return the {@link ContinuedFraction} or {@code null}.
     */
    ContinuedFraction getFractionA();

    /**
     * Returns the second continued fraction for comparison.
     * 
     * @return the {@link ContinuedFraction} or {@code null}.
     */
    ContinuedFraction getFractionB();

    /**
     * Returns the value number formatter.
     * 
     * @return the {@link NumberFormat}.
     */
    NumberFormat getValueFormat();
}
