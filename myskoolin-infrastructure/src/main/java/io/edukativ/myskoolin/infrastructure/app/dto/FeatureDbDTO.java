package io.edukativ.myskoolin.infrastructure.app.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * A Feature.
 */
@Document(collection = FeatureDbDTO.MONGO_COLLECTION_NAME)
public class FeatureDbDTO implements Serializable {

    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_AUTHORITIES = "authorities";
    public static final String MONGO_COLLECTION_NAME = "features";

    @Id
    private String id;

    @Field(MONGO_FIELD_NAME)
    private String name;

    @Field(MONGO_FIELD_AUTHORITIES)
    private List<String> authorities;

    public FeatureDbDTO(String id, String name, List<String> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }

    public FeatureDbDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

}
