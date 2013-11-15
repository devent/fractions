package com.anrisoftware.fractions.calculator.app

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.junit.BeforeClass
import org.junit.Test

import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see App
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
class AppTest {

	@Test
	void "caluclate continued fraction"() {
		String service = "IntegerFraction"
		String max = "6"
		String value = "62.8908766605"
		String[] args = [
			"-service",
			service,
			"-max",
			max,
			value
		]
		App app = injector.getInstance App
		app.doStart args
	}

	static Injector injector

	@BeforeClass
	static void createInjector() {
		injector = Guice.createInjector(new AppModule())
	}
}
