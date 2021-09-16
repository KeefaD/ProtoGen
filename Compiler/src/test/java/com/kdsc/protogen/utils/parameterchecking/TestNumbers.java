package com.kdsc.protogen.utils.parameterchecking;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNumbers extends BaseTestNode {

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

}
