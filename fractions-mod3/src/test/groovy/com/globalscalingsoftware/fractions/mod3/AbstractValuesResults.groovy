/*
 * Copyright 2011-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-mod3.
 *
 * fractions-mod3 is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-mod3 is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-mod3. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.fractions.mod3

import org.junit.Before
import org.junit.BeforeClass

import com.globalscalingsoftware.fractions.mod3.Mod3ContinuedFractionModule
import com.google.common.base.Charsets
import com.google.common.io.LineProcessor
import com.google.common.io.Resources
import com.google.inject.Guice

abstract class AbstractValuesResults {

	static injector

	@BeforeClass
	static void beforeClass() {
		injector = Guice.createInjector(new Mod3ContinuedFractionModule())
	}

	def valuesFileName

	def resultsFileName

	def testValues = []

	def expectedResults = []

	@Before
	void beforeTest() {
		testValues = readTestValues()
		expectedResults = readExpectedResults()
	}

	def readTestValues() {
		return Resources.readLines(Resources.getResource(AbstractValuesResults, valuesFileName), Charsets.UTF_8,
		[
			getResult: { return testValues },

			processLine: {  line ->
				return processOneLineOfTestValues(line)
			}
		]as LineProcessor)
	}

	abstract processOneLineOfTestValues(def line)

	def readExpectedResults() {
		return Resources.readLines(Resources.getResource(
		AbstractValuesResults, resultsFileName), Charsets.UTF_8)
	}
}
