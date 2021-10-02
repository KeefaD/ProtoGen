package simple;

import java.util.Optional;

public class Type {

	private double TestDouble;
	private float TestFloat;
	private int TestInt32;
	private long TestInt64;
	private Optional<Double> TestOptionalDouble;
	private Optional<Float> TestOptionalFloat;
	private Optional<Integer> TestOptionalInt32;
	private Optional<Long> TestOptionalInt64;

	public Type(
		double TestDouble,
		float TestFloat,
		int TestInt32,
		long TestInt64,
		Optional<Double> TestOptionalDouble,
		Optional<Float> TestOptionalFloat,
		Optional<Integer> TestOptionalInt32,
		Optional<Long> TestOptionalInt64
	) {
		this.TestDouble = TestDouble;
		this.TestFloat = TestFloat;
		this.TestInt32 = TestInt32;
		this.TestInt64 = TestInt64;
		this.TestOptionalDouble = TestOptionalDouble;
		this.TestOptionalFloat = TestOptionalFloat;
		this.TestOptionalInt32 = TestOptionalInt32;
		this.TestOptionalInt64 = TestOptionalInt64;
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

	@Override
	public String toString() {
		return toFormattedString(0);
	}

	public String toFormattedString(final int indentationLevel) {
		var stringBuilder = new StringBuilder();
		stringBuilder.append("//simple.Type\n");
		return stringBuilder.toString().indent(indentationLevel * 4);
	}

}