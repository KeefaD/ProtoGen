package simple;

import com.kdsc.protogen.runtime.options.EqualsHashCodeOptions;
import com.kdsc.protogen.runtime.ProtoGenType;
import java.util.Optional;
import com.kdsc.protogen.runtime.options.ToStringOptions;

public class Type implements ProtoGenType {

    private final double TestDouble;
    private final float TestFloat;
    private final int TestInt32;
    private final long TestInt64;
    private final boolean TestBool;
    private final String TestString;
    private final com.kdsc.protogen.runtime.types.Bytes TestBytes;
    private final com.kdsc.protogen.runtime.types.Decimal TestDecimal;
    private final com.kdsc.protogen.runtime.types.Date TestDate;
    private final com.kdsc.protogen.runtime.types.DateTime TestDateTime;
    private final com.kdsc.protogen.runtime.types.LocalDate TestLocalDate;
    private final com.kdsc.protogen.runtime.types.LocalDateTime TestLocalDateTime;
    private final com.kdsc.protogen.runtime.types.Map<Integer, Integer> TestMap;
    private final com.kdsc.protogen.runtime.types.Set<Integer> TestSet;
    private final com.kdsc.protogen.runtime.types.List<Integer> TestList;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Integer> TestValueOrError;
    private final simple.InnerType TestType;
    private final com.kdsc.protogen.runtime.types.Array<Integer> TestArray;
    private final Optional<Double> TestOptionalDouble;
    private final Optional<Float> TestOptionalFloat;
    private final Optional<Integer> TestOptionalInt32;
    private final Optional<Long> TestOptionalInt64;
    private final Optional<Boolean> TestOptionalBool;
    private final Optional<String> TestOptionalString;
    private final Optional<com.kdsc.protogen.runtime.types.Bytes> TestOptionalBytes;
    private final Optional<com.kdsc.protogen.runtime.types.Decimal> TestOptionalDecimal;
    private final Optional<com.kdsc.protogen.runtime.types.Date> TestOptionalDate;
    private final Optional<com.kdsc.protogen.runtime.types.DateTime> TestOptionalDatetime;
    private final Optional<com.kdsc.protogen.runtime.types.LocalDate> TestOptionalLocalDate;
    private final Optional<com.kdsc.protogen.runtime.types.LocalDateTime> TestOptionalLocalDateTime;
    private final Optional<com.kdsc.protogen.runtime.types.Map<Integer, Integer>> TestOptionalMap;
    private final Optional<com.kdsc.protogen.runtime.types.Set<Integer>> TestOptionalSet;
    private final Optional<com.kdsc.protogen.runtime.types.List<Integer>> TestOptionalList;
    private final Optional<com.kdsc.protogen.runtime.types.ValueOrError<Integer>> TestOptionalValueOrError;
    private final Optional<simple.InnerType> TestOptionalType;
    private final Optional<com.kdsc.protogen.runtime.types.Array<Integer>> TestOptionalArray;

