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
	private simple.InnerType TestType;
	private Optional<Double> TestOptionalDouble;
	private Optional<Float> TestOptionalFloat;
	private Optional<Integer> TestOptionalInt32;
	private Optional<Long> TestOptionalInt64;
	private Optional<Boolean> TestOptionalBool;
	private Optional<String> TestOptionalString;
	private Optional<simple.InnerType> TestOptionalType;

	public Type(
		double TestDouble,
		float TestFloat,
		int TestInt32,
		long TestInt64,
		boolean TestBool,
		String TestString,
		simple.InnerType TestType,
		Optional<Double> TestOptionalDouble,
		Optional<Float> TestOptionalFloat,
		Optional<Integer> TestOptionalInt32,
		Optional<Long> TestOptionalInt64,
		Optional<Boolean> TestOptionalBool,
		Optional<String> TestOptionalString,
		Optional<simple.InnerType> TestOptionalType
	) {
		this.TestDouble = TestDouble;
		this.TestFloat = TestFloat;
		this.TestInt32 = TestInt32;
		this.TestInt64 = TestInt64;
		this.TestBool = TestBool;
		this.TestString = TestString;
		this.TestType = TestType;
		this.TestOptionalDouble = TestOptionalDouble;
		this.TestOptionalFloat = TestOptionalFloat;
		this.TestOptionalInt32 = TestOptionalInt32;
		this.TestOptionalInt64 = TestOptionalInt64;
		this.TestOptionalBool = TestOptionalBool;
		this.TestOptionalString = TestOptionalString;
		this.TestOptionalType = TestOptionalType;
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

	public simple.InnerType getTestType() {
		return TestType;
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

	public Optional<simple.InnerType> getTestOptionalType() {
		return TestOptionalType;
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