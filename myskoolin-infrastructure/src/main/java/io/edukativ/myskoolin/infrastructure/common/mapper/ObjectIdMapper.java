package io.edukativ.myskoolin.infrastructure.common.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ObjectIdMapper {

    default ObjectId map(String id) {
        if (id != null && !id.isEmpty()) {
            return new ObjectId(id);
        }
        return null;
    }

    default String map(ObjectId id) {
        if (id != null) {
            return id.toString();
        }
        return null;
    }
}
