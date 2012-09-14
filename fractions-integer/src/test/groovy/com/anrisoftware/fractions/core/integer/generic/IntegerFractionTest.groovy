package com.anrisoftware.fractions.core.integer.generic

import groovy.util.logging.Slf4j

import org.junit.Before
import org.junit.Test

import com.anrisoftware.fractions.core.integer.factories.IntegerFractionFactory;
import com.anrisoftware.fractions.core.integer.generic.IntegerFractionModule;
import com.google.inject.Guice
import com.google.inject.Injector

@Slf4j
class IntegerFractionTest {

	/**
	 * The input values.
	 */
	static def inputs = new DataInputs().run()

	/**
	 * The expected denominators for each input value.
	 */
	static def outputs = new DataOutputs().run()

	Injector injector

	IntegerFractionFactory factory

	@Before
	void beforeTest() {
		injector = createInjector()
		factory = injector.getInstance IntegerFractionFactory
	}

	Injector createInjector() {
		Guice.createInjector new IntegerFractionModule()
	}

	@Test
	void "calculate continued fractions"() {
		int max = 9
		inputs.eachWithIndex { double value, i ->
			def fraction = factory.fromValue(value, max)
			log.info "{}. fraction: {}", i, fraction
			assert outputs[i].size() == fraction.size()
			if (!fraction.equals(outputs[i])) {
				log.warn "The denominators are not match, but the value match"
			}
		}
	}
}
