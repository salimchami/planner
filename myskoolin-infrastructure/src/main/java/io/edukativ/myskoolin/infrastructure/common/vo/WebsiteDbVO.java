package io.edukativ.myskoolin.infrastructure.common.vo;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public class WebsiteDbVO implements Serializable {

    public static final String MONGO_FIELD_LINK = "link";
    public static final String MONGO_FIELD_NAME = "name";
    @Field(MONGO_FIELD_LINK)
    private String link;

    @Field(MONGO_FIELD_NAME)
    private String name;

    public WebsiteDbVO() {
    }

    public WebsiteDbVO(String link, String name) {
        this.link = link;
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
