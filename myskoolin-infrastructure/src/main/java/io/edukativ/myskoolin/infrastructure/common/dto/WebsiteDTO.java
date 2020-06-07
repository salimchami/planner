package io.edukativ.myskoolin.infrastructure.common.dto;

import java.io.Serializable;

public class WebsiteDTO implements Serializable {

    private String link;
    private String name;

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
