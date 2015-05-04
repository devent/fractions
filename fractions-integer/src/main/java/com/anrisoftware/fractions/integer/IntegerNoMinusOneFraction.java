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
package com.anrisoftware.fractions.integer;

import static com.anrisoftware.globalpom.math.MathUtils.fix;
import static org.apache.commons.math3.util.FastMath.abs;
import static org.apache.commons.math3.util.FastMath.log;
import static org.apache.commons.math3.util.FastMath.round;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

import javax.inject.Inject;

import com.anrisoftware.fractions.core.AbstractContinuedFraction;
import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.EvaluateFractions;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Continued fraction with integer number denominators. The denominators of this
 * continued fraction cannot be of value -1. The partial numerator for all
 * denominators of this continued fraction will be 1.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@SuppressWarnings("serial")
public final class IntegerNoMinusOneFraction extends AbstractContinuedFraction {

    @Inject
    private IntegerNoMinusOneFractionFactory factory;

    /**
     * @see IntegerNoMinusOneFractionFactory#create(double, int[])
     */
    @AssistedInject
    IntegerNoMinusOneFraction(@Assisted double z, @Assisted int[] denos) {
        super(z, denos);
    }

    /**
     * @see IntegerNoMinusOneFractionFactory#fromValue(double, int)
     */
    @AssistedInject
    IntegerNoMinusOneFraction(@Assisted double value, @Assisted int max) {
        this(value, 1.0, max);
    }

    /**
     * @see IntegerNoMinusOneFractionFactory#fromValue(double, double, int)
     *
     * @since 2.7
     */
    @AssistedInject
    IntegerNoMinusOneFraction(@Assisted("value") final double value,
            @Assisted("z") final double z, @Assisted final int max) {
        super(createEvaluateFractions(), value, z, max);
    }

    /**
     * @see IntegerNoMinusOneFractionFactory#fromValue(double, int, int)
     *
     * @since 2.7
     */
    @AssistedInject
    IntegerNoMinusOneFraction(@Assisted("value") double value,
            @Assisted("d0") int d0, @Assisted("max") int max) {
        this(value, 1.0, d0, max);
    }

    /**
     * @see IntegerNoMinusOneFractionFactory#fromValue(double, int, int)
     *
     * @since 2.7
     */
    @AssistedInject
    IntegerNoMinusOneFraction(@Assisted("value") double value,
            @Assisted("z") double z, @Assisted("d0") int d0,
            @Assisted("max") int max) {
        super(createEvaluateFractions(), value, z, d0, max);
    }

    private static EvaluateFractions createEvaluateFractions() {
        return new EvaluateFractions() {

            @Override
            public int[] evaluate(double value, double z, int max) {
                TIntList denos = new TIntArrayList(max);
                double y = round(value);
                double r = value - y;
                if (r > 0.5) {
                    r = r - 1.0;
                    y = y + 1.0;
                }
                denos.add((int) y);
                return evaluate0(value, max, denos, y, r);
            }

            @Override
            public int[] evaluate(double value, double z, int d0, int max) {
                TIntList denos = new TIntArrayList(max);
                double y = d0;
                double r = value - y;
                if (r > 0.5) {
                    r = r - 1.0;
                    y = y + 1.0;
                }
                denos.add(d0);
                return evaluate0(value, max, denos, y, r);
            }

        };
    }

    private static int[] evaluate0(final double value, final int max,
            TIntList denos, double y, double r) {
        double relativeError = abs(r / value);
        double s;
        int k = 1;
        while (log(relativeError) > -16.0 && k < max) {
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
        return denos.toArray();
    }

    @Override
    protected ContinuedFraction createFraction(double z, int[] denos) {
        return factory.create(z, denos);
    }
}
