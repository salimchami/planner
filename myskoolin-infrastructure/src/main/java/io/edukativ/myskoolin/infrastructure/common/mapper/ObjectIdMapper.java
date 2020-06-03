package io.edukativ.myskoolin.infrastructure.common.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectIdMapper {

    default ObjectId toObjectId(String id) {
        if (id != null && !id.isEmpty()) {
            return new ObjectId(id);
        }
        return null;
    }

    default String toString(ObjectId id) {
        if (id != null) {
            return id.toString();
        }
        return null;
    }
}
