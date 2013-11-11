/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-integer.
 *
 * fractions-integer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-integer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-integer. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.mod3;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

import javax.inject.Inject;

import com.anrisoftware.fractions.core.AbstractContinuedFraction;
import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.EvaluateFractions;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Mod3 continued fraction.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@SuppressWarnings("serial")
public class Mod3Fraction extends AbstractContinuedFraction {

	private static final double Z_DEFAULT = 1.0;
	private static final int MAX_LIMES = 32766;

	@Inject
	private Mod3FractionFactory factory;

	/**
	 * @see Mod3FractionFactory#create(double, int[])
	 */
	@AssistedInject
	Mod3Fraction(@Assisted double z, @Assisted int[] denos) {
		super(z, denos);
	}

	/**
	 * @see Mod3FractionFactory#fromValue(double, int)
	 */
	@AssistedInject
	Mod3Fraction(final Mod3Round round, @Assisted double value,
			@Assisted int max) {
		super(new EvaluateFractions() {

			@Override
			public int[] evaluate(double value, int max) {
				return evaluateDenos(value, max, round);
			}
		}, value, 1.0, max);
	}

	private static int[] evaluateDenos(double value, int max, Mod3Round round) {
		TIntList denos = new TIntArrayList(max);
		double logvalue = value;
		denos.add(round.round(logvalue));
		return calculateDenos(denos, round, logvalue, Z_DEFAULT, max).toArray();
	}

	private static TIntList calculateDenos(TIntList denos, Mod3Round round,
			double logvalue, double z, int max) {
		int lmax = max;
		if (denos.get(0) % 3 != 0) {
			lmax = 0;
		}
		int nenner = denos.get(0);
		int limes = MAX_LIMES;
		double dn = 0.0;
		int level = 1;
		while (level <= lmax) {
			dn = logvalue - nenner;
			if (Math.abs(dn) <= 1.0 / limes) {
				denos.add(limes);
				lmax = level;
			} else {
				logvalue = z / dn;
				nenner = round.round(logvalue);
				denos.add(nenner);
			}
			level++;
		}
		return denos;
	}

	@Override
	protected ContinuedFraction createFraction(double z, int[] denos) {
		return factory.create(z, denos);
	}
}
