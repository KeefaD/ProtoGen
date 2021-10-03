package simple;

import com.kdsc.protogen.runtime.types.*;
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
        var testBytes = new Bytes();
        var testDecimal = new Decimal();
        var testDate = new Date();
        var testDateTime = new DateTime();
        var testLocalDateTime = new LocalDateTime();
        var testMap = new Map<Integer, Integer>();
        var testSet = new Set<Integer>();
        var testValueOrError = new ValueOrError<>(1);
        var testType = new InnerType(1f);
        var testArray = new Array<Integer>();
        var testOptionalDouble = Optional.of(1d);
        var testOptionalFloat = Optional.of(1f);
        var testOptionalInt32 = Optional.of(1);
        var testOptionalInt64 = Optional.of(1L);
        var testOptionalBool = Optional.of(false);
        var testOptionalString = Optional.of("Test");
        var testOptionalBytes = Optional.of(new Bytes());
        var testOptionalDecimal = Optional.of(new Decimal());
        var testOptionalDate = Optional.of(new Date());
        var testOptionalDateTime = Optional.of(new DateTime());
        var testOptionalLocalDateTime = Optional.of(new LocalDateTime());
        var testOptionalMap = Optional.of(new Map<Integer, Integer>());
        var testOptionalSet = Optional.of(new Set<Integer>());
        var testOptionalValueOrError = Optional.of(new ValueOrError<>(1));
        var testOptionalType = Optional.of(new InnerType(1f));
        var testOptionalArray = Optional.of(new Array<Integer>());
        var type = new Type(
            testDoubleValue,
            testFloatValue,
            testInt32Value,
            testInt64Value,
            testBoolValue,
            testStringValue,
            testBytes,
            testDecimal,
            testDate,
            testDateTime,
            testLocalDateTime,
            testMap,
            testSet,
            testValueOrError,
            testType,
            testArray,
            testOptionalDouble,
            testOptionalFloat,
            testOptionalInt32,
            testOptionalInt64,
            testOptionalBool,
            testOptionalString,
            testOptionalBytes,
            testOptionalDecimal,
            testOptionalDate,
            testOptionalDateTime,
            testOptionalLocalDateTime,
            testOptionalMap,
            testOptionalSet,
            testOptionalValueOrError,
            testOptionalType,
            testOptionalArray
        );
        assertEquals(testDoubleValue, type.getTestDouble(), "Unexpected value returned from getter");
        assertEquals(testFloatValue, type.getTestFloat(), "Unexpected value returned from getter");
        assertEquals(testInt32Value, type.getTestInt32(), "Unexpected value returned from getter");
        assertEquals(testInt64Value, type.getTestInt64(), "Unexpected value returned from getter");
        assertEquals(testBoolValue, type.getTestBool(), "Unexpected value returned from getter");
        assertEquals(testStringValue, type.getTestString(), "Unexpected value returned from getter");
        //TODO:KMD No point testing Decimal, Date, DateTime, LocalDate time at the moment
        //TODO:KMD This is dodgy but will be ok until we implement equals and hashcode
        assertEquals(testType, type.getTestType(), "Unexpected value returned from getter");
        assertEquals(testOptionalDouble, type.getTestOptionalDouble(), "Unexpected value returned from getter");
        assertEquals(testOptionalFloat, type.getTestOptionalFloat(), "Unexpected value returned from getter");
        assertEquals(testOptionalInt32, type.getTestOptionalInt32(), "Unexpected value returned from getter");
        assertEquals(testOptionalInt64, type.getTestOptionalInt64(), "Unexpected value returned from getter");
        assertEquals(testOptionalBool, type.getTestOptionalBool(), "Unexpected value returned from getter");
        assertEquals(testOptionalString, type.getTestOptionalString(), "Unexpected value returned from getter");
        assertEquals(testOptionalType, type.getTestOptionalType(), "Unexpected value returned from getter");
    }

}