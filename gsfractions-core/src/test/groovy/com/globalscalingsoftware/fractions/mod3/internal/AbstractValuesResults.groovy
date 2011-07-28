package com.globalscalingsoftware.fractions.mod3.internal

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
