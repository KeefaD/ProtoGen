package com.kdsc.protogen.filegenerationtreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final record FileGenerationTreeFormattedStringOptions(boolean hideBaseParseTreeNodeParameter) implements FormattedStringOptions {

    public static FileGenerationTreeFormattedStringOptions defaultFileGenerationTreeFormattedStringOptions = new FileGenerationTreeFormattedStringOptions(false);

}