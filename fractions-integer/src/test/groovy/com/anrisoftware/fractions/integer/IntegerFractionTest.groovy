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
package com.anrisoftware.fractions.integer

import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see IntegerFraction
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Slf4j
class IntegerFractionTest {

    @Test
    void "calculate continued fractions"() {
        int max = 9
        double z = 1.0
        inputs.eachWithIndex { double value, i ->
            int[] denos = outputs[i] as int[]
            def fraction = factory.fromValue(value, max)
            log.info "{}. fraction: {}", i, fraction
            assert outputs[i].size() == fraction.size()
            if (!fraction.equals(factory.create(z, denos))) {
                log.warn "{}. fraction: {}; expected denominators: {}", i, fraction, outputs[i]
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

    @Test
    void "calculate continued fractions with d0"() {
        int max = 9
        double value = 6
        int d0 = 0
        def fractiond0 = factory.fromValue(value, d0, max)
        log.info "fraction d0={}: {}", d0, fractiond0
        def fraction = factory.fromValue(value, max)
        log.info "fraction: {}", fraction
    }

    @Test
    void "compare continued fractions a<b"() {
        double z = 1.0
        def a = factory.create(z, [1, 8, 11] as int[])
        def b = factory.create(z, [1, 8, 27] as int[])
        assert !a.equals(b)
        assert a.compareTo(b) < 0
    }

    @Test
    void "compare continued fractions a<<b"() {
        double z = 1.0
        def a = factory.create(z, [1, 8, 11] as int[])
        def b = factory.create(z, [1, 8] as int[])
        assert !a.equals(b)
        assert a.compareTo(b) < 0
    }

    @Test
    void "compare continued fractions a>b"() {
        double z = 1.0
        def a = factory.create(z, [1, 8, 27] as int[])
        def b = factory.create(z, [1, 8, 11] as int[])
        assert !a.equals(b)
        assert a.compareTo(b) > 0
    }

    @Test
    void "compare continued fractions a>>b"() {
        double z = 1.0
        def a = factory.create(z, [1, 8] as int[])
        def b = factory.create(z, [1, 8, 11] as int[])
        assert !a.equals(b)
        assert a.compareTo(b) > 0
    }

    @Test
    void "compare continued fractions a=b"() {
        double z = 1.0
        def a = factory.create(z, [1, 8, 11] as int[])
        def b = factory.create(z, [1, 8, 11] as int[])
        assert a.equals(b)
        assert a.compareTo(b) == 0
    }

    @Test
    void "compare continued fractions az!=bz"() {
        double az = 1.0
        double bz = 2.0
        def a = factory.create(az, [1, 8, 11] as int[])
        def b = factory.create(bz, [1, 8, 11] as int[])
        assert !a.equals(b)
        assert a.compareTo(b) < 0
    }

    @Test
    void "to array"() {
        def a = factory.create(1.0, [1, 8, 11] as int[])
        int[] array = new int[3]
        a.toArray(array)
        assert array == [1, 8, 11]
    }

    @Test
    void "set denominator"() {
        def a = factory.create(1.0, [1, 8, 11] as int[])
        def b = a.set 1, 10
        assert a.toArray() == [1, 8, 11]
        assert b.toArray() == [1, 10, 11]
    }

    @Test
    void "expand"() {
        def a = factory.create(1.0, [1, 8, 11] as int[])
        def b = a.expand 5
        assert a.toArray() == [1, 8, 11]
        assert b.toArray() == [1, 8, 11, 5]
    }

    @Test
    void "contract"() {
        def a = factory.create(1.0, [1, 8, 11] as int[])
        def b = a.contract()
        assert a.toArray() == [1, 8, 11]
        assert b.toArray() == [1, 8]
    }

    @Test
    void "contract 2"() {
        def a = factory.create(1.0, [1, 8, 11] as int[])
        def b = a.contract(2)
        assert a.toArray() == [1, 8, 11]
        assert b.toArray() == [1]
    }

    @Test(expected = IndexOutOfBoundsException)
    void "contract -1"() {
        def a = factory.create(1.0, [1, 8, 11] as int[])
        def b = a.contract(-1)
    }

    static inputs = new DataInputs().run()

    static outputs = new IntegerFractionData().run()

    static n0_cases = new n0_cases().run()

    static Injector injector

    static IntegerFractionFactory factory

    @BeforeClass
    static void beforeTest() {
        injector = createInjector()
        factory = injector.getInstance IntegerFractionFactory
    }

    static Injector createInjector() {
        Guice.createInjector new IntegerFractionsModule()
    }
}
