package com.anrisoftware.fractions.core;

/**
 * Continued fraction service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface FractionService {

	/**
	 * Returns the continued fraction service information.
	 * 
	 * @return the information.
	 */
	Object getInfo();

	/**
	 * Returns the continued fraction factory.
	 * 
	 * @return the {@link FractionFactory}.
	 */
	FractionFactory getFactory(Object... parent);
}
