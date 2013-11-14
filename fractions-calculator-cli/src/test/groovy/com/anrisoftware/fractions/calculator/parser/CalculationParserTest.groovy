package com.anrisoftware.fractions.calculator.parser

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.fractions.calculator.app.AppModule
import com.anrisoftware.fractions.integer.IntegerFractionFactory
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see CalculationParser
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
class CalculationParserTest {

	@Test
	void "parse arguments"() {
		String service = "IntegerFraction"
		String value = "62.8908766605"
		String[] args = ["-service", service, value]
		def parser = parserFactory.create args
		def model = parser.parse().getModel()
		assertDecimalEquals model.getValue(), 62.8908766605d
		assert model.getFractionFactory() instanceof IntegerFractionFactory
	}

	static Injector injector

	static CalculationParserFactory parserFactory

	@BeforeClass
	static void createInjector() {
		injector = Guice.createInjector(new AppModule())
		parserFactory = injector.getInstance CalculationParserFactory
	}
}
