package petstore;

import com.kdsc.protogen.runtime.options.EqualsHashCodeOptions;
import com.kdsc.protogen.runtime.ProtoGenType;
import com.kdsc.protogen.runtime.options.ToStringOptions;

public class Basket implements ProtoGenType {

    private final com.kdsc.protogen.runtime.types.Set<petstore.LineItem> LineItems;

    public Basket(
        final com.kdsc.protogen.runtime.types.Set<petstore.LineItem> LineItems
    ) {
        this.LineItems = LineItems;
    }

    public com.kdsc.protogen.runtime.types.Set<petstore.LineItem> getLineItems() {
        return LineItems;
    }

    @Override
    public String toString() {
        return toFormattedString(ToStringOptions.defaultToStringOptions, 0);
    }

    public String toFormattedString(final ToStringOptions toStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//petstore.Basket\n");
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