/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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
public final class Mod3Fraction extends AbstractContinuedFraction {

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
    Mod3Fraction(Mod3Round round, @Assisted double value, @Assisted int max) {
        this(round, value, Z_DEFAULT, max);
    }

    /**
     * @see Mod3FractionFactory#fromValue(double, double, int)
     *
     * @since 2.7
     */
    @AssistedInject
    Mod3Fraction(Mod3Round round, @Assisted("value") double value,
            @Assisted("z") double z, @Assisted int max) {
        super(createEvaluateFractions(round), value, z, max);
    }

    /**
     * @see Mod3FractionFactory#fromValue(double, int, int)
     *
     * @since 2.7
     */
    @AssistedInject
    Mod3Fraction(Mod3Round round, @Assisted("value") double value,
            @Assisted("d0") int d0, @Assisted("max") int max) {
        this(round, value, Z_DEFAULT, d0, max);
    }

    /**
     * @see Mod3FractionFactory#fromValue(double, double, int, int)
     *
     * @since 2.7
     */
    @AssistedInject
    Mod3Fraction(Mod3Round round, @Assisted("value") double value,
            @Assisted("z") double z, @Assisted("d0") int d0,
            @Assisted("max") int max) {
        super(createEvaluateFractions(round), value, z, d0, max);
    }

    private static EvaluateFractions createEvaluateFractions(
            final Mod3Round round) {
        return new EvaluateFractions() {

            @Override
            public int[] evaluate(double value, double z, int max) {
                return evaluateDenos(value, z, max, round);
            }

            @Override
            public int[] evaluate(double value, double z, int d0, int max) {
                return evaluateDenos(value, z, d0, max, round);
            }
        };
    }

    private static int[] evaluateDenos(final double value, final double z,
            final int max, Mod3Round round) {
        TIntList denos = new TIntArrayList(max);
        double logvalue = value;
        denos.add(round.round(logvalue));
        return calculateDenos(denos, round, logvalue, z, max).toArray();
    }

    private static int[] evaluateDenos(final double value, final double z,
            final int d0, final int max, Mod3Round round) {
        TIntList denos = new TIntArrayList(max);
        double logvalue = value;
        denos.add(d0);
        return calculateDenos(denos, round, logvalue, z, max).toArray();
    }

    private static TIntList calculateDenos(TIntList denos, Mod3Round round,
            double logvalue, double z, int max) {
        int lmax = max;
        int n0 = denos.get(0);
        if (n0 % 3 != 0) {
            lmax = 0;
        }
        int nenner = n0;
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
