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

import static com.anrisoftware.fractions.calculator.app.AppHelpLogger._.error_load_resource;
import static com.anrisoftware.fractions.calculator.app.AppHelpLogger._.error_load_resource_message;
import static com.anrisoftware.fractions.calculator.app.AppHelpLogger._.error_print_help;
import static com.anrisoftware.fractions.calculator.app.AppHelpLogger._.error_print_help_message;
import static com.anrisoftware.fractions.calculator.app.AppHelpLogger._.resource_name;
import static com.anrisoftware.fractions.calculator.app.AppHelpLogger._.the_output;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging for {@link AppHelp}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.7
 */
class AppHelpLogger extends AbstractLogger {

    enum _ {

        error_load_resource("Error load text resource"),

        error_load_resource_message("Error load text resource '{}': {}"),

        resource_name("resource"),

        error_print_help("Error print help text"),

        error_print_help_message("Error print help text: {}"),

        the_output("output");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Sets the context of the logger to {@link AppHelp}.
     */
    public AppHelpLogger() {
        super(AppHelp.class);
    }

    AppException errorLoadResource(ResourcesException e, String name) {
        return logException(new AppException(error_load_resource, e).add(
                resource_name, name), error_load_resource_message, name,
                e.getLocalizedMessage());
    }

    AppException errorPrintHelp(IOException e, Appendable output) {
        return logException(
                new AppException(error_print_help, e).add(the_output, output),
                error_print_help_message, e.getLocalizedMessage());
    }
}