    public Type(
        final double TestDouble,
        final float TestFloat,
        final int TestInt32,
        final long TestInt64,
        final boolean TestBool,
        final String TestString,
        final com.kdsc.protogen.runtime.types.Bytes TestBytes,
        final com.kdsc.protogen.runtime.types.Decimal TestDecimal,
        final com.kdsc.protogen.runtime.types.Date TestDate,
        final com.kdsc.protogen.runtime.types.DateTime TestDateTime,
        final com.kdsc.protogen.runtime.types.LocalDate TestLocalDate,
        final com.kdsc.protogen.runtime.types.LocalDateTime TestLocalDateTime,
        final com.kdsc.protogen.runtime.types.Map<Integer, Integer> TestMap,
        final com.kdsc.protogen.runtime.types.Set<Integer> TestSet,
        final com.kdsc.protogen.runtime.types.List<Integer> TestList,
        final com.kdsc.protogen.runtime.types.ValueOrError<Integer> TestValueOrError,
        final simple.InnerType TestType,
        final com.kdsc.protogen.runtime.types.Array<Integer> TestArray,
        final Optional<Double> TestOptionalDouble,
        final Optional<Float> TestOptionalFloat,
        final Optional<Integer> TestOptionalInt32,
        final Optional<Long> TestOptionalInt64,
        final Optional<Boolean> TestOptionalBool,
        final Optional<String> TestOptionalString,
        final Optional<com.kdsc.protogen.runtime.types.Bytes> TestOptionalBytes,
        final Optional<com.kdsc.protogen.runtime.types.Decimal> TestOptionalDecimal,
        final Optional<com.kdsc.protogen.runtime.types.Date> TestOptionalDate,
        final Optional<com.kdsc.protogen.runtime.types.DateTime> TestOptionalDatetime,
        final Optional<com.kdsc.protogen.runtime.types.LocalDate> TestOptionalLocalDate,
        final Optional<com.kdsc.protogen.runtime.types.LocalDateTime> TestOptionalLocalDateTime,
        final Optional<com.kdsc.protogen.runtime.types.Map<Integer, Integer>> TestOptionalMap,
        final Optional<com.kdsc.protogen.runtime.types.Set<Integer>> TestOptionalSet,
        final Optional<com.kdsc.protogen.runtime.types.List<Integer>> TestOptionalList,
        final Optional<com.kdsc.protogen.runtime.types.ValueOrError<Integer>> TestOptionalValueOrError,
        final Optional<simple.InnerType> TestOptionalType,
        final Optional<com.kdsc.protogen.runtime.types.Array<Integer>> TestOptionalArray
    ) {
        this.TestDouble = TestDouble;
        this.TestFloat = TestFloat;
        this.TestInt32 = TestInt32;
        this.TestInt64 = TestInt64;
        this.TestBool = TestBool;
        this.TestString = TestString;
        this.TestBytes = TestBytes;
        this.TestDecimal = TestDecimal;
        this.TestDate = TestDate;
        this.TestDateTime = TestDateTime;
        this.TestLocalDate = TestLocalDate;
        this.TestLocalDateTime = TestLocalDateTime;
        this.TestMap = TestMap;
        this.TestSet = TestSet;
        this.TestList = TestList;
        this.TestValueOrError = TestValueOrError;
        this.TestType = TestType;
        this.TestArray = TestArray;
        this.TestOptionalDouble = TestOptionalDouble;
        this.TestOptionalFloat = TestOptionalFloat;
        this.TestOptionalInt32 = TestOptionalInt32;
        this.TestOptionalInt64 = TestOptionalInt64;
        this.TestOptionalBool = TestOptionalBool;
        this.TestOptionalString = TestOptionalString;
        this.TestOptionalBytes = TestOptionalBytes;
        this.TestOptionalDecimal = TestOptionalDecimal;
        this.TestOptionalDate = TestOptionalDate;
        this.TestOptionalDatetime = TestOptionalDatetime;
        this.TestOptionalLocalDate = TestOptionalLocalDate;
        this.TestOptionalLocalDateTime = TestOptionalLocalDateTime;
        this.TestOptionalMap = TestOptionalMap;
        this.TestOptionalSet = TestOptionalSet;
        this.TestOptionalList = TestOptionalList;
        this.TestOptionalValueOrError = TestOptionalValueOrError;
        this.TestOptionalType = TestOptionalType;
        this.TestOptionalArray = TestOptionalArray;
    }

    public double getTestDouble() {
        return TestDouble;
    }

    public float getTestFloat() {
        return TestFloat;
    }

    public int getTestInt32() {
        return TestInt32;
    }

    public long getTestInt64() {
        return TestInt64;
    }

    public boolean getTestBool() {
        return TestBool;
    }

    public String getTestString() {
        return TestString;
    }

    public com.kdsc.protogen.runtime.types.Bytes getTestBytes() {
        return TestBytes;
    }

    public com.kdsc.protogen.runtime.types.Decimal getTestDecimal() {
        return TestDecimal;
    }

