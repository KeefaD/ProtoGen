package com.kdsc.protogen.parsetreenodes.utils.clone;

import com.kdsc.protogen.parsetreenodes.BaseParseTreeNode;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.TestBoolFieldTypeNode;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.TestStringFieldTypeNode;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLists {

    @Test
    public void testNull() {
        assertThrows(
            NullPointerException.class,
            () ->
            Lists.clone(null)
        );
    }

    @Test
    public void testEmpty() {
        List<BaseParseTreeNode> listOfNodes = Collections.emptyList();
        var clonedList = Lists.clone(listOfNodes);
        assertEquals(0, clonedList.size(), "Expected empty list");
    }

    @Test
    public void testTwoEntries() {
        var item1 = TestBoolFieldTypeNode.createPopulatedTestNode();
        var item2 = TestStringFieldTypeNode.createPopulatedTestNode();
        var listOfNodes = List.of(item1, item2);
        var clonedList = Lists.clone(listOfNodes);
        assertEquals(2, clonedList.size(), "Expected two items in list");
        assertEquals(item1, clonedList.get(0), "Expected list entries to be equal");
        assertEquals(item2, clonedList.get(1), "Expected list entries to be equal");
    }

}
