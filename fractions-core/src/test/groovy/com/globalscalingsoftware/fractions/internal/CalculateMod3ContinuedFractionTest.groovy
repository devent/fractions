package com.globalscalingsoftware.fractions.internal

import org.junit.Test;

import com.globalscalingsoftware.fractions.internal.Mod3ContinuedFraction.Mod3ContinuedFractionFactory;

class CalculateMod3ContinuedFractionTest extends AbstractValuesResults {
	
	@Test
	void testValues() {
		values.eachWithIndex { it, idx ->
			def value = it[0]
			def z = (int)it[1]
			def maxDenominators = (int)it[2]
			assert factory.create(value, z, maxDenominators).toString() == results[idx]
		}
	}
}
