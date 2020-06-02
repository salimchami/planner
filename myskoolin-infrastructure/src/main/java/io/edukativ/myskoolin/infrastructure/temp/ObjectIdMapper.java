package io.edukativ.myskoolin.infrastructure.temp;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectIdMapper {

    default ObjectId toObjectId(String id) {
        if(id != null && !id.isEmpty()) {
            return new ObjectId(id);
        }
        return null;
    }

    default String toString(ObjectId id) {
        return id.toString();
    }
}
