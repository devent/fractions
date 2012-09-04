/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of fractions-core. All rights reserved.
 */
package com.anrisoftware.fractions.core;

import java.util.List;

/**
 * Represents a continued fraction with the denominators as {@link Number}s. You
 * can use the continued fraction as a {@link List} to access the denominators.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
public interface ContinuedFraction<Type extends Number> extends List<Type> {

	/**
	 * Returns the original value of this continued fraction.
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
	Type getZ();

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

}
