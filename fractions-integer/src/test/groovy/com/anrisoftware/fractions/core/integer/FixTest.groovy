/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of fractions-integer. All rights reserved.
 */
package com.anrisoftware.fractions.core.integer

import org.junit.Test

/**
 * Test the round to zero algorithm.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FixTest {

	static double[] inputs = [-5.2, 5.2, -1.9, -0.2, 3.4, 5.6, 7.0]
	
	static double[] outputs = [-5.0, 5.0, -1.0, 0.0, 3.0, 5.0, 7.0]
	
	@Test
	void "fix inputs to outputs"() {
		inputs.eachWithIndex { double value, int i ->
			double result = MathUtils.fix(value)
			assert outputs[i] == result
		}
	}
}
