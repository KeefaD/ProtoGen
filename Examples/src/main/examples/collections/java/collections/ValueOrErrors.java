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

public class ValueOrErrors implements ProtoGenType {

    private final com.kdsc.protogen.runtime.types.ValueOrError<Double> TestDoubleValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Float> TestFloatValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Integer> TestInt32ValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Long> TestInt64ValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Boolean> TestBoolValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<String> TestStringValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Bytes> TestBytesValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal> TestDecimalValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Date> TestDateValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.DateTime> TestDateTimeValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.LocalDate> TestLocalDateValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.LocalDateTime> TestLocalDateTimeValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Map<String, String>> TestMapValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Set<String>> TestSetValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.List<String>> TestListValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>> TestValueOrErrorValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<collections.ValueOrErrors> TestObjectValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Array<String>> TestArrayValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Double>> TestOptionalDoubleValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Float>> TestOptionalFloatValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Integer>> TestOptionalInt32ValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Long>> TestOptionalInt64ValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Boolean>> TestOptionalBoolValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<String>> TestOptionalStringValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.LocalDate>> TestOptionalLocalDateValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocalDateTimeValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalSetValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.List<String>>> TestOptionalListValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>>> TestOptionalValueOrErrorValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<collections.ValueOrErrors>> TestOptionalObjectValueOrError;
    private final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArrayValueOrError;

