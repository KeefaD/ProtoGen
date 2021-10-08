package com.kdsc.protogen.utils.parameterchecking;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestOptionals extends BaseTestNode {

    @Test
    public void TestRequireOneEmpty() {
        assertThrows(IllegalArgumentException.class, Optionals::requireOne);
    }

    @Test
    public void TestRequireOneMoreThanOne() {
        assertThrows(IllegalArgumentException.class, () -> Optionals.requireOne(Optional.of(1), Optional.of(1)));
    }

    @Test
    public void TestRequireOneMoreThanOneDifferentTypes() {
        assertThrows(IllegalArgumentException.class, () -> Optionals.requireOne(Optional.of(1), Optional.of("TestString")));
    }

    @Test
    public void TestRequireOneHappyPathOne() {
        Optionals.requireOne(Optional.of(1));
    }

    @Test
    public void TestRequireOneHappyPathTwo() {
        Optionals.requireOne(Optional.of(1), Optional.empty());
    }

    @Test
    public void TestRequireOneHappyPathThree() {
        Optionals.requireOne(Optional.of(1), Optional.empty(), Optional.empty());
    }

    @Test
    public void TestRequireOneNull() {
        assertThrows(NullPointerException.class, () -> Optionals.requireOne(null));
    }

}