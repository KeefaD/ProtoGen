package simple;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO:KMD Put max range stuff in here in a base class
public class TestGetters {

    @Test
    public void testGetters() {
        var testDoubleValue = 1d;
        var testFloatValue = 1f;
        var testInt32Value = 1;
        var testInt64Value = 1;
        var testOptionalDoubleValue = Optional.of(1d);
        var testOptionalFloatValue = Optional.of(1f);
        var testOptionalInt32Value = Optional.of(1);
        var testOptionalInt64Value = Optional.of(1L);
        var type = new Type(
            testDoubleValue,
            testFloatValue,
            testInt32Value,
            testInt64Value,
            Optional.of(1d),
            Optional.of(1f),
            Optional.of(1),
            Optional.of(1L)
        );
        assertEquals(testDoubleValue, type.getTestDouble(), "Unexpected value returned from getter");
        assertEquals(testFloatValue, type.getTestFloat(), "Unexpected value returned from getter");
        assertEquals(testInt32Value, type.getTestInt32(), "Unexpected value returned from getter");
        assertEquals(testInt64Value, type.getTestInt64(), "Unexpected value returned from getter");
        assertEquals(testOptionalDoubleValue, type.getTestOptionalDouble(), "Unexpected value returned from getter");
        assertEquals(testOptionalFloatValue, type.getTestOptionalFloat(), "Unexpected value returned from getter");
        assertEquals(testOptionalInt32Value, type.getTestOptionalInt32(), "Unexpected value returned from getter");
        assertEquals(testOptionalInt64Value, type.getTestOptionalInt64(), "Unexpected value returned from getter");
    }

}