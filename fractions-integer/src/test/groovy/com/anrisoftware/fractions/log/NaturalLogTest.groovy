/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-integer.
 *
 * fractions-integer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-integer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-integer. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.log

import static com.anrisoftware.globalpom.utils.TestUtils.*
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

	static List javaOutputs = [
		{ double result -> assert result.isInfinite() },
		{ double result -> assertDecimalEquals result, 0.0 },
		{ double result ->
			assert result.isNaN()
		}
	]

	static def complexOutputs = [
		new Complex(Double.NEGATIVE_INFINITY, 0.0),
		new Complex(0.0),
		new Complex(0.0, 3.141592653589793)
	]

	@Test
	void "use fast math log"() {
		inputs.eachWithIndex { double value, int i ->
			double result = FastMath.log(value)
			javaOutputs[i](result)
		}
	}

	@Test
	void "use complex log"() {
		inputs.eachWithIndex { double value, int i ->
			def complex = new Complex(value)
			def result = complex.log()
			log.info "#{} - value: {}; result: {}", i, value, result
			assert complexOutputs[i] == result
		}
	}
}
