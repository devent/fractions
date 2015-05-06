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
package com.anrisoftware.fractions.calculator.parser

import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.fractions.calculator.app.AppModule
import com.anrisoftware.fractions.calculator.model.CalculationModel
import com.anrisoftware.fractions.integer.IntegerFractionService
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see CalculationParser
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@Slf4j
class CalculationParserTest {

    @Test
    void "parse args"() {
        String value = "62.8908766605"
        def cases = [
            [
                args: [value], test: { CalculationModel parser ->
                    assertDecimalEquals parser.getValue(), 62.8908766605d
                    assert parser.getMax() == 10
                    assert parser.getService().getClass() == IntegerFractionService
                }
            ],
            [
                args: [
                    "-service",
                    "IntegerFraction",
                    "-max",
                    "6",
                    value
                ], test: { CalculationModel parser ->
                    assertDecimalEquals parser.getValue(), 62.8908766605d
                    assert parser.getMax() == 6
                    assert parser.getService().getClass() == IntegerFractionService
                }
            ],
            [
                args: [
                    "-d0",
                    "0",
                    value
                ], test: { CalculationModel parser ->
                    assertDecimalEquals parser.getValue(), 62.8908766605d
                    assert parser.getD0value() == 0
                    assert parser.getMax() == 10
                    assert parser.getService().getClass() == IntegerFractionService
                }
            ],
            [
                args: [
                    "-f",
                    "#.###",
                    "-d",
                    "[1;5,6,7,8]"
                ], test: { CalculationModel parser ->
                    assert parser.getValue() == null
                    assert parser.getFraction().toArray() == [1, 5, 6, 7, 8]
                    assert parser.getValueFormat().toPattern() == "#0.###"
                    assert parser.getService().getClass() == IntegerFractionService
                }
            ],
            [
                args: [
                    "-a",
                    "[1;5,6,7,8]",
                    "-b",
                    "[1;5,2,3,4]"
                ], test: { CalculationModel parser ->
                    assert parser.getValue() == null
                    assert parser.getFractionA().toArray() == [1, 5, 6, 7, 8]
                    assert parser.getFractionB().toArray() == [1, 5, 2, 3, 4]
                    assert parser.getService().getClass() == IntegerFractionService
                    assert parser.getService().getClass() == IntegerFractionService
                }
            ],
        ]
        cases.each {
            log.info "Parse the arguments {}", it.args
            CalculationModel parser = parserFactory.create(it.args as String[]).parse()
            it.test parser
        }
    }

    @Test
    void "parse both value and fraction"() {
        String value = "62.8908766605"
        String deno = "[1;5,6,7,8]"
        String[] args = [
            "-d",
            deno,
            value
        ]
        CalculationModel parser = parserFactory.create(args)
        shouldFailWith ArgsException, { parser.parse() }
    }

    static Injector injector

    static CalculationParserFactory parserFactory

    @BeforeClass
    static void createInjector() {
        injector = Guice.createInjector(new AppModule())
        parserFactory = injector.getInstance CalculationParserFactory
    }
}
