package com.anrisoftware.fractions.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Implements the list and number methods and calculates the value of this
 * continued fraction.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.3
 */
@SuppressWarnings("serial")
public class AbstractContinuedFraction<Type extends Number> extends Number
		implements ContinuedFraction<Type> {

	private final List<Type> denominators;

	private final double value;

	private final int maxDenominators;

	private final double z;

	/**
	 * Calculates the denominators from the specified value.
	 * 
	 * @param value
	 *            the value.
	 * 
	 * @param z
	 *            the partial numerator for all denominators of this continued
	 *            fraction.
	 * 
	 * @param maxDenominators
	 *            the maximum count of the denominators.
	 */
	protected AbstractContinuedFraction(
			EvaluateFractions<Type> evaluateFractions, double value, double z,
			int maxDenominators) {
		this.value = value;
		this.z = z;
		this.maxDenominators = maxDenominators;
		this.denominators = evaluateFractions.evaluate(value, maxDenominators);
	}

	@Override
	public int getMaxDenominators() {
		return maxDenominators;
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
	public double doubleValue() {
		int lastIndex = size() - 1;
		double x = z / get(lastIndex).doubleValue();
		for (int i = lastIndex - 1; i > 0; i--) {
			x = z / (get(i).doubleValue() + x);
		}
		x = get(0).doubleValue() / z + x;
		return x;
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
	public boolean add(Type e) {
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
	public boolean addAll(Collection<? extends Type> c) {
		return denominators.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Type> c) {
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
	public Type get(int index) {
		return denominators.get(index);
	}

	@Override
	public Type set(int index, Type element) {
		return denominators.set(index, element);
	}

	@Override
	public void add(int index, Type element) {
		denominators.add(index, element);
	}

	@Override
	public Type remove(int index) {
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
	public ListIterator<Type> listIterator() {
		return denominators.listIterator();
	}

	@Override
	public ListIterator<Type> listIterator(int index) {
		return denominators.listIterator(index);
	}

	@Override
	public List<Type> subList(int fromIndex, int toIndex) {
		return denominators.subList(fromIndex, toIndex);
	}

	@Override
	public String toString() {
		return String.format("%s=%s", doubleValue(), denominators.toString());
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
