/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-calculator-cli.
 *
 * fractions-calculator-cli is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-calculator-cli is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-calculator-cli. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.calculator.parser

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.fractions.calculator.app.AppModule
import com.anrisoftware.fractions.integer.IntegerFractionFactory
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see CalculationParser
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
class CalculationParserTest {

	@Test
	void "parse arguments"() {
		String service = "IntegerFraction"
		String max = "6"
		String value = "62.8908766605"
		String[] args = [
			"-service",
			service,
			"-max",
			max,
			value
		]
		def parser = parserFactory.create args
		def model = parser.parse().getModel()
		assertDecimalEquals model.getValue(), 62.8908766605d
		assert model.getMax() == 6
		assert model.getFractionFactory() instanceof IntegerFractionFactory
	}

	static Injector injector

	static CalculationParserFactory parserFactory

	@BeforeClass
	static void createInjector() {
		injector = Guice.createInjector(new AppModule())
		parserFactory = injector.getInstance CalculationParserFactory
	}
}
