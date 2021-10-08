package collections;

import com.kdsc.protogen.examples.BaseExpectedOutputMatchesTest;
import org.junit.jupiter.api.Test;

public class TestExpectedOutputMatchesForCollectionsExample extends BaseExpectedOutputMatchesTest {

    @Test
    public void testExpectedOutputMatches() {
        runCompilerWithTestProgram("collections", "", "collections");
    }

}