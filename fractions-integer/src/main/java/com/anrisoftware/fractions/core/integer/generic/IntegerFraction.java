/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-integer. All rights reserved.
 */
package com.anrisoftware.fractions.core.integer.generic;

import static com.anrisoftware.fractions.core.integer.generic.MathUtils.fix;
import static org.apache.commons.math3.util.FastMath.abs;
import static org.apache.commons.math3.util.FastMath.log;

import java.util.ArrayList;
import java.util.List;

import com.anrisoftware.fractions.core.AbstractContinuedFraction;
import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.EvaluateFractions;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Continued fraction with integer number denominators.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
class IntegerFraction extends AbstractContinuedFraction<Integer> implements
		ContinuedFraction<Integer> {

	/**
	 * Calculates the denominators from the specified value.
	 * 
	 * @param value
	 *            the value.
	 * 
	 * @param maxDenominators
	 *            the maximum count of the denominators.
	 */
	@AssistedInject
	IntegerFraction(@Assisted double value, @Assisted int maxDenominators) {
		super(new EvaluateFractions<Integer>() {

			@Override
			public List<Integer> evaluate(double value, int maxDenominators) {
				List<Integer> denos = new ArrayList<Integer>();

				int k = 1;
				double y = fix(value);
				double r = value - y;
				if (r > 0.5) {
					r = r - 1.0;
					y = y + 1.0;
				}
				denos.add((int) y);
				double relativeError = abs(r / value);
				double s;

				while (log(relativeError) > -16.0 && k < maxDenominators) {
					k++;
					s = 1 / r;
					y = fix(s);
					r = s - y;
					if (r > 0.5) {
						r -= 1.0;
						y += 1.0;
					} else if (r < -0.5) {
						r += 1.0;
						y -= 1.0;
					}
					relativeError = abs(r / value);
					denos.add((int) y);
				}

				return denos;
			}
		}, value, 1, maxDenominators);
	}

}
