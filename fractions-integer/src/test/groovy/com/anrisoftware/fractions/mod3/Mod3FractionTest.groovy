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
package com.anrisoftware.fractions.mod3

import groovy.util.logging.Slf4j

import org.junit.BeforeClass

import com.anrisoftware.fractions.integer.DataInputs
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

	//@Test
	void "calculate continued fractions"() {
		int max = 9
		double z = 1.0
		inputs.eachWithIndex { value, i ->
			int[] denos = outputs[i] as int[]
			def fraction = factory.fromValue(value, max)
			log.info "{}. fraction: {}", i, fraction
			assert outputs[i].size() == fraction.size()
			if (!fraction.equals(factory.create(z, denos))) {
				log.warn "{}. fraction: {}; expected denominators: {}", i, fraction, outputs[i]
			}
		}
	}

	static inputs = new DataInputs().run()

	static outputs = new Mod3FractionData().run()

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
