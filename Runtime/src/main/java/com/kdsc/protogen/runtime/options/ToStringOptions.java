package com.kdsc.protogen.runtime.options;

public final record ToStringOptions(boolean allOnOneLineParameter) {

    public static ToStringOptions defaultToStringOptions = new ToStringOptions(false);

    public static ToStringOptions allOnOneLine = new ToStringOptions(true);

}
