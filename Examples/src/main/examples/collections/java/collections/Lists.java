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

public class Lists implements ProtoGenType {

    private final com.kdsc.protogen.runtime.types.List<Double> TestDoubleList;
    private final com.kdsc.protogen.runtime.types.List<Float> TestFloatList;
    private final com.kdsc.protogen.runtime.types.List<Integer> TestInt32List;
    private final com.kdsc.protogen.runtime.types.List<Long> TestInt64List;
    private final com.kdsc.protogen.runtime.types.List<Boolean> TestBoolList;
    private final com.kdsc.protogen.runtime.types.List<String> TestStringList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Bytes> TestBytesList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Decimal> TestDecimalList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Date> TestDateList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.DateTime> TestDateTimeList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.LocalDate> TestLocalDateList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.LocalDateTime> TestLocalDateTimeList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Map<String, String>> TestMapList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Set<String>> TestListSet;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.List<String>> TestListList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>> TestValueOrErrorList;
    private final com.kdsc.protogen.runtime.types.List<collections.InnerType> TestObjectList;
    private final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Array<String>> TestArrayList;
    private final com.kdsc.protogen.runtime.types.List<Optional<Double>> TestOptionalDoubleList;
    private final com.kdsc.protogen.runtime.types.List<Optional<Float>> TestOptionalFloatList;
    private final com.kdsc.protogen.runtime.types.List<Optional<Integer>> TestOptionalInt32List;
    private final com.kdsc.protogen.runtime.types.List<Optional<Long>> TestOptionalInt64List;
    private final com.kdsc.protogen.runtime.types.List<Optional<Boolean>> TestOptionalBoolList;
    private final com.kdsc.protogen.runtime.types.List<Optional<String>> TestOptionalStringList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.LocalDate>> TestOptionalLocalDateList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocalDateTimeList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalListSet;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.List<String>>> TestOptionalListList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>>> TestOptionalValueOrErrorList;
    private final com.kdsc.protogen.runtime.types.List<Optional<collections.InnerType>> TestOptionalObjectList;
    private final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArrayList;

    public Lists(
        final com.kdsc.protogen.runtime.types.List<Double> TestDoubleList,
        final com.kdsc.protogen.runtime.types.List<Float> TestFloatList,
        final com.kdsc.protogen.runtime.types.List<Integer> TestInt32List,
        final com.kdsc.protogen.runtime.types.List<Long> TestInt64List,
        final com.kdsc.protogen.runtime.types.List<Boolean> TestBoolList,
        final com.kdsc.protogen.runtime.types.List<String> TestStringList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Bytes> TestBytesList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Decimal> TestDecimalList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Date> TestDateList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.DateTime> TestDateTimeList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.LocalDate> TestLocalDateList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.LocalDateTime> TestLocalDateTimeList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Map<String, String>> TestMapList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Set<String>> TestListSet,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.List<String>> TestListList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>> TestValueOrErrorList,
        final com.kdsc.protogen.runtime.types.List<collections.InnerType> TestObjectList,
        final com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Array<String>> TestArrayList,
        final com.kdsc.protogen.runtime.types.List<Optional<Double>> TestOptionalDoubleList,
        final com.kdsc.protogen.runtime.types.List<Optional<Float>> TestOptionalFloatList,
        final com.kdsc.protogen.runtime.types.List<Optional<Integer>> TestOptionalInt32List,
        final com.kdsc.protogen.runtime.types.List<Optional<Long>> TestOptionalInt64List,
        final com.kdsc.protogen.runtime.types.List<Optional<Boolean>> TestOptionalBoolList,
        final com.kdsc.protogen.runtime.types.List<Optional<String>> TestOptionalStringList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.LocalDate>> TestOptionalLocalDateList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocalDateTimeList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalListSet,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.List<String>>> TestOptionalListList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>>> TestOptionalValueOrErrorList,
        final com.kdsc.protogen.runtime.types.List<Optional<collections.InnerType>> TestOptionalObjectList,
        final com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArrayList
    ) {
        this.TestDoubleList = TestDoubleList;
        this.TestFloatList = TestFloatList;
        this.TestInt32List = TestInt32List;
        this.TestInt64List = TestInt64List;
        this.TestBoolList = TestBoolList;
        this.TestStringList = TestStringList;
        this.TestBytesList = TestBytesList;
        this.TestDecimalList = TestDecimalList;
        this.TestDateList = TestDateList;
        this.TestDateTimeList = TestDateTimeList;
        this.TestLocalDateList = TestLocalDateList;
        this.TestLocalDateTimeList = TestLocalDateTimeList;
        this.TestMapList = TestMapList;
        this.TestListSet = TestListSet;
        this.TestListList = TestListList;
        this.TestValueOrErrorList = TestValueOrErrorList;
        this.TestObjectList = TestObjectList;
        this.TestArrayList = TestArrayList;
        this.TestOptionalDoubleList = TestOptionalDoubleList;
        this.TestOptionalFloatList = TestOptionalFloatList;
        this.TestOptionalInt32List = TestOptionalInt32List;
        this.TestOptionalInt64List = TestOptionalInt64List;
        this.TestOptionalBoolList = TestOptionalBoolList;
        this.TestOptionalStringList = TestOptionalStringList;
        this.TestOptionalBytesList = TestOptionalBytesList;
        this.TestOptionalDecimalList = TestOptionalDecimalList;
        this.TestOptionalDateList = TestOptionalDateList;
        this.TestOptionalDatetimeList = TestOptionalDatetimeList;
        this.TestOptionalLocalDateList = TestOptionalLocalDateList;
        this.TestOptionalLocalDateTimeList = TestOptionalLocalDateTimeList;
        this.TestOptionalMapList = TestOptionalMapList;
        this.TestOptionalListSet = TestOptionalListSet;
        this.TestOptionalListList = TestOptionalListList;
        this.TestOptionalValueOrErrorList = TestOptionalValueOrErrorList;
        this.TestOptionalObjectList = TestOptionalObjectList;
        this.TestOptionalArrayList = TestOptionalArrayList;
    }

