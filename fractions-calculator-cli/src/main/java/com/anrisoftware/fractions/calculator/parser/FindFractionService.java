package com.anrisoftware.fractions.calculator.parser;

import static java.util.ServiceLoader.load;

import javax.inject.Inject;

import com.anrisoftware.fractions.core.FractionService;

/**
 * Finds the continued fraction service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
class FindFractionService {

	@Inject
	private FindFractionServiceLogger log;

	/**
	 * Finds the continued fraction service with the specified information.
	 * 
	 * @param info
	 *            the service information.
	 * 
	 * @return the {@link FractionService}.
	 * 
	 * @throws ArgsException
	 *             if the service with the specified information could not be
	 *             found.
	 */
	FractionService findService(Object info) {
		for (FractionService service : load(FractionService.class)) {
			if (service.getInfo().equals(info)) {
				return service;
			}
		}
		throw log.errorFindService(info);
	}
}
