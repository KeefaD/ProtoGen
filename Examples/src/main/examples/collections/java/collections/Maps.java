package collections;

import com.kdsc.protogen.runtime.ProtoGenType;
import java.util.Optional;

public class Maps implements ProtoGenType {

	private com.kdsc.protogen.runtime.types.Map<Integer, Integer> TestInt32Map;
	private com.kdsc.protogen.runtime.types.Map<Long, Long> TestInt64Map;
	private com.kdsc.protogen.runtime.types.Map<Boolean, Boolean> TestBoolMap;
	private com.kdsc.protogen.runtime.types.Map<String, String> TestStringMap;
	private com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Bytes, com.kdsc.protogen.runtime.types.Bytes> TestBytesMap;
	private com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Decimal, com.kdsc.protogen.runtime.types.Decimal> TestDecimalMap;
	private com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Date, com.kdsc.protogen.runtime.types.Date> TestDateMap;
	private com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.DateTime, com.kdsc.protogen.runtime.types.DateTime> TestDatetimeMap;
	private com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.LocalDateTime, com.kdsc.protogen.runtime.types.LocalDateTime> TestLocaldatetimeMap;
	private com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Map<String, String>, com.kdsc.protogen.runtime.types.Map<String, String>> TestMapMap;
	private com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Set<String>, com.kdsc.protogen.runtime.types.Set<String>> TestSetMap;
	private com.kdsc.protogen.runtime.types.Map<collections.InnerType, collections.InnerType> TestObjectMap;
	private com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Array<String>, com.kdsc.protogen.runtime.types.Array<String>> TestArrayMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<Integer>, Optional<Integer>> TestOptionalInt32Map;
	private com.kdsc.protogen.runtime.types.Map<Optional<Long>, Optional<Long>> TestOptionalInt64Map;
	private com.kdsc.protogen.runtime.types.Map<Optional<Boolean>, Optional<Boolean>> TestOptionalBoolMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<String>, Optional<String>> TestOptionalStringMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Bytes>, Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Decimal>, Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Date>, Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateSetMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.DateTime>, Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>, Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocaldatetimeMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>, Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Set<String>>, Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalSetMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<collections.InnerType>, Optional<collections.InnerType>> TestOptionalObjectMap;
	private com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Array<String>>, Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArrayMap;