    public com.kdsc.protogen.runtime.types.Date getTestDate() {
        return TestDate;
    }

    public com.kdsc.protogen.runtime.types.DateTime getTestDateTime() {
        return TestDateTime;
    }

    public com.kdsc.protogen.runtime.types.LocalDate getTestLocalDate() {
        return TestLocalDate;
    }

    public com.kdsc.protogen.runtime.types.LocalDateTime getTestLocalDateTime() {
        return TestLocalDateTime;
    }

    public com.kdsc.protogen.runtime.types.Map<Integer, Integer> getTestMap() {
        return TestMap;
    }

    public com.kdsc.protogen.runtime.types.Set<Integer> getTestSet() {
        return TestSet;
    }

    public com.kdsc.protogen.runtime.types.List<Integer> getTestList() {
        return TestList;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Integer> getTestValueOrError() {
        return TestValueOrError;
    }

    public simple.InnerType getTestType() {
        return TestType;
    }

    public com.kdsc.protogen.runtime.types.Array<Integer> getTestArray() {
        return TestArray;
    }

    public Optional<Double> getTestOptionalDouble() {
        return TestOptionalDouble;
    }

    public Optional<Float> getTestOptionalFloat() {
        return TestOptionalFloat;
    }

    public Optional<Integer> getTestOptionalInt32() {
        return TestOptionalInt32;
    }

    public Optional<Long> getTestOptionalInt64() {
        return TestOptionalInt64;
    }

    public Optional<Boolean> getTestOptionalBool() {
        return TestOptionalBool;
    }

    public Optional<String> getTestOptionalString() {
        return TestOptionalString;
    }

    public Optional<com.kdsc.protogen.runtime.types.Bytes> getTestOptionalBytes() {
        return TestOptionalBytes;
    }

    public Optional<com.kdsc.protogen.runtime.types.Decimal> getTestOptionalDecimal() {
        return TestOptionalDecimal;
    }

    public Optional<com.kdsc.protogen.runtime.types.Date> getTestOptionalDate() {
        return TestOptionalDate;
    }

    public Optional<com.kdsc.protogen.runtime.types.DateTime> getTestOptionalDatetime() {
        return TestOptionalDatetime;
    }

    public Optional<com.kdsc.protogen.runtime.types.LocalDate> getTestOptionalLocalDate() {
        return TestOptionalLocalDate;
    }

    public Optional<com.kdsc.protogen.runtime.types.LocalDateTime> getTestOptionalLocalDateTime() {
        return TestOptionalLocalDateTime;
    }

    public Optional<com.kdsc.protogen.runtime.types.Map<Integer, Integer>> getTestOptionalMap() {
        return TestOptionalMap;
    }

    public Optional<com.kdsc.protogen.runtime.types.Set<Integer>> getTestOptionalSet() {
        return TestOptionalSet;
    }

    public Optional<com.kdsc.protogen.runtime.types.List<Integer>> getTestOptionalList() {
        return TestOptionalList;
    }

    public Optional<com.kdsc.protogen.runtime.types.ValueOrError<Integer>> getTestOptionalValueOrError() {
        return TestOptionalValueOrError;
    }

    public Optional<simple.InnerType> getTestOptionalType() {
        return TestOptionalType;
    }

    public Optional<com.kdsc.protogen.runtime.types.Array<Integer>> getTestOptionalArray() {
        return TestOptionalArray;
    }

    @Override
    public String toString() {
        return toFormattedString(ToStringOptions.defaultToStringOptions, 0);
    }

    public String toFormattedString(final ToStringOptions toStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//simple.Type\n");
        return stringBuilder.toString().indent(indentationLevel * TO_STRING_INDENTATION_LEVEL);
    }

    @Override
    public boolean equals(final Object object) {
        return equals(EqualsHashCodeOptions.defaultEqualsHashCodeOptions, object);
    }

    public boolean equals(final EqualsHashCodeOptions equalsHashCodeOptions, final Object object) {
        return false;
    }

}