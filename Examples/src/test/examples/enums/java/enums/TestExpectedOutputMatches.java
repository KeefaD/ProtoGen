package enums;

import com.kdsc.protogen.examples.BaseExpectedOutputMatchesTest;
import org.junit.jupiter.api.Test;

public class TestExpectedOutputMatches extends BaseExpectedOutputMatchesTest {

    @Test
    public void testExpectedOutputMatches() {
        runCompilerWithTestProgram("enums", "", "enums");
    }

}