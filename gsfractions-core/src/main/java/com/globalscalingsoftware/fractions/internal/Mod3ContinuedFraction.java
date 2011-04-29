package com.globalscalingsoftware.fractions.internal;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
public class Mod3ContinuedFraction extends AbstractContinuedFraction<Integer> {

	public interface Mod3ContinuedFractionFactory {
		Mod3ContinuedFraction create(@Assisted("value") double value,
				@Assisted("z") float z,
				@Assisted("maxDenominators") int maxDenominators);
	}

	private final Mod3Round round;
	private final float z;
	private final int maxDenominators;
	private final double value;

	@Inject
	Mod3ContinuedFraction(Mod3Round round, @Assisted("value") double value,
			@Assisted("z") float z,
			@Assisted("maxDenominators") int maxDenominators) {
		super(new ArrayList<Integer>());
		this.round = round;
		this.z = z;
		this.maxDenominators = maxDenominators;
		this.value = value;
		evaluate(value, denominators);
	}

	private void evaluate(double value, List<Integer> denominators) {
		double logvalue = value;
		denominators.add(round.round(logvalue));
		calculateDenos(logvalue, denominators);
	}

	private void calculateDenos(double logvalue, List<Integer> denominators) {
		int lmax = maxDenominators;
		if (denominators.get(0) % 3 != 0) {
			lmax = 0;
		}
		int nenner = denominators.get(0);
		int limes = 32766;
		double dn = 0.0;
		int level = 1;
		while (level <= lmax) {
			dn = logvalue - nenner;
			if (Math.abs(dn) <= 1.0 / limes) {
				denominators.add(limes);
				lmax = level;
			} else {
				logvalue = z / dn;
				nenner = round.round(logvalue);
				denominators.add(nenner);
			}
			level++;
		}
	}

	@Override
	public float getZ() {
		return z;
	}

	@Override
	public double getValue() {
		return value;
	}
}
