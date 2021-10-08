package com.kdsc.protogen.utils.parameterchecking;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TestNumbers extends BaseTestNode {

    @Test
    public void TestRequireZeroOrGreaterZero() {
        Numbers.requireZeroOrGreater(0);
    }

    @Test
    public void TestRequireZeroOrGreaterOne() {
        Numbers.requireZeroOrGreater(1);
    }

    @Test
    public void TestRequireZeroOrGreaterMinusOne() {
        assertThrows(IllegalArgumentException.class, () -> Numbers.requireZeroOrGreater(-1));
    }

    @Test
    public void TestRequireOneOrGreaterOne() {
        Numbers.requireOneOrGreater(1);
    }

    @Test
    public void TestRequireOneOrGreaterTwo() {
        Numbers.requireOneOrGreater(2);
    }

    @Test
    public void TestRequireOneOrGreaterZero() {
        assertThrows(IllegalArgumentException.class, () -> Numbers.requireOneOrGreater(0));
    }

    @Test
    public void TestRequireOneOrGreaterMinusOne() {
        assertThrows(IllegalArgumentException.class, () -> Numbers.requireOneOrGreater(-1));
    }

}