package com.kdsc.protogen.runtime.options;

public final record EqualsHashCodeOptions(boolean doubleNANEqualsAnotherDoubleNANParameter, boolean floatNANEqualsAnotherFloatNANParameter) {

    public static EqualsHashCodeOptions defaultEqualsHashCodeOptions = new EqualsHashCodeOptions(true, true);

    public static EqualsHashCodeOptions doubleNANNotEqualToAnotherDoubleNAN = new EqualsHashCodeOptions(false, true);

    public static EqualsHashCodeOptions floatNANNotEqualToAnotherFloatNAN = new EqualsHashCodeOptions(true, false);

    public static EqualsHashCodeOptions nanNotEqualToAnotherNAN = new EqualsHashCodeOptions(false, false);

}
