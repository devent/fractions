package com.globalscalingsoftware.fractions.internal

import com.globalscalingsoftware.fractions.internal.Mod3ContinuedFraction.Mod3ContinuedFractionFactory 
import com.globalscalingsoftware.fractions.module.Mod3ContinuedFractionModule 
import com.google.common.base.Charsets 
import com.google.common.io.LineProcessor 
import com.google.common.io.Resources 
import com.google.inject.Guice 
import org.junit.Before;
import org.junit.BeforeClass;

class AbstractValuesResults {
	
	static injector
	
	@BeforeClass
	static void beforeClass() {
		injector = Guice.createInjector(new Mod3ContinuedFractionModule())
	}
	
	def valuesFileName
	
	def resultsFileName
	
	def values = []
	
	def results = []
	
	@Before
	void beforeTest() {
		values = Resources.readLines(Resources.getResource(
				AbstractValuesResults, valuesFileName), Charsets.UTF_8,
				[getResult:{ return values },
					processLine: {  line ->
						return processLine(line)
					}]as LineProcessor)
		results = Resources.readLines(Resources.getResource(
				AbstractValuesResults, resultsFileName), Charsets.UTF_8)
	}
}
