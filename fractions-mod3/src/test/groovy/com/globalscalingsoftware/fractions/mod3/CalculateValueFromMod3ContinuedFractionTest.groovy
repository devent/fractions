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
import org.junit.Test

import com.globalscalingsoftware.fractions.mod3.api.Mod3ContinuedFractionFromValueFactory

class CalculateValueFromMod3ContinuedFractionTest extends AbstractValuesResults {

	def factory

	@Before
	void beforeTest() {
		factory = injector.getInstance(Mod3ContinuedFractionFromValueFactory)
		valuesFileName = "valuemod3continuedfractions_values.txt"
		resultsFileName = "valuemod3continuedfractions_results.txt"
		super.beforeTest()
	}

	def processOneLineOfTestValues(def line) {
		def vs = []
		line.split("[,;]").each { vs << Double.parseDouble(it) }
		testValues << vs
		return true
	}

	@Test
	void testValues() {
		testValues.eachWithIndex { it, idx ->
			def z = (int)it[0]
			def denominators = it.subList(1, it.size())
			def res = Double.parseDouble(expectedResults[idx])
			assert Math.abs(factory.create(z, denominators).doubleValue() - res) < 1e-3
		}
	}
}
