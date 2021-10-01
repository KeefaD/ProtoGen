package enums;

import com.kdsc.protogen.examples.BaseExpectedOutputMatchesTest;
import org.junit.jupiter.api.Test;

//TODO:KMD need to find a way to get these tests organised in IntelliJ
public class TestExpectedOutputMatches extends BaseExpectedOutputMatchesTest {

    @Test
    public void testExpectedOutputMatches() {
        runCompilerWithTestProgram("enums", "", "enums");
    }

}