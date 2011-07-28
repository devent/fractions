package com.globalscalingsoftware.fractions.mod3.api;

import com.globalscalingsoftware.fractions.api.ContinuedFraction;
import com.globalscalingsoftware.fractions.api.ContinuedFractionFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new mod3 implementation of a {@link ContinuedFraction}.
 * The continued fraction is calculated from the given value.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface Mod3ContinuedFractionFactory extends ContinuedFractionFactory {

	/**
	 * Factory to create a new mod3 implementation of a
	 * {@link ContinuedFraction}. The continued fraction is calculated from the
	 * given value.
	 * 
	 * @param value
	 *            the value of the continued fraction.
	 * 
	 * @param z
	 *            the Z variable of the continued fraction.
	 * 
	 * @param maxDenominators
	 *            the maximum count of denominators for the continued fraction.
	 * 
	 * @return the new created mod3 implementation of a
	 *         {@link ContinuedFraction}. The continued fraction is calculated
	 *         from the given value.
	 */
	@Override
	ContinuedFraction<?> create(@Assisted("value") double value,
			@Assisted("z") float z,
			@Assisted("maxDenominators") int maxDenominators);
}
