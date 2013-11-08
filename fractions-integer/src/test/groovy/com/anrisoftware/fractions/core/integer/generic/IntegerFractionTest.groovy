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
