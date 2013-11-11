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
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Implements denominator methods and calculates the value of this continued
 * fraction.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@SuppressWarnings("serial")
public abstract class AbstractContinuedFraction extends Number implements
		ContinuedFraction {

	private final TIntList denominators;

	private final double value;

	private final double z;

	private final double fractionValue;

	/**
	 * Sets the partial numerator and the denominators.
	 *
	 * @param z
	 *            the partial numerator for all denominators of this continued
	 *            fraction.
	 *
	 * @param denos
	 *            the array containing the denominator values.
	 */
	protected AbstractContinuedFraction(double z, int[] denos) {
		this.z = z;
		this.denominators = new TIntArrayList(denos);
		this.fractionValue = calculateValue();
		this.value = fractionValue;
	}

	/**
	 * Calculates the denominators from the specified value.
	 *
	 * @param evaluate
	 *            the {@link EvaluateFractions} that calculated the denominators
	 *            of the continued fraction.
	 *
	 * @param value
	 *            the value.
	 *
	 * @param z
	 *            the partial numerator for all denominators of this continued
	 *            fraction.
	 *
	 * @param max
	 *            the maximum count of the denominators.
	 */
	protected AbstractContinuedFraction(EvaluateFractions evaluate,
			double value, double z, int max) {
		this.value = value;
		this.z = z;
		this.denominators = new TIntArrayList(evaluate.evaluate(value, max));
		this.fractionValue = calculateValue();
	}

	private double calculateValue() {
		double z = this.z;
		int lastIndex = size() - 1;
		double x = z / get(lastIndex);
		for (int i = lastIndex - 1; i > 0; i--) {
			x = z / (get(i) + x);
		}
		x = get(0) / z + x;
		return x;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public double getZ() {
		return z;
	}

	@Override
	public int get(int index) {
		return denominators.get(index);
	}

	@Override
	public ContinuedFraction set(int index, int value) {
		TIntArrayList denos = new TIntArrayList(denominators);
		denos.set(index, value);
		return createFraction(getZ(), denos.toArray());
	}

	@Override
	public int size() {
		return denominators.size();
	}

	@Override
	public boolean isEmpty() {
		return denominators.isEmpty();
	}

	@Override
	public int[] toArray() {
		return denominators.toArray();
	}

	@Override
	public int[] toArray(int[] a) {
		return denominators.toArray(a);
	}

	@Override
	public ContinuedFraction expand(int denominator) {
		TIntArrayList denos = new TIntArrayList(denominators);
		denos.add(denominator);
		return createFraction(getZ(), denos.toArray());
	}

	@Override
	public ContinuedFraction contract() {
		TIntList denos = denominators.subList(0, size() - 1);
		return createFraction(getZ(), denos.toArray());
	}

	@Override
	public TIntIterator iterator() {
		return denominators.iterator();
	}

	@Override
	public double doubleValue() {
		return fractionValue;
	}

	@Override
	public float floatValue() {
		return (float) doubleValue();
	}

	@Override
	public int intValue() {
		return (int) doubleValue();
	}

	@Override
	public long longValue() {
		return (long) doubleValue();
	}

	/**
	 * Creates the continued fraction with the spezified partial numerator and
	 * denominators.
	 *
	 * @param z
	 *            the partial numerator for all denominators.
	 *
	 * @param denos
	 *            the array containing the denominator values.
	 *
	 * @return the {@link ContinuedFraction}.
	 */
	protected abstract ContinuedFraction createFraction(double z, int[] denos);

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(doubleValue()).append("=[");
		appendZ(b);
		appendN0(b);
		appendDenos(b).append("]");
		return b.toString();
	}

	private StringBuilder appendN0(StringBuilder b) {
		if (!isEmpty()) {
			b.append(get(0)).append(";");
		}
		return b;
	}

	private StringBuilder appendDenos(StringBuilder b) {
		int s = size();
		int s1 = s - 1;
		int i;
		for (i = 1; i < s1; i++) {
			b.append(get(i)).append(",");
		}
		if (i < s) {
			b.append(get(s1));
		}
		return b;
	}

	private StringBuilder appendZ(StringBuilder b) {
		double z = getZ();
		if (z != 1.0) {
			b.append(z).append(";");
		}
		return b;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ContinuedFraction)) {
			return false;
		}
		ContinuedFraction rhs = (ContinuedFraction) obj;
		return new EqualsBuilder().append(getZ(), rhs.getZ())
				.append(toArray(), rhs.toArray()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getZ()).append(toArray())
				.hashCode();
	}

}
