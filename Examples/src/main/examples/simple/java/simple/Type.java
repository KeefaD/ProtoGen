package simple;

import java.util.Optional;

public class Type {

	private int TestInt32;
	private long TestInt64;
	private Optional<Integer> TestOptionalInt32;
	private Optional<Long> TestOptionalInt64;

	public Type(
		int TestInt32,
		long TestInt64,
		Optional<Integer> TestOptionalInt32,
		Optional<Long> TestOptionalInt64
	) {
		this.TestInt32 = TestInt32;
		this.TestInt64 = TestInt64;
		this.TestOptionalInt32 = TestOptionalInt32;
		this.TestOptionalInt64 = TestOptionalInt64;
	}

	public int getTestInt32() {
		return TestInt32;
	}

	public long getTestInt64() {
		return TestInt64;
	}

	public Optional<Integer> getTestOptionalInt32() {
		return TestOptionalInt32;
	}

	public Optional<Long> getTestOptionalInt64() {
		return TestOptionalInt64;
	}

}