/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-calculator-cli.
 *
 * fractions-calculator-cli is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-calculator-cli is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-calculator-cli. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.calculator.app

import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.fractions.calculator.starter.Starter
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
    void "starter no args"() {
        String[] args = []
        Starter.main args
    }

    @Test(expected=AppException)
    void "starter invalid args"() {
        String[] args = ["-invalid"]
        Starter.main args
    }

    @Test
    void "caluclate continued fraction by starter"() {
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
        Starter.main args
    }

    @Test
    void "print help"() {
        String[] args = [
            "-help",
            "-locale",
            "en-US"
        ]
        log.info "Arguments: '{}'", Arrays.toString(args)
        App app = injector.getInstance App
        app.doStart args
        assert app.getOutput().size() == 5217
        assert app.getOutput()[0] == "D"
    }

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
    void "caluclate continued fraction with d0"() {
        String d0 = "0"
        String value = "62.8908766605"
        String[] args = [
            "-d0",
            d0,
            value
        ]
        log.info "Arguments: '{}'", Arrays.toString(args)
        App app = injector.getInstance App
        app.doStart args
        assert app.getOutput() == "[0;0,62,-9,-6,-10,-34,2,16,-4]"
    }

    @Test
    void "caluclate continued fraction with z"() {
        String z = "2.0"
        String value = "62.8908766605"
        String[] args = [
            "-z",
            z,
            value
        ]
        log.info "Arguments: '{}'", Arrays.toString(args)
        App app = injector.getInstance App
        app.doStart args
        assert app.getOutput() == "[63;-9,-6,-10,-34,2,16,-4,6,3]"
    }

    @Test
    void "caluclate mod3 continued fraction"() {
        String service = "Mod3Fraction"
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
        assert app.getOutput() == "[63;-9,-6,-11,2,-2,2]"
    }

    @Test
    void "caluclate value"() {
        String service = "IntegerFraction"
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
        assert app.getOutput() == "62.890876661"
    }

    @Test
    void "caluclate mod3 value"() {
        String service = "Mod3Fraction"
        String denos = "[63;-9,-6,-11,2,-2,2]"
        String[] args = [
            "-service",
            service,
            "-denominators",
            denos
        ]
        log.info "Arguments: '{}'", Arrays.toString(args)
        App app = injector.getInstance App
        app.doStart args
        assert app.getOutput() == "62.890877346"
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

    @Test
    void "compare fractions"() {
        String a = "[63;-9,-6,-10,-34,2]"
        String b = "[63;-9,-6,-10,-34,4]"
        String[] args = [
            "-a",
            a,
            "-b",
            b
        ]
        log.info "Arguments: '{}'", Arrays.toString(args)
        App app = injector.getInstance App
        app.doStart args
        assert app.getOutput() == "1"
    }

    static Injector injector

    @BeforeClass
    static void createInjector() {
        injector = Guice.createInjector(new AppModule())
    }
}
