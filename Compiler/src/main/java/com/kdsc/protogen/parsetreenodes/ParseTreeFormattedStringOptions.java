package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public record ParseTreeFormattedStringOptions(
    boolean hideBaseParseTreeNodeParameter
) implements FormattedStringOptions {

    public static ParseTreeFormattedStringOptions defaultParseTreeFormattedStringOptions = new ParseTreeFormattedStringOptions(false);

    public static ParseTreeFormattedStringOptions hideBaseParseTreeNode = new ParseTreeFormattedStringOptions(true);

}