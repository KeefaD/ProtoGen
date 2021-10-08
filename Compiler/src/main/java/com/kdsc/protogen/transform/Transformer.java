package com.kdsc.protogen.transform;

import com.kdsc.protogen.compilerresults.CompilerResults;
import com.kdsc.protogen.filegenerationtreenodes.FileNode;

import java.util.List;

public interface Transformer {

    List<FileNode> transform(final CompilerResults compilerResults, TransformerContext transformerContext);

}