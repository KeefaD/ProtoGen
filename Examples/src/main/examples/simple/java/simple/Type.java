package simple;

import com.kdsc.protogen.runtime.ProtoGenType;
import java.util.Optional;

public class Type implements ProtoGenType {

	private double TestDouble;
	private float TestFloat;
	private int TestInt32;
	private long TestInt64;
	private boolean TestBool;
	private String TestString;
	private com.kdsc.protogen.runtime.types.Bytes TestBytes;
	private com.kdsc.protogen.runtime.types.Decimal TestDecimal;
	private com.kdsc.protogen.runtime.types.Date TestDate;
	private com.kdsc.protogen.runtime.types.DateTime TestDateTime;
	private com.kdsc.protogen.runtime.types.LocalDateTime TestLocalDateTime;
	private com.kdsc.protogen.runtime.types.Map<Integer, Integer> TestMap;
	private com.kdsc.protogen.runtime.types.Set<Integer> TestSet;
	private com.kdsc.protogen.runtime.types.ValueOrError<Integer> TestValueOrError;
	private simple.InnerType TestType;
	private com.kdsc.protogen.runtime.types.Array<Integer> TestArray;
	private Optional<Double> TestOptionalDouble;
	private Optional<Float> TestOptionalFloat;
	private Optional<Integer> TestOptionalInt32;
	private Optional<Long> TestOptionalInt64;
	private Optional<Boolean> TestOptionalBool;
	private Optional<String> TestOptionalString;
	private Optional<com.kdsc.protogen.runtime.types.Bytes> TestOptionalBytes;
	private Optional<com.kdsc.protogen.runtime.types.Decimal> TestOptionalDecimal;
	private Optional<com.kdsc.protogen.runtime.types.Date> TestOptionalDate;
	private Optional<com.kdsc.protogen.runtime.types.DateTime> TestOptionalDatetime;
	private Optional<com.kdsc.protogen.runtime.types.LocalDateTime> TestOptionalLocalDateTime;
	private Optional<com.kdsc.protogen.runtime.types.Map<Integer, Integer>> TestOptionalMap;
	private Optional<com.kdsc.protogen.runtime.types.Set<Integer>> TestOptionalSet;
	private Optional<com.kdsc.protogen.runtime.types.ValueOrError<Integer>> TestOptionalValueOrError;
	private Optional<simple.InnerType> TestOptionalType;
	private Optional<com.kdsc.protogen.runtime.types.Array<Integer>> TestOptionalArray;

	public Type(
		double TestDouble,
		float TestFloat,
		int TestInt32,
		long TestInt64,
		boolean TestBool,
		String TestString,
		com.kdsc.protogen.runtime.types.Bytes TestBytes,
		com.kdsc.protogen.runtime.types.Decimal TestDecimal,
		com.kdsc.protogen.runtime.types.Date TestDate,
		com.kdsc.protogen.runtime.types.DateTime TestDateTime,
		com.kdsc.protogen.runtime.types.LocalDateTime TestLocalDateTime,
		com.kdsc.protogen.runtime.types.Map<Integer, Integer> TestMap,
		com.kdsc.protogen.runtime.types.Set<Integer> TestSet,
		com.kdsc.protogen.runtime.types.ValueOrError<Integer> TestValueOrError,
		simple.InnerType TestType,
		com.kdsc.protogen.runtime.types.Array<Integer> TestArray,
		Optional<Double> TestOptionalDouble,
		Optional<Float> TestOptionalFloat,
		Optional<Integer> TestOptionalInt32,
		Optional<Long> TestOptionalInt64,
		Optional<Boolean> TestOptionalBool,
		Optional<String> TestOptionalString,
		Optional<com.kdsc.protogen.runtime.types.Bytes> TestOptionalBytes,
		Optional<com.kdsc.protogen.runtime.types.Decimal> TestOptionalDecimal,
		Optional<com.kdsc.protogen.runtime.types.Date> TestOptionalDate,
		Optional<com.kdsc.protogen.runtime.types.DateTime> TestOptionalDatetime,
		Optional<com.kdsc.protogen.runtime.types.LocalDateTime> TestOptionalLocalDateTime,
		Optional<com.kdsc.protogen.runtime.types.Map<Integer, Integer>> TestOptionalMap,
		Optional<com.kdsc.protogen.runtime.types.Set<Integer>> TestOptionalSet,
		Optional<com.kdsc.protogen.runtime.types.ValueOrError<Integer>> TestOptionalValueOrError,
		Optional<simple.InnerType> TestOptionalType,
		Optional<com.kdsc.protogen.runtime.types.Array<Integer>> TestOptionalArray
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
		this.TestLocalDateTime = TestLocalDateTime;
		this.TestMap = TestMap;
		this.TestSet = TestSet;
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
		this.TestOptionalLocalDateTime = TestOptionalLocalDateTime;
		this.TestOptionalMap = TestOptionalMap;
		this.TestOptionalSet = TestOptionalSet;
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

	public com.kdsc.protogen.runtime.types.LocalDateTime getTestLocalDateTime() {
		return TestLocalDateTime;
	}

	public com.kdsc.protogen.runtime.types.Map<Integer, Integer> getTestMap() {
		return TestMap;
	}

	public com.kdsc.protogen.runtime.types.Set<Integer> getTestSet() {
		return TestSet;
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

	public Optional<com.kdsc.protogen.runtime.types.LocalDateTime> getTestOptionalLocalDateTime() {
		return TestOptionalLocalDateTime;
	}

	public Optional<com.kdsc.protogen.runtime.types.Map<Integer, Integer>> getTestOptionalMap() {
		return TestOptionalMap;
	}

	public Optional<com.kdsc.protogen.runtime.types.Set<Integer>> getTestOptionalSet() {
		return TestOptionalSet;
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
		return toFormattedString(0);
	}

	public String toFormattedString(final int indentationLevel) {
		var stringBuilder = new StringBuilder();
		stringBuilder.append("//simple.Type\n");
		return stringBuilder.toString().indent(indentationLevel * TO_STRING_INDENTATION_LEVEL);
	}

}