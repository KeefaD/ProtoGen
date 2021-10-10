//
//   ╔═╗┬─┐┌─┐┌┬┐┌─┐╔═╗┌─┐┌┐┌
//   ╠═╝├┬┘│ │ │ │ │║ ╦├┤ │││
//   ╩  ┴└─└─┘ ┴ └─┘╚═╝└─┘┘└┘   v0.0 2021
//
package petstore;

import com.kdsc.protogen.runtime.ProtoGenType;

public interface SellableItem extends ProtoGenType {

    petstore.SellableItemId getId();
    com.kdsc.protogen.runtime.types.Set<petstore.CategoryId> getCategoryIds();
    com.kdsc.protogen.runtime.types.Bytes getSmallPicture();
    com.kdsc.protogen.runtime.types.Bytes getLargePicture();
    String getName();
    String getDescription();

}