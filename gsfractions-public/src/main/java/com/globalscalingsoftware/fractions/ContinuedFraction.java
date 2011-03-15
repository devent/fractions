package com.globalscalingsoftware.fractions;

import java.util.List;

public interface ContinuedFraction<Type extends Number> extends List<Type> {

	float getZ();

	float floatValue();

	double doubleValue();
}
