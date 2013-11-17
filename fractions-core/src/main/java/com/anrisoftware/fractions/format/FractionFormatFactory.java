/*
 * Copyright 2011-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-core.
 *
 * fractions-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.format;

import com.anrisoftware.fractions.core.FractionFactory;

/**
 * Creates the duration format.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.9
 */
public interface FractionFormatFactory {

	/**
	 * Creates duration format.
	 * 
	 * @param factory
	 *            the continued fraction {@link FractionFactory} factory.
	 * 
	 * @return the {@link FractionFormat}.
	 */
	FractionFormat create(FractionFactory factory);
}