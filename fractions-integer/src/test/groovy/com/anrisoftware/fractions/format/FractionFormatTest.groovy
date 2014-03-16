/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.fractions.format

import static com.anrisoftware.fractions.format.FractionFormat.*
import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.fractions.integer.IntegerFractionFactory
import com.anrisoftware.fractions.integer.IntegerFractionsModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see FractionFormat
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@Slf4j
class FractionFormatTest {

	@Test
	void "format fractions"() {
		def format = createFractionFormat factory
		fractions.inputs.eachWithIndex { it, int i ->
			def out = format.format(it)
			log.info "Formats '{}' from {}", out, it
			assert out == fractions.outputs[i]
		}
	}

	@Test
	void "parse fractions"() {
		def format = createFractionFormat factory
		strings.inputs.eachWithIndex { it, int i ->
			def out = format.parse(it)
			log.info "Parses {} from '{}'", out, it
			assertDecimalEquals out.getValue(), strings.outputs[i]
		}
	}

	static strings

	static fractions

	static Injector injector

	static IntegerFractionFactory factory

	@BeforeClass
	static void beforeTest() {
		injector = createInjector()
		factory = injector.getInstance IntegerFractionFactory
		strings = [
			inputs: [
				"[12;8,10,-23,2,5]",
				"[-12;-8,-10,23,-2,-5]"
			],
			outputs: [
				12.12345d,
				-12.12345d,
			]]
		fractions = [
			inputs: [
				{ factory.fromValue(12.12345, 9) }(),
				{ factory.fromValue(-12.12345, 9) }(),
			],
			outputs: [
				"[12;8,10,-23,2,5]",
				"[-12;-8,-10,23,-2,-5]",
			],
		]
	}

	static Injector createInjector() {
		Guice.createInjector new IntegerFractionsModule()
	}
}
