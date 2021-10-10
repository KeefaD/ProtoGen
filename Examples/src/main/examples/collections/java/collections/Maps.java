//
//   ╔═╗┬─┐┌─┐┌┬┐┌─┐╔═╗┌─┐┌┐┌
//   ╠═╝├┬┘│ │ │ │ │║ ╦├┤ │││
//   ╩  ┴└─└─┘ ┴ └─┘╚═╝└─┘┘└┘   v0.0 2021
//
package collections;

import com.kdsc.protogen.runtime.ProtoGenType;
import com.kdsc.protogen.runtime.options.EqualsHashCodeOptions;
import com.kdsc.protogen.runtime.options.ToStringOptions;
import java.util.Optional;

public class Maps implements ProtoGenType {

    private final com.kdsc.protogen.runtime.types.Map<Integer, Integer> TestInt32Map;
    private final com.kdsc.protogen.runtime.types.Map<Long, Long> TestInt64Map;
    private final com.kdsc.protogen.runtime.types.Map<Boolean, Boolean> TestBoolMap;
    private final com.kdsc.protogen.runtime.types.Map<String, String> TestStringMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Bytes, com.kdsc.protogen.runtime.types.Bytes> TestBytesMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Decimal, com.kdsc.protogen.runtime.types.Decimal> TestDecimalMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Date, com.kdsc.protogen.runtime.types.Date> TestDateMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.DateTime, com.kdsc.protogen.runtime.types.DateTime> TestDateTimeMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.LocalDate, com.kdsc.protogen.runtime.types.LocalDate> TestLocalDateMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.LocalDateTime, com.kdsc.protogen.runtime.types.LocalDateTime> TestLocalDateTimeMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Map<String, String>, com.kdsc.protogen.runtime.types.Map<String, String>> TestMapMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Set<String>, com.kdsc.protogen.runtime.types.Set<String>> TestSetMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.List<String>, com.kdsc.protogen.runtime.types.List<String>> TestListMap;
    private final com.kdsc.protogen.runtime.types.Map<collections.InnerType, collections.InnerType> TestObjectMap;
    private final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Array<String>, com.kdsc.protogen.runtime.types.Array<String>> TestArrayMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<Integer>, Optional<Integer>> TestOptionalInt32Map;
    private final com.kdsc.protogen.runtime.types.Map<Optional<Long>, Optional<Long>> TestOptionalInt64Map;
    private final com.kdsc.protogen.runtime.types.Map<Optional<Boolean>, Optional<Boolean>> TestOptionalBoolMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<String>, Optional<String>> TestOptionalStringMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Bytes>, Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Decimal>, Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Date>, Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateSetMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.DateTime>, Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.LocalDate>, Optional<com.kdsc.protogen.runtime.types.LocalDate>> TestOptionalLocalDateMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>, Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocalDateTimeMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>, Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Set<String>>, Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalSetMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.List<String>>, Optional<com.kdsc.protogen.runtime.types.List<String>>> TestOptionalListMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<collections.InnerType>, Optional<collections.InnerType>> TestOptionalObjectMap;
    private final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Array<String>>, Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArrayMap;

