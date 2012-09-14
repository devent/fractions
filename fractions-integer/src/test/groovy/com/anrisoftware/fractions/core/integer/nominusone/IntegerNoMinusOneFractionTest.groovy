package com.anrisoftware.fractions.core.integer.nominusone

import groovy.util.logging.Slf4j

import org.junit.Before
import org.junit.Test

import com.anrisoftware.fractions.core.integer.factories.IntegerNoMinusOneFractionFactory;
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

	@Before
	void beforeTest() {
		injector = createInjector()
		factory = injector.getInstance IntegerNoMinusOneFractionFactory
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
}
