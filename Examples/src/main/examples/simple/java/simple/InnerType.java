package simple;

import com.kdsc.protogen.runtime.ProtoGenType;

public class InnerType implements ProtoGenType {

	private float TestFloat;

	public InnerType(
		float TestFloat
	) {
		this.TestFloat = TestFloat;
	}

	public float getTestFloat() {
		return TestFloat;
	}

	@Override
	public String toString() {
		return toFormattedString(0);
	}

	public String toFormattedString(final int indentationLevel) {
		var stringBuilder = new StringBuilder();
		stringBuilder.append("//simple.InnerType\n");
		return stringBuilder.toString().indent(indentationLevel * TO_STRING_INDENTATION_LEVEL);
	}

}