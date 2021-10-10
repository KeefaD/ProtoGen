//
//   ╔═╗┬─┐┌─┐┌┬┐┌─┐╔═╗┌─┐┌┐┌
//   ╠═╝├┬┘│ │ │ │ │║ ╦├┤ │││
//   ╩  ┴└─└─┘ ┴ └─┘╚═╝└─┘┘└┘   v0.0 2021
//
package petstore;

import com.kdsc.protogen.runtime.ProtoGenType;
import com.kdsc.protogen.runtime.options.EqualsHashCodeOptions;
import com.kdsc.protogen.runtime.options.ToStringOptions;

public class SellableItemId implements ProtoGenType {

    private final long Id;

    public SellableItemId(
        final long Id
    ) {
        this.Id = Id;
    }

    public long getId() {
        return Id;
    }

    @Override
    public String toString() {
        return toFormattedString(ToStringOptions.defaultToStringOptions, 0);
    }

    public String toFormattedString(final ToStringOptions toStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//petstore.SellableItemId\n");
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