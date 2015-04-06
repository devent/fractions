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
import org.perfidix.Benchmark
import org.perfidix.annotation.Bench
import org.perfidix.ouput.TabularSummaryOutput

import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see IntegerNoMinusOneFraction
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Slf4j
class IntegerNoMinusOneFractionTest {

	@Test
	void "calculate continued fractions"() {
		int max = 9
		double z = 1.0
		inputs.eachWithIndex { double value, i ->
			int[] denos = outputs[i] as int[]
			def fraction = factory.fromValue(value, max)
			log.info "{}. fraction: {}", i, fraction
			if (!fraction.equals(factory.create(z, denos))) {
				log.warn "{}. fraction: {}; expected denominators: {}", i, fraction, outputs[i]
			}
		}
	}

	double benmarkValue

	int benchmarkIndex

	@Test
	void "run benchmark"() {
		benchmarkIndex = 0
		def benchmark = new Benchmark()
		benchmark.add(this)
		def result = benchmark.run()
		new TabularSummaryOutput().visitBenchmark result
	}

	@Bench(runs = 125, beforeEachRun = "chooseValue")
	void "benmark continued fraction"() {
		def fraction = factory.fromValue(benmarkValue, 1)
	}

	void chooseValue() {
		if (benchmarkIndex >= inputs.size()) {
			benchmarkIndex = 0
		}
		benmarkValue = inputs.get(benchmarkIndex++)
	}

	static inputs = new DataInputs().run()

	static outputs = new IntegerNoMinusOneFractionData().run()

	static Injector injector

	static IntegerNoMinusOneFractionFactory factory

	static Random random

	@BeforeClass
	static void beforeTest() {
		injector = createInjector()
		factory = injector.getInstance IntegerNoMinusOneFractionFactory
		random = new Random(0)
	}

	static Injector createInjector() {
		Guice.createInjector new IntegerFractionsModule()
	}
}
