/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of fractions-integer. All rights reserved.
 */
package com.anrisoftware.fractions.core.integer

import groovy.util.logging.Slf4j

import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.util.FastMath
import org.junit.Test

/**
 * Test of natural logarithm algorithms.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Slf4j
class NaturalLogTest {
	
	static double[] inputs = [0.0, 1.0, -1.0]
	
	static double[] javaOutputs = [Double.NEGATIVE_INFINITY, 0.0, Double.NaN]
	
	static def complexOutputs = [new Complex(Double.NEGATIVE_INFINITY, 0.0), new Complex(0.0), new Complex(0.0, 3.141592653589793)]
	
	@Test
	void "use java log"() {
		inputs.eachWithIndex { double value, int i ->
			double result = FastMath.log(value)
			assert javaOutputs[i] == result
		}
	}

	@Test
	void "use complex log"() {
		inputs.eachWithIndex { double value, int i ->
			def complex = new Complex(value)
			def result = complex.log()
			log.info "{}: complex {}", i, result
			assert complexOutputs[i] == result
		}
	}

}
