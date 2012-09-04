/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of fractions-integer. All rights reserved.
 */
package com.anrisoftware.fractions.core.integer;

import static com.anrisoftware.fractions.core.integer.MathUtils.fix;
import static java.util.Collections.unmodifiableList;
import static org.apache.commons.math3.util.FastMath.abs;
import static org.apache.commons.math3.util.FastMath.log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.anrisoftware.fractions.core.ContinuedFraction;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

class IntegerFraction extends Number implements ContinuedFraction<Number> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9017458577046687007L;

	private final List<Number> denominators;

	private final double value;

	private final int maxDenominators;

	@AssistedInject
	IntegerFraction(@Assisted double value, @Assisted Number z,
			@Assisted int maxDenominators) {
		this.value = value;
		this.maxDenominators = maxDenominators;
		this.denominators = unmodifiableList(evaluateFraction());
	}

	private List<Number> evaluateFraction() {
		List<Number> denos = new ArrayList<Number>();

		int k = 1;
		double y = fix(value);
		double r = value - y;
		if (r > 0.5) {
			r = r - 1.0;
			y = y + 1.0;
		}
		denos.add(y);
		double e = abs(r / value);

		while (log(e) > -16.0 && k < maxDenominators) {
			k++;
			double s = 1 / r;
			y = fix(s);
			r = s - y;
			if (r > 0.5) {
				r -= 1.0;
				y += 1.0;
			} else if (r < -0.5) {
				r += 1.0;
				y -= 1.0;
			}
			e = abs(r / value);
			denos.add(y);
		}

		return denos;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public Number getZ() {
		return 1.0;
	}

	@Override
	public double doubleValue() {
		int lastIndex = size() - 1;
		double z = getZ().doubleValue();
		double x = z / get(lastIndex).doubleValue();
		for (int i = lastIndex - 1; i > 0; i--) {
			x = z / (get(i).doubleValue() + x);
		}
		x = get(0).doubleValue() / z + x;
		return x;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
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
	public boolean contains(Object o) {
		return denominators.contains(o);
	}

	@Override
	public Iterator<Number> iterator() {
		return denominators.iterator();
	}

	@Override
	public Object[] toArray() {
		return denominators.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return denominators.toArray(a);
	}

	@Override
	public boolean add(Number e) {
		return denominators.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return denominators.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return denominators.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Number> c) {
		return denominators.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Number> c) {
		return denominators.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return denominators.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return denominators.retainAll(c);
	}

	@Override
	public void clear() {
		denominators.clear();
	}

	@Override
	public Number get(int index) {
		return denominators.get(index);
	}

	@Override
	public Number set(int index, Number element) {
		return denominators.set(index, element);
	}

	@Override
	public void add(int index, Number element) {
		denominators.add(index, element);
	}

	@Override
	public Number remove(int index) {
		return denominators.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return denominators.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return denominators.lastIndexOf(o);
	}

	@Override
	public ListIterator<Number> listIterator() {
		return denominators.listIterator();
	}

	@Override
	public ListIterator<Number> listIterator(int index) {
		return denominators.listIterator(index);
	}

	@Override
	public List<Number> subList(int fromIndex, int toIndex) {
		return denominators.subList(fromIndex, toIndex);
	}

	@Override
	public String toString() {
		return String.format("%s=[%s]", doubleValue(), denominators.toString());
	}

	@Override
	public boolean equals(Object o) {
		return denominators.equals(o);
	}

	@Override
	public int hashCode() {
		return denominators.hashCode();
	}

}
