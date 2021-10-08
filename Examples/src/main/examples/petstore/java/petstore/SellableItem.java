package petstore;

import com.kdsc.protogen.runtime.options.EqualsHashCodeOptions;
import com.kdsc.protogen.runtime.ProtoGenType;
import com.kdsc.protogen.runtime.options.ToStringOptions;

public class SellableItem implements ProtoGenType {

    private final petstore.SellableItemId Id;
    private final com.kdsc.protogen.runtime.types.Bytes SmallPicture;
    private final com.kdsc.protogen.runtime.types.Bytes LargePicture;
    private final String Name;
    private final String Description;

    public SellableItem(
        final petstore.SellableItemId Id,
        final com.kdsc.protogen.runtime.types.Bytes SmallPicture,
        final com.kdsc.protogen.runtime.types.Bytes LargePicture,
        final String Name,
        final String Description
    ) {
        this.Id = Id;
        this.SmallPicture = SmallPicture;
        this.LargePicture = LargePicture;
        this.Name = Name;
        this.Description = Description;
    }

    public petstore.SellableItemId getId() {
        return Id;
    }

    public com.kdsc.protogen.runtime.types.Bytes getSmallPicture() {
        return SmallPicture;
    }

    public com.kdsc.protogen.runtime.types.Bytes getLargePicture() {
        return LargePicture;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    @Override
    public String toString() {
        return toFormattedString(ToStringOptions.defaultToStringOptions, 0);
    }

    public String toFormattedString(final ToStringOptions toStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//petstore.SellableItem\n");
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