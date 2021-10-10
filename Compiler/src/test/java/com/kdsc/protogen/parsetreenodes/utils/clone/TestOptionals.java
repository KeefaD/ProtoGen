package com.kdsc.protogen.parsetreenodes.utils.clone;

import com.kdsc.protogen.parsetreenodes.BaseParseTreeNode;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.TestBoolFieldTypeNode;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestOptionals {

    @Test
    public void testNull() {
        assertThrows(
            NullPointerException.class,
            () ->
            Optionals.clone(null)
        );
    }

    @Test
    public void testEmpty() {
        Optional<BaseParseTreeNode> testOptional = Optional.empty();
        var clonedOptional = Optionals.clone(testOptional);
        assertEquals(Optional.empty(), clonedOptional, "Expected empty optional");
    }

    @Test
    public void testPresent() {
        var testOptional = Optional.of(TestBoolFieldTypeNode.createPopulatedTestNode());
        var clonedOptional = Optionals.clone(testOptional);
        assertEquals(testOptional, clonedOptional, "Expected optional to be equal");
    }

}
