package com.globalscalingsoftware.fractions.internal

import com.globalscalingsoftware.fractions.internal.Mod3ContinuedFractionFromValue.Mod3ContinuedFractionFromValueFactory 
import org.junit.Before;
import org.junit.Test;

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
