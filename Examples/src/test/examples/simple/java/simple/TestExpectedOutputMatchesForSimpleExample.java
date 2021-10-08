package simple;

import com.kdsc.protogen.examples.BaseExpectedOutputMatchesTest;
import org.junit.jupiter.api.Test;

public class TestExpectedOutputMatchesForSimpleExample extends BaseExpectedOutputMatchesTest {

    @Test
    public void testExpectedOutputMatches() {
        runCompilerWithTestProgram("simple", "", "simple");
    }

}