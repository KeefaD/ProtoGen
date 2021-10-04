package interfaces;

import com.kdsc.protogen.runtime.ProtoGenType;

public class InnerType implements ProtoGenType {

	private final int TestInt32;

	public InnerType(
		final int TestInt32
	) {
		this.TestInt32 = TestInt32;
	}

	public int getTestInt32() {
		return TestInt32;
	}

	@Override
	public String toString() {
		return toFormattedString(0);
	}

	public String toFormattedString(final int indentationLevel) {
		var stringBuilder = new StringBuilder();
		stringBuilder.append("//interfaces.InnerType\n");
		return stringBuilder.toString().indent(indentationLevel * TO_STRING_INDENTATION_LEVEL);
	}

}