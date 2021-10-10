package com.kdsc.protogen.parsetreenodes.utils.clone;

import com.kdsc.protogen.parsetreenodes.BaseParseTreeNode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lists {

    public static <T extends BaseParseTreeNode> List<T> clone(final List<T> inputList) {

        Objects.requireNonNull(inputList);

        return inputList
            .stream()
            .map(t -> (T)t.clone())
            .collect(Collectors.toList());
    }

}