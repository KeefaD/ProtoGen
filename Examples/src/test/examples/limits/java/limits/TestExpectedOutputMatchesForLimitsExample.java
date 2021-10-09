package limits;

import com.kdsc.protogen.examples.BaseExpectedOutputMatchesTest;
import org.junit.jupiter.api.Test;

public final class TestExpectedOutputMatchesForLimitsExample extends BaseExpectedOutputMatchesTest {

    @Test
    public void testExpectedOutputMatches() {
        runCompilerWithTestProgram("limits", "", "limits");
    }

}