package collections;

import com.kdsc.protogen.runtime.ProtoGenType;
import java.util.Optional;

public class Sets implements ProtoGenType {

    private final com.kdsc.protogen.runtime.types.Set<Integer> TestInt32Set;
    private final com.kdsc.protogen.runtime.types.Set<Long> TestInt64Set;
    private final com.kdsc.protogen.runtime.types.Set<Boolean> TestBoolSet;
    private final com.kdsc.protogen.runtime.types.Set<String> TestStringSet;
    private final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Bytes> TestBytesSet;
    private final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Decimal> TestDecimalSet;
    private final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Date> TestDateSet;
    private final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.DateTime> TestDateTimeSet;
    private final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.LocalDate> TestLocalDateSet;
    private final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.LocalDateTime> TestLocalDateTimeSet;
    private final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Map<String, String>> TestMapSet;
    private final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Set<String>> TestSetSet;
    private final com.kdsc.protogen.runtime.types.Set<collections.InnerType> TestObjectSet;
    private final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Array<String>> TestArraySet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<Integer>> TestOptionalInt32Set;
    private final com.kdsc.protogen.runtime.types.Set<Optional<Long>> TestOptionalInt64Set;
    private final com.kdsc.protogen.runtime.types.Set<Optional<Boolean>> TestOptionalBoolSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<String>> TestOptionalStringSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.LocalDate>> TestOptionalLocalDateSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocalDateTimeSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalSetSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<collections.InnerType>> TestOptionalObjectSet;
    private final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArraySet;

    public Sets(
        final com.kdsc.protogen.runtime.types.Set<Integer> TestInt32Set,
        final com.kdsc.protogen.runtime.types.Set<Long> TestInt64Set,
        final com.kdsc.protogen.runtime.types.Set<Boolean> TestBoolSet,
        final com.kdsc.protogen.runtime.types.Set<String> TestStringSet,
        final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Bytes> TestBytesSet,
        final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Decimal> TestDecimalSet,
        final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Date> TestDateSet,
        final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.DateTime> TestDateTimeSet,
        final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.LocalDate> TestLocalDateSet,
        final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.LocalDateTime> TestLocalDateTimeSet,
        final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Map<String, String>> TestMapSet,
        final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Set<String>> TestSetSet,
        final com.kdsc.protogen.runtime.types.Set<collections.InnerType> TestObjectSet,
        final com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Array<String>> TestArraySet,
        final com.kdsc.protogen.runtime.types.Set<Optional<Integer>> TestOptionalInt32Set,
        final com.kdsc.protogen.runtime.types.Set<Optional<Long>> TestOptionalInt64Set,
        final com.kdsc.protogen.runtime.types.Set<Optional<Boolean>> TestOptionalBoolSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<String>> TestOptionalStringSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.LocalDate>> TestOptionalLocalDateSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocalDateTimeSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalSetSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<collections.InnerType>> TestOptionalObjectSet,
        final com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArraySet
    ) {
        this.TestInt32Set = TestInt32Set;
        this.TestInt64Set = TestInt64Set;
        this.TestBoolSet = TestBoolSet;
        this.TestStringSet = TestStringSet;
        this.TestBytesSet = TestBytesSet;
        this.TestDecimalSet = TestDecimalSet;
        this.TestDateSet = TestDateSet;
        this.TestDateTimeSet = TestDateTimeSet;
        this.TestLocalDateSet = TestLocalDateSet;
        this.TestLocalDateTimeSet = TestLocalDateTimeSet;
        this.TestMapSet = TestMapSet;
        this.TestSetSet = TestSetSet;
        this.TestObjectSet = TestObjectSet;
        this.TestArraySet = TestArraySet;
        this.TestOptionalInt32Set = TestOptionalInt32Set;
        this.TestOptionalInt64Set = TestOptionalInt64Set;
        this.TestOptionalBoolSet = TestOptionalBoolSet;
        this.TestOptionalStringSet = TestOptionalStringSet;
        this.TestOptionalBytesSet = TestOptionalBytesSet;
        this.TestOptionalDecimalSet = TestOptionalDecimalSet;
        this.TestOptionalDateSet = TestOptionalDateSet;
        this.TestOptionalDatetimeSet = TestOptionalDatetimeSet;
        this.TestOptionalLocalDateSet = TestOptionalLocalDateSet;
        this.TestOptionalLocalDateTimeSet = TestOptionalLocalDateTimeSet;
        this.TestOptionalMapSet = TestOptionalMapSet;
        this.TestOptionalSetSet = TestOptionalSetSet;
        this.TestOptionalObjectSet = TestOptionalObjectSet;
        this.TestOptionalArraySet = TestOptionalArraySet;
    }

