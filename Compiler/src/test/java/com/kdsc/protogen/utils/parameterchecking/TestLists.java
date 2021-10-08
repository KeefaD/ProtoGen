package com.kdsc.protogen.utils.parameterchecking;

import com.kdsc.protogen.parsetreenodes.BaseTestNode;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TestLists extends BaseTestNode {

    @Test
    public void TestRequireAtLeastOneZero() {
        assertThrows(IllegalArgumentException.class, () -> Lists.requireAtLeastOne(Collections.emptyList()));
    }

    @Test
    public void TestRequireAtLeastOneNull() {
        assertThrows(NullPointerException.class, () -> Lists.requireAtLeastOne(null));
    }

    @Test
    public void TestRequireAtLeastOneWithOne() {
        Lists.requireAtLeastOne(List.of(1));
    }

    @Test
    public void TestRequireAtLeastOneWithTwo() {
        Lists.requireAtLeastOne(List.of(1, 2));
    }

}