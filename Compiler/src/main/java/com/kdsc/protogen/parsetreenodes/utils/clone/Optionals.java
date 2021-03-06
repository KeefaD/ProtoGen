package com.kdsc.protogen.parsetreenodes.utils.clone;

import com.kdsc.protogen.parsetreenodes.BaseParseTreeNode;

import java.util.Objects;
import java.util.Optional;

public class Optionals {

    public static <T extends BaseParseTreeNode> Optional<T> clone(final Optional<T> inputOptional) {

        Objects.requireNonNull(inputOptional);

        return inputOptional.map(t -> (T) t.clone());
    }

}