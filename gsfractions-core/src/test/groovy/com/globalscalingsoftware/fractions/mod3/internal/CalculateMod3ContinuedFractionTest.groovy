package com.globalscalingsoftware.fractions.mod3.internal

import org.junit.Before
import org.junit.Test

import com.globalscalingsoftware.fractions.mod3.internal.Mod3ContinuedFraction.Mod3ContinuedFractionFactory

class CalculateMod3ContinuedFractionTest extends AbstractValuesResults {

	def factory

	@Before
	void beforeTest() {
		factory = injector.getInstance(Mod3ContinuedFractionFactory)
		valuesFileName = "mod3continuedfractions_values.txt"
		resultsFileName = "mod3continuedfractions_results.txt"
		super.beforeTest()
	}

	def processOneLineOfTestValues(def line) {
		def vs = []
		line.split(',').each {	vs << Double.parseDouble(it) }
		testValues << vs
		return true
	}

	@Test
	void testValues() {
		testValues.eachWithIndex { it, idx ->
			def value = it[0]
			def z = (int)it[1]
			def maxDenominators = (int)it[2]
			assert factory.create(value, z, maxDenominators).toString() == expectedResults[idx]
		}
	}
}
