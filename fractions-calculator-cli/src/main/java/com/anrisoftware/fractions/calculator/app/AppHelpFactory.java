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
package com.anrisoftware.fractions.calculator.app;

import java.util.Locale;

/**
 * Factory to create the application help printer.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.7
 */
interface AppHelpFactory {

    /**
     * Creates the application help printer for the specified locale.
     *
     * @param locale
     *            the {@link Locale} locale.
     *
     * @return the {@link AppHelp}.
     */
    AppHelp create(Locale locale);
}
