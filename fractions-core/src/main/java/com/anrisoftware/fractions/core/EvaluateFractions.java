/*
 * Copyright 2011-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-core.
 *
 * fractions-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.core;

/**
 * Calculate the denominators of a continued fraction from the value.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface EvaluateFractions {

    /**
     * Calculate the denominators of a continued fraction from the specified
     * value.
     * 
     * @param value
     *            the value.
     * 
     * @param max
     *            the maximum count of denominators for the continued fraction.
     * 
     * @return the denominators.
     */
    int[] evaluate(double value, int max);
}
