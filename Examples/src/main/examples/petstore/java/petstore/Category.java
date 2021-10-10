//
//   ╔═╗┬─┐┌─┐┌┬┐┌─┐╔═╗┌─┐┌┐┌
//   ╠═╝├┬┘│ │ │ │ │║ ╦├┤ │││
//   ╩  ┴└─└─┘ ┴ └─┘╚═╝└─┘┘└┘   v0.0 2021
//
package petstore;

import com.kdsc.protogen.runtime.ProtoGenType;
import com.kdsc.protogen.runtime.options.EqualsHashCodeOptions;
import com.kdsc.protogen.runtime.options.ToStringOptions;
import java.util.Optional;

public class Category implements ProtoGenType {

    private final Optional<petstore.CategoryId> ParentCategoryId;
    private final petstore.CategoryId CategoryId;
    private final String Name;
    private final String Description;

    public Category(
        final Optional<petstore.CategoryId> ParentCategoryId,
        final petstore.CategoryId CategoryId,
        final String Name,
        final String Description
    ) {
        this.ParentCategoryId = ParentCategoryId;
        this.CategoryId = CategoryId;
        this.Name = Name;
        this.Description = Description;
    }

    public Optional<petstore.CategoryId> getParentCategoryId() {
        return ParentCategoryId;
    }

    public petstore.CategoryId getCategoryId() {
        return CategoryId;
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
        stringBuilder.append("//petstore.Category\n");
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