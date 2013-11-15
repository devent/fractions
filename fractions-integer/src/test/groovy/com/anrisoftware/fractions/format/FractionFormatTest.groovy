package com.anrisoftware.fractions.format

import static com.anrisoftware.fractions.format.FractionFormat.*
import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.fractions.integer.IntegerFractionFactory
import com.anrisoftware.fractions.integer.IntegerFractionsModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see FractionFormat
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@Slf4j
class FractionFormatTest {

	@Test
	void "format fractions"() {
		def format = createFractionFormat factory
		fractions.inputs.eachWithIndex { it, int i ->
			def out = format.format(it)
			log.info "Formats '{}' from {}", out, it
			assert out == fractions.outputs[i]
		}
	}

	@Test
	void "parse fractions"() {
		def format = createFractionFormat factory
		strings.inputs.eachWithIndex { it, int i ->
			def out = format.parse(it)
			log.info "Parses {} from '{}'", out, it
			assertDecimalEquals out.getValue(), strings.outputs[i]
		}
	}

	static strings

	static fractions

	static Injector injector

	static IntegerFractionFactory factory

	@BeforeClass
	static void beforeTest() {
		injector = createInjector()
		factory = injector.getInstance IntegerFractionFactory
		strings = [
			inputs: [
				"[12;8,10,-23,2,5]",
				"[-12;-8,-10,23,-2,-5]"
			],
			outputs: [
				12.12345d,
				-12.12345d,
			]]
		fractions = [
			inputs: [
				{ factory.fromValue(12.12345, 9) }(),
				{ factory.fromValue(-12.12345, 9) }(),
			],
			outputs: [
				"[12;8,10,-23,2,5]",
				"[-12;-8,-10,23,-2,-5]",
			],
		]
	}

	static Injector createInjector() {
		Guice.createInjector new IntegerFractionsModule()
	}
}