	public Maps(
		com.kdsc.protogen.runtime.types.Map<Integer, Integer> TestInt32Map,
		com.kdsc.protogen.runtime.types.Map<Long, Long> TestInt64Map,
		com.kdsc.protogen.runtime.types.Map<Boolean, Boolean> TestBoolMap,
		com.kdsc.protogen.runtime.types.Map<String, String> TestStringMap,
		com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Bytes, com.kdsc.protogen.runtime.types.Bytes> TestBytesMap,
		com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Decimal, com.kdsc.protogen.runtime.types.Decimal> TestDecimalMap,
		com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Date, com.kdsc.protogen.runtime.types.Date> TestDateMap,
		com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.DateTime, com.kdsc.protogen.runtime.types.DateTime> TestDatetimeMap,
		com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.LocalDateTime, com.kdsc.protogen.runtime.types.LocalDateTime> TestLocaldatetimeMap,
		com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Map<String, String>, com.kdsc.protogen.runtime.types.Map<String, String>> TestMapMap,
		com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Set<String>, com.kdsc.protogen.runtime.types.Set<String>> TestSetMap,
		com.kdsc.protogen.runtime.types.Map<collections.InnerType, collections.InnerType> TestObjectMap,
		com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Array<String>, com.kdsc.protogen.runtime.types.Array<String>> TestArrayMap,
		com.kdsc.protogen.runtime.types.Map<Optional<Integer>, Optional<Integer>> TestOptionalInt32Map,
		com.kdsc.protogen.runtime.types.Map<Optional<Long>, Optional<Long>> TestOptionalInt64Map,
		com.kdsc.protogen.runtime.types.Map<Optional<Boolean>, Optional<Boolean>> TestOptionalBoolMap,
		com.kdsc.protogen.runtime.types.Map<Optional<String>, Optional<String>> TestOptionalStringMap,
		com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Bytes>, Optional<com.kdsc.protogen.runtime.types.Bytes>> TestOptionalBytesMap,
		com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Decimal>, Optional<com.kdsc.protogen.runtime.types.Decimal>> TestOptionalDecimalMap,
		com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Date>, Optional<com.kdsc.protogen.runtime.types.Date>> TestOptionalDateSetMap,
		com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.DateTime>, Optional<com.kdsc.protogen.runtime.types.DateTime>> TestOptionalDatetimeMap,
		com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>, Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> TestOptionalLocaldatetimeMap,
		com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>, Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> TestOptionalMapMap,
		com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Set<String>>, Optional<com.kdsc.protogen.runtime.types.Set<String>>> TestOptionalSetMap,
		com.kdsc.protogen.runtime.types.Map<Optional<collections.InnerType>, Optional<collections.InnerType>> TestOptionalObjectMap,
		com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Array<String>>, Optional<com.kdsc.protogen.runtime.types.Array<String>>> TestOptionalArrayMap
	) {
		this.TestInt32Map = TestInt32Map;
		this.TestInt64Map = TestInt64Map;
		this.TestBoolMap = TestBoolMap;
		this.TestStringMap = TestStringMap;
		this.TestBytesMap = TestBytesMap;
		this.TestDecimalMap = TestDecimalMap;
		this.TestDateMap = TestDateMap;
		this.TestDatetimeMap = TestDatetimeMap;
		this.TestLocaldatetimeMap = TestLocaldatetimeMap;
		this.TestMapMap = TestMapMap;
		this.TestSetMap = TestSetMap;
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
		this.TestOptionalLocaldatetimeMap = TestOptionalLocaldatetimeMap;
		this.TestOptionalMapMap = TestOptionalMapMap;
		this.TestOptionalSetMap = TestOptionalSetMap;
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

	public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.DateTime, com.kdsc.protogen.runtime.types.DateTime> getTestDatetimeMap() {
		return TestDatetimeMap;
	}

	public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.LocalDateTime, com.kdsc.protogen.runtime.types.LocalDateTime> getTestLocaldatetimeMap() {
		return TestLocaldatetimeMap;
	}

	public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Map<String, String>, com.kdsc.protogen.runtime.types.Map<String, String>> getTestMapMap() {
		return TestMapMap;
	}

	public com.kdsc.protogen.runtime.types.Map<com.kdsc.protogen.runtime.types.Set<String>, com.kdsc.protogen.runtime.types.Set<String>> getTestSetMap() {
		return TestSetMap;
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

	public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.LocalDateTime>, Optional<com.kdsc.protogen.runtime.types.LocalDateTime>> getTestOptionalLocaldatetimeMap() {
		return TestOptionalLocaldatetimeMap;
	}

	public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Map<String, String>>, Optional<com.kdsc.protogen.runtime.types.Map<String, String>>> getTestOptionalMapMap() {
		return TestOptionalMapMap;
	}

	public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Set<String>>, Optional<com.kdsc.protogen.runtime.types.Set<String>>> getTestOptionalSetMap() {
		return TestOptionalSetMap;
	}

	public com.kdsc.protogen.runtime.types.Map<Optional<collections.InnerType>, Optional<collections.InnerType>> getTestOptionalObjectMap() {
		return TestOptionalObjectMap;
	}

	public com.kdsc.protogen.runtime.types.Map<Optional<com.kdsc.protogen.runtime.types.Array<String>>, Optional<com.kdsc.protogen.runtime.types.Array<String>>> getTestOptionalArrayMap() {
		return TestOptionalArrayMap;
	}

	@Override
	public String toString() {
		return toFormattedString(0);
	}

	public String toFormattedString(final int indentationLevel) {
		var stringBuilder = new StringBuilder();
		stringBuilder.append("//collections.Maps\n");
		return stringBuilder.toString().indent(indentationLevel * TO_STRING_INDENTATION_LEVEL);
	}

}