    public ValueOrErrors(
        final com.kdsc.protogen.runtime.types.ValueOrError<Double> TestDoubleValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Float> TestFloatValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Integer> TestInt32ValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Long> TestInt64ValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Boolean> TestBoolValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<String> TestStringValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Bytes> TestBytesValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal> TestDecimalValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Date> TestDateValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.DateTime> TestDateTimeValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.LocalDate> TestLocalDateValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.LocalDateTime> TestLocalDateTimeValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Map<String, String>> TestMapValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Set<String>> TestSetValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.List<String>> TestListValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>> TestValueOrErrorValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<collections.ValueOrErrors> TestObjectValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Array<String>> TestArrayValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Double>> TestOptionalDoubleValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Float>> TestOptionalFloatValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Integer>> TestOptionalInt32ValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Long>> TestOptionalInt64ValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<Boolean>> TestOptionalBoolValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<String>> TestOptionalStringValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.LocalDate>> TestOptionalLocalDateValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocalDateTimeValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalSetValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.List<String>>> TestOptionalListValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>>> TestOptionalValueOrErrorValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<collections.ValueOrErrors>> TestOptionalObjectValueOrError,
        final com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArrayValueOrError
    ) {
        this.TestDoubleValueOrError = TestDoubleValueOrError;
        this.TestFloatValueOrError = TestFloatValueOrError;
        this.TestInt32ValueOrError = TestInt32ValueOrError;
        this.TestInt64ValueOrError = TestInt64ValueOrError;
        this.TestBoolValueOrError = TestBoolValueOrError;
        this.TestStringValueOrError = TestStringValueOrError;
        this.TestBytesValueOrError = TestBytesValueOrError;
        this.TestDecimalValueOrError = TestDecimalValueOrError;
        this.TestDateValueOrError = TestDateValueOrError;
        this.TestDateTimeValueOrError = TestDateTimeValueOrError;
        this.TestLocalDateValueOrError = TestLocalDateValueOrError;
        this.TestLocalDateTimeValueOrError = TestLocalDateTimeValueOrError;
        this.TestMapValueOrError = TestMapValueOrError;
        this.TestSetValueOrError = TestSetValueOrError;
        this.TestListValueOrError = TestListValueOrError;
        this.TestValueOrErrorValueOrError = TestValueOrErrorValueOrError;
        this.TestObjectValueOrError = TestObjectValueOrError;
        this.TestArrayValueOrError = TestArrayValueOrError;
        this.TestOptionalDoubleValueOrError = TestOptionalDoubleValueOrError;
        this.TestOptionalFloatValueOrError = TestOptionalFloatValueOrError;
        this.TestOptionalInt32ValueOrError = TestOptionalInt32ValueOrError;
        this.TestOptionalInt64ValueOrError = TestOptionalInt64ValueOrError;
        this.TestOptionalBoolValueOrError = TestOptionalBoolValueOrError;
        this.TestOptionalStringValueOrError = TestOptionalStringValueOrError;
        this.TestOptionalBytesValueOrError = TestOptionalBytesValueOrError;
        this.TestOptionalDecimalValueOrError = TestOptionalDecimalValueOrError;
        this.TestOptionalDateValueOrError = TestOptionalDateValueOrError;
        this.TestOptionalDatetimeValueOrError = TestOptionalDatetimeValueOrError;
        this.TestOptionalLocalDateValueOrError = TestOptionalLocalDateValueOrError;
        this.TestOptionalLocalDateTimeValueOrError = TestOptionalLocalDateTimeValueOrError;
        this.TestOptionalMapValueOrError = TestOptionalMapValueOrError;
        this.TestOptionalSetValueOrError = TestOptionalSetValueOrError;
        this.TestOptionalListValueOrError = TestOptionalListValueOrError;
        this.TestOptionalValueOrErrorValueOrError = TestOptionalValueOrErrorValueOrError;
        this.TestOptionalObjectValueOrError = TestOptionalObjectValueOrError;
        this.TestOptionalArrayValueOrError = TestOptionalArrayValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Double> getTestDoubleValueOrError() {
        return TestDoubleValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Float> getTestFloatValueOrError() {
        return TestFloatValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Integer> getTestInt32ValueOrError() {
        return TestInt32ValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Long> getTestInt64ValueOrError() {
        return TestInt64ValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Boolean> getTestBoolValueOrError() {
        return TestBoolValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<String> getTestStringValueOrError() {
        return TestStringValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Bytes> getTestBytesValueOrError() {
        return TestBytesValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal> getTestDecimalValueOrError() {
        return TestDecimalValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Date> getTestDateValueOrError() {
        return TestDateValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.DateTime> getTestDateTimeValueOrError() {
        return TestDateTimeValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.LocalDate> getTestLocalDateValueOrError() {
        return TestLocalDateValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.LocalDateTime> getTestLocalDateTimeValueOrError() {
        return TestLocalDateTimeValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Map<String, String>> getTestMapValueOrError() {
        return TestMapValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Set<String>> getTestSetValueOrError() {
        return TestSetValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.List<String>> getTestListValueOrError() {
        return TestListValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>> getTestValueOrErrorValueOrError() {
        return TestValueOrErrorValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<collections.ValueOrErrors> getTestObjectValueOrError() {
        return TestObjectValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Array<String>> getTestArrayValueOrError() {
        return TestArrayValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<Double>> getTestOptionalDoubleValueOrError() {
        return TestOptionalDoubleValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<Float>> getTestOptionalFloatValueOrError() {
        return TestOptionalFloatValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<Integer>> getTestOptionalInt32ValueOrError() {
        return TestOptionalInt32ValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<Long>> getTestOptionalInt64ValueOrError() {
        return TestOptionalInt64ValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<Boolean>> getTestOptionalBoolValueOrError() {
        return TestOptionalBoolValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<String>> getTestOptionalStringValueOrError() {
        return TestOptionalStringValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Bytes>> getTestOptionalBytesValueOrError() {
        return TestOptionalBytesValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Decimal>> getTestOptionalDecimalValueOrError() {
        return TestOptionalDecimalValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Date>> getTestOptionalDateValueOrError() {
        return TestOptionalDateValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.DateTime>> getTestOptionalDatetimeValueOrError() {
        return TestOptionalDatetimeValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.LocalDate>> getTestOptionalLocalDateValueOrError() {
        return TestOptionalLocalDateValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> getTestOptionalLocalDateTimeValueOrError() {
        return TestOptionalLocalDateTimeValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> getTestOptionalMapValueOrError() {
        return TestOptionalMapValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Set<String>>> getTestOptionalSetValueOrError() {
        return TestOptionalSetValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.List<String>>> getTestOptionalListValueOrError() {
        return TestOptionalListValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.ValueOrError<com.kdsc.protogen.runtime.types.Decimal>>> getTestOptionalValueOrErrorValueOrError() {
        return TestOptionalValueOrErrorValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<collections.ValueOrErrors>> getTestOptionalObjectValueOrError() {
        return TestOptionalObjectValueOrError;
    }

    public com.kdsc.protogen.runtime.types.ValueOrError<Optional<com.kdsc.protogen.runtime.types.Array<String>>> getTestOptionalArrayValueOrError() {
        return TestOptionalArrayValueOrError;
    }

    @Override
    public String toString() {
        return toFormattedString(ToStringOptions.defaultToStringOptions, 0);
    }

    public String toFormattedString(final ToStringOptions toStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//collections.ValueOrErrors\n");
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