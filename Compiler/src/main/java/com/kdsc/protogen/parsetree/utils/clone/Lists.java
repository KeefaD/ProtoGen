package com.kdsc.protogen.parsetree.utils.clone;

import com.kdsc.protogen.parsetree.BaseParseTreeNode;

import java.util.List;
import java.util.stream.Collectors;

public class Lists {

    public static <T extends BaseParseTreeNode> List<T> clone(final List<T> inputList) {
        return inputList
            .stream()
            .map(t -> (T)t.clone())
            .collect(Collectors.toList());
    }

}