    public com.kdsc.protogen.runtime.types.Set<Integer> getTestInt32Set() {
        return TestInt32Set;
    }

    public com.kdsc.protogen.runtime.types.Set<Long> getTestInt64Set() {
        return TestInt64Set;
    }

    public com.kdsc.protogen.runtime.types.Set<Boolean> getTestBoolSet() {
        return TestBoolSet;
    }

    public com.kdsc.protogen.runtime.types.Set<String> getTestStringSet() {
        return TestStringSet;
    }

    public com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Bytes> getTestBytesSet() {
        return TestBytesSet;
    }

    public com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Decimal> getTestDecimalSet() {
        return TestDecimalSet;
    }

    public com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Date> getTestDateSet() {
        return TestDateSet;
    }

    public com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.DateTime> getTestDateTimeSet() {
        return TestDateTimeSet;
    }

    public com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.LocalDate> getTestLocalDateSet() {
        return TestLocalDateSet;
    }

    public com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.LocalDateTime> getTestLocalDateTimeSet() {
        return TestLocalDateTimeSet;
    }

    public com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Map<String, String>> getTestMapSet() {
        return TestMapSet;
    }

    public com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Set<String>> getTestSetSet() {
        return TestSetSet;
    }

    public com.kdsc.protogen.runtime.types.Set<collections.InnerType> getTestObjectSet() {
        return TestObjectSet;
    }

    public com.kdsc.protogen.runtime.types.Set<com.kdsc.protogen.runtime.types.Array<String>> getTestArraySet() {
        return TestArraySet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<Integer>> getTestOptionalInt32Set() {
        return TestOptionalInt32Set;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<Long>> getTestOptionalInt64Set() {
        return TestOptionalInt64Set;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<Boolean>> getTestOptionalBoolSet() {
        return TestOptionalBoolSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<String>> getTestOptionalStringSet() {
        return TestOptionalStringSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Bytes>> getTestOptionalBytesSet() {
        return TestOptionalBytesSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Decimal>> getTestOptionalDecimalSet() {
        return TestOptionalDecimalSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Date>> getTestOptionalDateSet() {
        return TestOptionalDateSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.DateTime>> getTestOptionalDatetimeSet() {
        return TestOptionalDatetimeSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.LocalDate>> getTestOptionalLocalDateSet() {
        return TestOptionalLocalDateSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> getTestOptionalLocalDateTimeSet() {
        return TestOptionalLocalDateTimeSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> getTestOptionalMapSet() {
        return TestOptionalMapSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Set<String>>> getTestOptionalSetSet() {
        return TestOptionalSetSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<collections.InnerType>> getTestOptionalObjectSet() {
        return TestOptionalObjectSet;
    }

    public com.kdsc.protogen.runtime.types.Set<Optional<com.kdsc.protogen.runtime.types.Array<String>>> getTestOptionalArraySet() {
        return TestOptionalArraySet;
    }

    @Override
    public String toString() {
        return toFormattedString(0);
    }

    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//collections.Sets\n");
        return stringBuilder.toString().indent(indentationLevel * TO_STRING_INDENTATION_LEVEL);
    }

}