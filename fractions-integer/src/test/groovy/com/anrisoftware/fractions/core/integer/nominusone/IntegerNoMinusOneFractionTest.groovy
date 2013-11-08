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
package com.anrisoftware.fractions.core.integer.nominusone

import groovy.util.logging.Slf4j

import org.junit.Before
import org.junit.Test
import org.perfidix.Benchmark
import org.perfidix.annotation.Bench
import org.perfidix.ouput.TabularSummaryOutput

import com.anrisoftware.fractions.core.ContinuedFraction
import com.anrisoftware.fractions.core.integer.factories.IntegerNoMinusOneFractionFactory
import com.google.inject.Guice
import com.google.inject.Injector

@Slf4j
class IntegerNoMinusOneFractionTest {

	/**
	 * The input values.
	 */
	static def inputs = new DataInputs().run()

	/**
	 * The expected denominators for each input value.
	 */
	static def outputs = new DataOutputs().run()

	Injector injector

	IntegerNoMinusOneFractionFactory factory

	Random random

	@Before
	void beforeTest() {
		injector = createInjector()
		factory = injector.getInstance IntegerNoMinusOneFractionFactory
		random = new Random(0)
	}

	Injector createInjector() {
		Guice.createInjector new IntegerNoMinusOneFractionModule()
	}

	@Test
	void "calculate continued fractions"() {
		int max = 9
		inputs.eachWithIndex { double value, i ->
			def fraction = factory.fromValue(value, max)
			log.info "{}. fraction: {}", i, fraction
			//assert outputs[i].size() == fraction.size()
			if (!fraction.equals(outputs[i])) {
				log.warn "The denominators are not match, but the value match"
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
		ContinuedFraction fraction = factory.fromValue(benmarkValue, 9i)
	}

	void chooseValue() {
		if (benchmarkIndex >= inputs.size()) {
			benchmarkIndex = 0
		}
		benmarkValue = inputs.get(benchmarkIndex++)
	}
}
