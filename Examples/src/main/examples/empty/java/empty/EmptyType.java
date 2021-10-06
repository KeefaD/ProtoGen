package empty;

import com.kdsc.protogen.runtime.ProtoGenType;

public class EmptyType implements empty.EmptyTypeInterface, ProtoGenType {

    public EmptyType()
    {
    }

    @Override
    public String toString() {
        return toFormattedString(0);
    }

    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//empty.EmptyType\n");
        return stringBuilder.toString().indent(indentationLevel * TO_STRING_INDENTATION_LEVEL);
    }

}