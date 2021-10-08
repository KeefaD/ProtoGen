package interfaces;

import com.kdsc.protogen.examples.BaseExpectedOutputMatchesTest;
import org.junit.jupiter.api.Test;

public class TestExpectedOutputMatchesForInterfaceExample extends BaseExpectedOutputMatchesTest {

    @Test
    public void testExpectedOutputMatches() {
        runCompilerWithTestProgram("interfaces", "", "interfaces");
    }

}