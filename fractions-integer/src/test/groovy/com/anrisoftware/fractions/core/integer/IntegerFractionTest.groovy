package com.anrisoftware.fractions.core.integer

import org.junit.Before
import org.junit.Test

import com.google.inject.Guice
import com.google.inject.Injector

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
		inputs.eachWithIndex { value, i ->
			def fraction = factory.fromValue(value)
			fraction.eachWithIndex { denominator, n ->
				assert outputs[i][n] == denominator
			}
		}
	}
}
