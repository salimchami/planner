package io.edukativ.myskoolin.infrastructure.common.vo;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * An Email.
 */
public class EmailDbVO implements Serializable {

    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_LINK = "link";

    @Field(MONGO_FIELD_NAME)
    private String name;

    @Field(MONGO_FIELD_LINK)
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
        message="{invalid.email}")
    private String link;

    public EmailDbVO() {
    }

    public EmailDbVO(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
