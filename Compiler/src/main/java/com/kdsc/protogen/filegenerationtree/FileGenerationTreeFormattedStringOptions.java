package com.kdsc.protogen.filegenerationtree;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public record FileGenerationTreeFormattedStringOptions(boolean hideBaseParseTreeNodeParameter) implements FormattedStringOptions {

    public static FileGenerationTreeFormattedStringOptions defaultFileGenerationTreeFormattedStringOptions = new FileGenerationTreeFormattedStringOptions(false);

}