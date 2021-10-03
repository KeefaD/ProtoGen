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
        var testBoolValue = false;
        var testStringValue = "Test";
        var testType = new InnerType(1f);
        var testOptionalDoubleValue = Optional.of(1d);
        var testOptionalFloatValue = Optional.of(1f);
        var testOptionalInt32Value = Optional.of(1);
        var testOptionalInt64Value = Optional.of(1L);
        var testOptionalBoolValue = Optional.of(false);
        var testOptionalStringValue = Optional.of("Test");
        var testOptionalTypeValue = Optional.of(new InnerType(1f));
        var type = new Type(
            testDoubleValue,
            testFloatValue,
            testInt32Value,
            testInt64Value,
            testBoolValue,
            testStringValue,
            testType,
            testOptionalDoubleValue,
            testOptionalFloatValue,
            testOptionalInt32Value,
            testOptionalInt64Value,
            testOptionalBoolValue,
            testOptionalStringValue,
            testOptionalTypeValue
        );
        assertEquals(testDoubleValue, type.getTestDouble(), "Unexpected value returned from getter");
        assertEquals(testFloatValue, type.getTestFloat(), "Unexpected value returned from getter");
        assertEquals(testInt32Value, type.getTestInt32(), "Unexpected value returned from getter");
        assertEquals(testInt64Value, type.getTestInt64(), "Unexpected value returned from getter");
        assertEquals(testBoolValue, type.getTestBool(), "Unexpected value returned from getter");
        assertEquals(testStringValue, type.getTestString(), "Unexpected value returned from getter");
        //TODO:KMD This is dodgy but will be ok until we implement equals and hashcode
        assertEquals(testType, type.getTestType(), "Unexpected value returned from getter");
        assertEquals(testOptionalDoubleValue, type.getTestOptionalDouble(), "Unexpected value returned from getter");
        assertEquals(testOptionalFloatValue, type.getTestOptionalFloat(), "Unexpected value returned from getter");
        assertEquals(testOptionalInt32Value, type.getTestOptionalInt32(), "Unexpected value returned from getter");
        assertEquals(testOptionalInt64Value, type.getTestOptionalInt64(), "Unexpected value returned from getter");
        assertEquals(testOptionalBoolValue, type.getTestOptionalBool(), "Unexpected value returned from getter");
        assertEquals(testOptionalStringValue, type.getTestOptionalString(), "Unexpected value returned from getter");
        assertEquals(testOptionalTypeValue, type.getTestOptionalType(), "Unexpected value returned from getter");
    }

}