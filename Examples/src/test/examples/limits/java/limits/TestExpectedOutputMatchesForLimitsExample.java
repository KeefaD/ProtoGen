package limits;

import com.kdsc.protogen.examples.BaseExpectedOutputMatchesTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public final class TestExpectedOutputMatchesForLimitsExample extends BaseExpectedOutputMatchesTest {

    @Disabled //Will be too slow to run all the time
    @Test
    public void testExpectedOutputMatches() {
        runCompilerWithTestProgram("limits", "", "limits");
    }

}