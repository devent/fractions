package com.globalscalingsoftware.fractions.mod3.internal;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
class Mod3ContinuedFractionFromValue extends AbstractContinuedFraction<Integer> {

	private final float z;

	@Inject
	public Mod3ContinuedFractionFromValue(@Assisted float z,
			@Assisted List<Integer> denominators) {
		super(denominators);
		this.z = z;
	}

	@Override
	public float getZ() {
		return z;
	}
}
