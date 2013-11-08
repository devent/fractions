/*
 * Copyright 2011-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-mod3.
 *
 * fractions-mod3 is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-mod3 is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-mod3. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.fractions.mod3.internal;

import static java.util.Collections.unmodifiableList;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.antlr.stringtemplate.StringTemplate;

import com.globalscalingsoftware.fractions.api.ContinuedFraction;

/**
 * Base implementation of an {@link ContinuedFraction}. Holds the denominators
 * in a {@link List} implementation and can calculate the value of the continued
 * fraction.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
@SuppressWarnings("serial")
abstract class AbstractContinuedFraction<Type extends Number> extends Number
		implements ContinuedFraction<Type> {

	protected final List<Type> denominators;

	/**
	 * Use the given values from the {@link List} as the denominators of the
	 * continued fraction.
	 */
	protected AbstractContinuedFraction(List<Type> denominators) {
		this.denominators = denominators;
	}

	@Override
	public double getValue() {
		return doubleValue();
	}

	@Override
	public float floatValue() {
		int lastIndex = size() - 1;
		float z = getZ();
		float x = z / get(lastIndex).floatValue();
		for (int i = lastIndex - 1; i > 0; i--) {
			x = z / (get(i).floatValue() + x);
		}
		x = get(0).floatValue() / z + x;
		return x;
	}

	@Override
	public int intValue() {
		return (int) floatValue();
	}

	@Override
	public long longValue() {
		return (long) doubleValue();
	}

	@Override
	public double doubleValue() {
		int lastIndex = size() - 1;
		double z = getZ();
		double x = z / get(lastIndex).doubleValue();
		for (int i = lastIndex - 1; i > 0; i--) {
			x = z / (get(i).doubleValue() + x);
		}
		x = get(0).doubleValue() / z + x;
		return x;
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
	public Iterator<Type> iterator() {
		return Collections.unmodifiableList(denominators).iterator();
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
	public boolean add(Type e) {
		throw createReadOnlyException();
	}

	@Override
	public boolean remove(Object o) {
		throw createReadOnlyException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return denominators.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Type> c) {
		throw createReadOnlyException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends Type> c) {
		throw createReadOnlyException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw createReadOnlyException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return denominators.retainAll(c);
	}

	@Override
	public void clear() {
		throw createReadOnlyException();
	}

	@Override
	public boolean equals(Object o) {
		return denominators.equals(o);
	}

	@Override
	public int hashCode() {
		return denominators.hashCode();
	}

	@Override
	public Type get(int index) {
		return denominators.get(index);
	}

	@Override
	public Type set(int index, Type element) {
		throw createReadOnlyException();
	}

	@Override
	public void add(int index, Type element) {
		throw createReadOnlyException();
	}

	@Override
	public Type remove(int index) {
		throw createReadOnlyException();
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
	public ListIterator<Type> listIterator() {
		return unmodifiableList(denominators).listIterator();
	}

	@Override
	public ListIterator<Type> listIterator(int index) {
		return unmodifiableList(denominators).listIterator(index);
	}

	@Override
	public List<Type> subList(int fromIndex, int toIndex) {
		return unmodifiableList(denominators.subList(fromIndex, toIndex));
	}

	private UnsupportedOperationException createReadOnlyException() {
		return new UnsupportedOperationException(
				"The continued fractions are read only.");
	}

	@Override
	public String toString() {
		String template = "[$first(denos):{$it$}$;$rest(denos);separator=\",\"$]";
		StringTemplate str = new StringTemplate(template);
		str.setAttribute("denos", this);
		return str.toString();
	}
}
