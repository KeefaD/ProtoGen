package com.kdsc.protogen.parsetree.utils.clone;

import com.kdsc.protogen.parsetree.BaseParseTreeNode;

import java.util.Optional;

public class Optionals {

    public static <T extends BaseParseTreeNode> Optional<T> clone(final Optional<T> inputOptional) {
        return inputOptional.map(t -> (T) t.clone());
    }

}