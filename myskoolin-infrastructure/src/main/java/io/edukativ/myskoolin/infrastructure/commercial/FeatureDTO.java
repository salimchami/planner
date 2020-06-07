package io.edukativ.myskoolin.infrastructure.commercial;

import java.util.List;

public class FeatureDTO {

    private String id;
    private String name;
    private List<String> authorities;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