    public com.kdsc.protogen.runtime.types.List<Double> getTestDoubleList() {
        return TestDoubleList;
    }

    public com.kdsc.protogen.runtime.types.List<Float> getTestFloatList() {
        return TestFloatList;
    }

    public com.kdsc.protogen.runtime.types.List<Integer> getTestInt32List() {
        return TestInt32List;
    }

    public com.kdsc.protogen.runtime.types.List<Long> getTestInt64List() {
        return TestInt64List;
    }

    public com.kdsc.protogen.runtime.types.List<Boolean> getTestBoolList() {
        return TestBoolList;
    }

    public com.kdsc.protogen.runtime.types.List<String> getTestStringList() {
        return TestStringList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Bytes> getTestBytesList() {
        return TestBytesList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Decimal> getTestDecimalList() {
        return TestDecimalList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Date> getTestDateList() {
        return TestDateList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.DateTime> getTestDateTimeList() {
        return TestDateTimeList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.LocalDate> getTestLocalDateList() {
        return TestLocalDateList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.LocalDateTime> getTestLocalDateTimeList() {
        return TestLocalDateTimeList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Map<String, String>> getTestMapList() {
        return TestMapList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Set<String>> getTestListSet() {
        return TestListSet;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.List<String>> getTestListList() {
        return TestListList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>> getTestValueOrErrorList() {
        return TestValueOrErrorList;
    }

    public com.kdsc.protogen.runtime.types.List<collections.InnerType> getTestObjectList() {
        return TestObjectList;
    }

    public com.kdsc.protogen.runtime.types.List<com.kdsc.protogen.runtime.types.Array<String>> getTestArrayList() {
        return TestArrayList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<Double>> getTestOptionalDoubleList() {
        return TestOptionalDoubleList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<Float>> getTestOptionalFloatList() {
        return TestOptionalFloatList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<Integer>> getTestOptionalInt32List() {
        return TestOptionalInt32List;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<Long>> getTestOptionalInt64List() {
        return TestOptionalInt64List;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<Boolean>> getTestOptionalBoolList() {
        return TestOptionalBoolList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<String>> getTestOptionalStringList() {
        return TestOptionalStringList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Bytes>> getTestOptionalBytesList() {
        return TestOptionalBytesList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Decimal>> getTestOptionalDecimalList() {
        return TestOptionalDecimalList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Date>> getTestOptionalDateList() {
        return TestOptionalDateList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.DateTime>> getTestOptionalDatetimeList() {
        return TestOptionalDatetimeList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.LocalDate>> getTestOptionalLocalDateList() {
        return TestOptionalLocalDateList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> getTestOptionalLocalDateTimeList() {
        return TestOptionalLocalDateTimeList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> getTestOptionalMapList() {
        return TestOptionalMapList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Set<String>>> getTestOptionalListSet() {
        return TestOptionalListSet;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.List<String>>> getTestOptionalListList() {
        return TestOptionalListList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>>> getTestOptionalValueOrErrorList() {
        return TestOptionalValueOrErrorList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<collections.InnerType>> getTestOptionalObjectList() {
        return TestOptionalObjectList;
    }

    public com.kdsc.protogen.runtime.types.List<Optional<com.kdsc.protogen.runtime.types.Array<String>>> getTestOptionalArrayList() {
        return TestOptionalArrayList;
    }

    @Override
    public String toString() {
        return toFormattedString(ToStringOptions.defaultToStringOptions, 0);
    }

    public String toFormattedString(final ToStringOptions toStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//collections.Lists\n");
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