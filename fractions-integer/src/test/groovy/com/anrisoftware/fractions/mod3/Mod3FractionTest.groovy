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
package com.anrisoftware.fractions.mod3

import static org.apache.commons.math3.util.FastMath.*
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see Mod3ContinuedFraction
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@Slf4j
class Mod3FractionTest {

    @Test
    void "calculate continued fractions"() {
        int max = 9
        double z = 1.0
        inputs.eachWithIndex { value, i ->
            int[] denos = outputsZ1[i] as int[]
            def fraction = factory.fromValue(value, max)
            log.info "{}. value: {} fraction: {}", i, value, fraction
            assert outputsZ1[i].size() == fraction.size()
            if (!fraction.equals(factory.create(z, denos))) {
                log.warn "{}. fraction: {}; expected denominators: {}", i, fraction, outputsZ1[i]
            }
        }
    }

    @Test
    void "calculate continued fractions with specified n0"() {
        int max = 9
        n0_cases.eachWithIndex { it, i ->
            int[] expectedDenos = it.denos
            def fraction = factory.fromValue(it.value, it.z, it.n0 as int, max)
            log.info "{}. z: {} n0: {} value: {} fraction: {}", i, it.z, it.n0, it.value, fraction
            assert expectedDenos.length == fraction.size()
            assert fraction.equals(factory.create(it.z, expectedDenos))
        }
    }

    static inputs = new DataInputs().run()

    static n0_cases = new n0_cases().run()

    static outputsZ1 = new Mod3FractionsZ1Data().run()

    static Injector injector

    static Mod3FractionFactory factory

    @BeforeClass
    static void beforeTest() {
        injector = createInjector()
        factory = injector.getInstance Mod3FractionFactory
    }

    static Injector createInjector() {
        Guice.createInjector new Mod3FractionsModule()
    }
}
