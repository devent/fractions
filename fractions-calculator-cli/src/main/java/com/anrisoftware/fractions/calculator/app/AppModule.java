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

import com.anrisoftware.fractions.calculator.parser.CalculationParserModule;
import com.anrisoftware.fractions.format.FractionFormatModule;
import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule;
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule;
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule;
import com.anrisoftware.resources.texts.texts.TextsResourcesCharsetModule;
import com.anrisoftware.resources.texts.texts.TextsResourcesModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Install every needed module of the application.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class AppModule extends AbstractModule {

    /**
     * Application resources module.
     *
     * @author Erwin Mueller, erwin.mueller@deventm.org
     * @since 2.7
     */
    public final class ResourcesModule extends AbstractModule {

        @Override
        protected void configure() {
            install(new TextsResourcesModule());
            install(new TextsDefaultMapsModule());
            install(new TextsResourcesCharsetModule());
            install(new BinariesResourcesModule());
            install(new BinariesDefaultMapsModule());
        }

    }

    @Override
    protected void configure() {
        install(new CalculationParserModule());
        install(new FractionFormatModule());
        install(new ResourcesModule());
        install(new FactoryModuleBuilder().implement(AppHelp.class,
                AppHelp.class).build(AppHelpFactory.class));
    }

}
