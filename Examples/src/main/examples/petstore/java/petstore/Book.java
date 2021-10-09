package petstore;

import com.kdsc.protogen.runtime.options.EqualsHashCodeOptions;
import com.kdsc.protogen.runtime.ProtoGenType;
import com.kdsc.protogen.runtime.options.ToStringOptions;

public class Book implements petstore.SellableItem, ProtoGenType {

    private final String ISBN;
    private final petstore.SellableItemId Id;
    private final com.kdsc.protogen.runtime.types.Set<petstore.CategoryId> CategoryIds;
    private final com.kdsc.protogen.runtime.types.Bytes SmallPicture;
    private final com.kdsc.protogen.runtime.types.Bytes LargePicture;
    private final String Name;
    private final String Description;

    public Book(
        final String ISBN,
        final petstore.SellableItemId Id,
        final com.kdsc.protogen.runtime.types.Set<petstore.CategoryId> CategoryIds,
        final com.kdsc.protogen.runtime.types.Bytes SmallPicture,
        final com.kdsc.protogen.runtime.types.Bytes LargePicture,
        final String Name,
        final String Description
    ) {
        this.ISBN = ISBN;
        this.Id = Id;
        this.CategoryIds = CategoryIds;
        this.SmallPicture = SmallPicture;
        this.LargePicture = LargePicture;
        this.Name = Name;
        this.Description = Description;
    }

    public String getISBN() {
        return ISBN;
    }

    public petstore.SellableItemId getId() {
        return Id;
    }

    public com.kdsc.protogen.runtime.types.Set<petstore.CategoryId> getCategoryIds() {
        return CategoryIds;
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
        stringBuilder.append("//petstore.Book\n");
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