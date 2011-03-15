package com.globalscalingsoftware.fractions.internal;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
public class Mod3ContinuedFractionFromValue extends
		AbstractContinuedFraction<Integer> {

	public interface Mod3ContinuedFractionFromValueFactory {
		Mod3ContinuedFractionFromValue create(@Assisted float z,
				@Assisted List<Integer> denominators);
	}

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
