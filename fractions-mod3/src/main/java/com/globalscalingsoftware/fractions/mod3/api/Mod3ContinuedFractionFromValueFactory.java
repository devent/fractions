package com.globalscalingsoftware.fractions.mod3.api;

import java.util.List;

import com.globalscalingsoftware.fractions.api.ContinuedFraction;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new mod3 implementation of a {@link ContinuedFraction}
 * from the given list of denominators. The value is calculated from the given
 * denominators.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface Mod3ContinuedFractionFromValueFactory {

	/**
	 * Creates a new mod3 implementation of a {@link ContinuedFraction} from the
	 * given list of denominators. The value is calculated from the given
	 * denominators.
	 * 
	 * @param z
	 *            the Z variable of the continued fraction.
	 * 
	 * @param denominators
	 *            the list of {@link Integer} denominators.
	 * 
	 * @return the new a new mod3 implementation of a {@link ContinuedFraction}.
	 */
	ContinuedFraction<?> create(@Assisted float z,
			@Assisted List<Integer> denominators);
}
