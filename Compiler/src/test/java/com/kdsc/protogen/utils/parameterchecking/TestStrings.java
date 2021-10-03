package com.kdsc.protogen.utils.parameterchecking;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestStrings extends BaseTestNode {

    @Test
    public void TestRequireNonBlankNull() {
        assertThrows(IllegalArgumentException.class, () -> Strings.requireNonBlank(null));
    }

    @Test
    public void TestRequireNonBlankEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Strings.requireNonBlank(""));
    }

    @Test
    public void TestRequireNonBlankSpace() {
        assertThrows(IllegalArgumentException.class, () -> Strings.requireNonBlank(" "));
    }

    @Test
    public void TestRequireNonBlankTab() {
        assertThrows(IllegalArgumentException.class, () -> Strings.requireNonBlank("\t"));
    }

    @Test
    public void TestRequireNonBlankNewLine() {
        assertThrows(IllegalArgumentException.class, () -> Strings.requireNonBlank("\n"));
    }

    @Test
    public void TestRequireNonBlankHappyPath() {
        Strings.requireNonBlank("String");
    }

}