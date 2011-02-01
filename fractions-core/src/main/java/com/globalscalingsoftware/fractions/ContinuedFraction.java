package com.globalscalingsoftware.fractions;

import java.util.List;

public interface ContinuedFraction<Type extends Number> extends List<Type> {

	Type getZ();

	double doubleValue();
}
