package com.anrisoftware.fractions.calculator.app

import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.util.logging.Slf4j

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
@Slf4j
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
		log.info "Arguments: '{}'", Arrays.toString(args)
		App app = injector.getInstance App
		app.doStart args
		assert app.getOutput() == "[63;-9,-6,-10,-34,2]"
	}

	@Test
	void "caluclate value"() {
		String service = "IntegerFraction"
		String max = "6"
		String denos = "[63;-9,-6,-10,-34,2]"
		String[] args = [
			"-service",
			service,
			"-denominators",
			denos
		]
		log.info "Arguments: '{}'", Arrays.toString(args)
		App app = injector.getInstance App
		app.doStart args
		assert app.getOutput() == "62.891"
	}

	@Test
	void "caluclate value, set format"() {
		String service = "IntegerFraction"
		String max = "6"
		String denos = "[63;-9,-6,-10,-34,2]"
		String format = "0.######"
		String[] args = [
			"-service",
			service,
			"-value-format",
			format,
			"-denominators",
			denos
		]
		log.info "Arguments: '{}'", Arrays.toString(args)
		App app = injector.getInstance App
		app.doStart args
		assert app.getOutput() == "62.890877"
	}

	static Injector injector

	@BeforeClass
	static void createInjector() {
		injector = Guice.createInjector(new AppModule())
	}
}
