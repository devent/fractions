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

import gnu.trove.iterator.TIntIterator;

/**
 * Represents a continued fraction.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface ContinuedFraction<FractionType extends ContinuedFraction<FractionType>>
        extends Comparable<FractionType> {

    /**
     * Returns the original value of the continued fraction. That is the value
     * from which the denominators are calculated.
     * 
     * @return the original value.
     */
    double getValue();

    /**
     * Returns the partial numerator for all denominators of this continued
     * fraction.
     * 
     * @return the partial numerator for all denominators.
     */
    double getZ();

    /**
     * Returns the denominator value with the specified index.
     * 
     * @param index
     *            the index in the range of {@code [0,size-1]}.
     * 
     * @return the denominator value.
     * 
     * @throws IndexOutOfBoundsException
     *             if the specified index is negative or greater then
     *             {@code size-1}.
     */
    int get(int index);

    /**
     * Replaces the demoninator with the specified index.
     * 
     * @param index
     *            the index in the range of {@code [0,size-1]}.
     * 
     * @param value
     *            the denominator value.
     * 
     * @return the {@link ContinuedFraction} with the replaced denominator.
     * 
     * @throws IndexOutOfBoundsException
     *             if the specified index is negative or greater then
     *             {@code size-1}.
     */
    ContinuedFraction<FractionType> set(int index, int value);

    /**
     * Expands the continued fraction by the specified denominator.
     * 
     * @param denominator
     *            the denominator.
     * 
     * @return the expanded {@link ContinuedFraction}.
     */
    ContinuedFraction<FractionType> expand(int denominator);

    /**
     * Contracts the continued fraction removing the last denominator.
     * 
     * @return the contracted {@link ContinuedFraction}.
     */
    ContinuedFraction<FractionType> contract();

    /**
     * Contracts the continued fraction removing the last n denominators.
     * 
     * @param n
     *            how many denominators should be removed.
     * 
     * @return the contracted {@link ContinuedFraction}.
     * 
     * @since 2.2
     */
    ContinuedFraction<FractionType> contract(int n);

    /**
     * Returns if the continued fraction does not have any denominators.
     * 
     * @return {@code true} if {@code size = 0}.
     */
    boolean isEmpty();

    /**
     * Returns the numbers of denominators.
     * 
     * @return the size.
     */
    int size();

    /**
     * Returns the denominators in an integer array.
     * 
     * @return the denominators array.
     */
    int[] toArray();

    /**
     * Returns the denominators in an integer array.
     * 
     * @param array
     *            the array in which the denominator values are set.
     * 
     * @return the denominators array.
     * 
     * @throws NullPointerException
     *             if the specified array is {@code null}.
     */
    int[] toArray(int[] array);

    /**
     * Returns an iterator over the denominator values.
     * 
     * @return the {@link TIntIterator}.
     */
    TIntIterator iterator();

    /**
     * Returns a calculated value from this continued fraction.
     * 
     * @return the float value.
     */
    float floatValue();

    /**
     * Returns a calculated value from this continued fraction.
     * 
     * @return the double value.
     */
    double doubleValue();

    /**
     * Returns a calculated value from this continued fraction.
     * 
     * @return the integer value.
     */
    int intValue();

    /**
     * Returns a calculated value from this continued fraction.
     * 
     * @return the long integer value.
     */
    long longValue();

    /**
     * Compare this continued fractions with the specified object on equality.
     * 
     * @param obj
     *            the {@link Object} to compare.
     * 
     * @return {@code true} if the object is a continued fraction and equals
     *         this continued fraction.
     */
    @Override
    boolean equals(Object obj);

}
