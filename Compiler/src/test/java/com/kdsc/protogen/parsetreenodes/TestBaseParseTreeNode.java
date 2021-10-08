package com.kdsc.protogen.parsetreenodes;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TestBaseParseTreeNode extends BaseTestNode {

    @Test
    public void testSuperToFormattedStringSuperWithNoBaseClassThrowsException() {

        var baseParseTreeNodeMock = Mockito.mock(BaseParseTreeNode.class, Mockito.withSettings().useConstructor("TestFileName.pg", 1L, 0L).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        assertThrows(
            UnsupportedOperationException.class,
            () -> baseParseTreeNodeMock.superToFormattedStringSuper(null, null, null)
        );
    }

}