    public Maps(
        final com.kdsc.protogen.runtime.types.Map<Integer, Integer> TestInt32Map,
        final com.kdsc.protogen.runtime.types.Map<Long, Long> TestInt64Map,
        final com.kdsc.protogen.runtime.types.Map<Boolean, Boolean> TestBoolMap,
        final com.kdsc.protogen.runtime.types.Map<String, String> TestStringMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Bytes, com.kdsc.protogen.runtime.types.Bytes> TestBytesMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Decimal, com.kdsc.protogen.runtime.types.Decimal> TestDecimalMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Date, com.kdsc.protogen.runtime.types.Date> TestDateMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.DateTime, com.kdsc.protogen.runtime.types.DateTime> TestDateTimeMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.LocalDate, com.kdsc.protogen.runtime.types.LocalDate> TestLocalDateMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.LocalDateTime, com.kdsc.protogen.runtime.types.LocalDateTime> TestLocalDateTimeMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Map<String, String>, com.kdsc.protogen.runtime.types.Map<String, String>> TestMapMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Set<String>, com.kdsc.protogen.runtime.types.Set<String>> TestSetMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.List<String>, com.kdsc.protogen.runtime.types.List<String>> TestListMap,
        final com.kdsc.protogen.runtime.types.Map<collections.InnerType, collections.InnerType> TestObjectMap,
        final com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Array<String>, com.kdsc.protogen.runtime.types.Array<String>> TestArrayMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<Integer>, Optional<Integer>> TestOptionalInt32Map,
        final com.kdsc.protogen.runtime.types.Map<Optional<Long>, Optional<Long>> TestOptionalInt64Map,
        final com.kdsc.protogen.runtime.types.Map<Optional<Boolean>, Optional<Boolean>> TestOptionalBoolMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<String>, Optional<String>> TestOptionalStringMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Bytes>, Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Decimal>, Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Date>, Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateSetMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.DateTime>, Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.LocalDate>, Optional<com.kdsc.protogen.runtime.types.LocalDate>> TestOptionalLocalDateMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>, Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocalDateTimeMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>, Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Set<String>>, Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalSetMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.List<String>>, Optional<com.kdsc.protogen.runtime.types.List<String>>> TestOptionalListMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<collections.InnerType>, Optional<collections.InnerType>> TestOptionalObjectMap,
        final com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Array<String>>, Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArrayMap
    ) {
        this.TestInt32Map = TestInt32Map;
        this.TestInt64Map = TestInt64Map;
        this.TestBoolMap = TestBoolMap;
        this.TestStringMap = TestStringMap;
        this.TestBytesMap = TestBytesMap;
        this.TestDecimalMap = TestDecimalMap;
        this.TestDateMap = TestDateMap;
        this.TestDateTimeMap = TestDateTimeMap;
        this.TestLocalDateMap = TestLocalDateMap;
        this.TestLocalDateTimeMap = TestLocalDateTimeMap;
        this.TestMapMap = TestMapMap;
        this.TestSetMap = TestSetMap;
        this.TestListMap = TestListMap;
        this.TestObjectMap = TestObjectMap;
        this.TestArrayMap = TestArrayMap;
        this.TestOptionalInt32Map = TestOptionalInt32Map;
        this.TestOptionalInt64Map = TestOptionalInt64Map;
        this.TestOptionalBoolMap = TestOptionalBoolMap;
        this.TestOptionalStringMap = TestOptionalStringMap;
        this.TestOptionalBytesMap = TestOptionalBytesMap;
        this.TestOptionalDecimalMap = TestOptionalDecimalMap;
        this.TestOptionalDateSetMap = TestOptionalDateSetMap;
        this.TestOptionalDatetimeMap = TestOptionalDatetimeMap;
        this.TestOptionalLocalDateMap = TestOptionalLocalDateMap;
        this.TestOptionalLocalDateTimeMap = TestOptionalLocalDateTimeMap;
        this.TestOptionalMapMap = TestOptionalMapMap;
        this.TestOptionalSetMap = TestOptionalSetMap;
        this.TestOptionalListMap = TestOptionalListMap;
        this.TestOptionalObjectMap = TestOptionalObjectMap;
        this.TestOptionalArrayMap = TestOptionalArrayMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Integer, Integer> getTestInt32Map() {
        return TestInt32Map;
    }

    public com.kdsc.protogen.runtime.types.Map<Long, Long> getTestInt64Map() {
        return TestInt64Map;
    }

    public com.kdsc.protogen.runtime.types.Map<Boolean, Boolean> getTestBoolMap() {
        return TestBoolMap;
    }

    public com.kdsc.protogen.runtime.types.Map<String, String> getTestStringMap() {
        return TestStringMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Bytes, com.kdsc.protogen.runtime.types.Bytes> getTestBytesMap() {
        return TestBytesMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Decimal, com.kdsc.protogen.runtime.types.Decimal> getTestDecimalMap() {
        return TestDecimalMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Date, com.kdsc.protogen.runtime.types.Date> getTestDateMap() {
        return TestDateMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.DateTime, com.kdsc.protogen.runtime.types.DateTime> getTestDateTimeMap() {
        return TestDateTimeMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.LocalDate, com.kdsc.protogen.runtime.types.LocalDate> getTestLocalDateMap() {
        return TestLocalDateMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.LocalDateTime, com.kdsc.protogen.runtime.types.LocalDateTime> getTestLocalDateTimeMap() {
        return TestLocalDateTimeMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Map<String, String>, com.kdsc.protogen.runtime.types.Map<String, String>> getTestMapMap() {
        return TestMapMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Set<String>, com.kdsc.protogen.runtime.types.Set<String>> getTestSetMap() {
        return TestSetMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.List<String>, com.kdsc.protogen.runtime.types.List<String>> getTestListMap() {
        return TestListMap;
    }

    public com.kdsc.protogen.runtime.types.Map<collections.InnerType, collections.InnerType> getTestObjectMap() {
        return TestObjectMap;
    }

    public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Array<String>, com.kdsc.protogen.runtime.types.Array<String>> getTestArrayMap() {
        return TestArrayMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<Integer>, Optional<Integer>> getTestOptionalInt32Map() {
        return TestOptionalInt32Map;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<Long>, Optional<Long>> getTestOptionalInt64Map() {
        return TestOptionalInt64Map;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<Boolean>, Optional<Boolean>> getTestOptionalBoolMap() {
        return TestOptionalBoolMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<String>, Optional<String>> getTestOptionalStringMap() {
        return TestOptionalStringMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Bytes>, Optional<com.kdsc.protogen.runtime.types.Bytes>> getTestOptionalBytesMap() {
        return TestOptionalBytesMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Decimal>, Optional<com.kdsc.protogen.runtime.types.Decimal>> getTestOptionalDecimalMap() {
        return TestOptionalDecimalMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Date>, Optional<com.kdsc.protogen.runtime.types.Date>> getTestOptionalDateSetMap() {
        return TestOptionalDateSetMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.DateTime>, Optional<com.kdsc.protogen.runtime.types.DateTime>> getTestOptionalDatetimeMap() {
        return TestOptionalDatetimeMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.LocalDate>, Optional<com.kdsc.protogen.runtime.types.LocalDate>> getTestOptionalLocalDateMap() {
        return TestOptionalLocalDateMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>, Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> getTestOptionalLocalDateTimeMap() {
        return TestOptionalLocalDateTimeMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>, Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> getTestOptionalMapMap() {
        return TestOptionalMapMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Set<String>>, Optional<com.kdsc.protogen.runtime.types.Set<String>>> getTestOptionalSetMap() {
        return TestOptionalSetMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.List<String>>, Optional<com.kdsc.protogen.runtime.types.List<String>>> getTestOptionalListMap() {
        return TestOptionalListMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<collections.InnerType>, Optional<collections.InnerType>> getTestOptionalObjectMap() {
        return TestOptionalObjectMap;
    }

    public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Array<String>>, Optional<com.kdsc.protogen.runtime.types.Array<String>>> getTestOptionalArrayMap() {
        return TestOptionalArrayMap;
    }

    @Override
    public String toString() {
        return toFormattedString(ToStringOptions.defaultToStringOptions, 0);
    }

    public String toFormattedString(final ToStringOptions toStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//collections.Maps\n");
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