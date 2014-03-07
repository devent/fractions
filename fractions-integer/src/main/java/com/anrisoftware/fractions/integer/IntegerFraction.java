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
package com.anrisoftware.fractions.integer;

import static com.anrisoftware.globalpom.math.MathUtils.fix;
import static org.apache.commons.math3.util.FastMath.abs;
import static org.apache.commons.math3.util.FastMath.log;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

import javax.inject.Inject;

import com.anrisoftware.fractions.core.AbstractContinuedFraction;
import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.EvaluateFractions;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Continued fraction with integer number denominators.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@SuppressWarnings("serial")
public final class IntegerFraction extends AbstractContinuedFraction {

    @Inject
    private IntegerFractionFactory factory;

    /**
     * @see IntegerFractionFactory#create(double, int[])
     */
    @AssistedInject
    IntegerFraction(@Assisted double z, @Assisted int[] denos) {
        super(z, denos);
    }

    /**
     * @see IntegerFractionFactory#fromValue(double, int)
     */
    @AssistedInject
    IntegerFraction(@Assisted double value, @Assisted int max) {
        super(new EvaluateFractions() {

            @Override
            public int[] evaluate(double value, int max) {
                TIntList denos = new TIntArrayList(max);
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
        }, value, 1.0, max);
    }

    @Override
    protected ContinuedFraction createFraction(double z, int[] denos) {
        return factory.create(z, denos);
    }
}
