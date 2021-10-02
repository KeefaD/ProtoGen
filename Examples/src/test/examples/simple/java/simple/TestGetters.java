package simple;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO:KMD Put max range stuff in here in a base class
public class TestGetters {

    @Test
    public void testGetters() {
        var testInt32Value = 1;
        var testInt64Value = 1;
        var testOptionalInt32Value = Optional.of(1);
        var testOptionalInt64Value = Optional.of(1L);
        var type = new Type(
            testInt32Value,
            testInt64Value,
            testOptionalInt32Value,
            testOptionalInt64Value
        );
        assertEquals(testInt32Value, type.getTestInt32(), "Unexpected value returned from getter");
        assertEquals(testInt64Value, type.getTestInt64(), "Unexpected value returned from getter");
        assertEquals(testOptionalInt32Value, type.getTestOptionalInt32(), "Unexpected value returned from getter");
        assertEquals(testOptionalInt64Value, type.getTestOptionalInt64(), "Unexpected value returned from getter");
    }

}