package io.edukativ.myskoolin.infrastructure.app.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * An authority (a security role) used by Spring Security.
 */
@Document(collection = "authorities")
public class AuthorityDbDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String MONGO_COLLECTION_NAME = "authorities";

    @NotNull
    @Size(max = 50)
    @Id
    private String name;

    public AuthorityDbDTO(String name) {
        this.name = name;
    }

    public AuthorityDbDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthorityDbDTO)) {
            return false;
        }
        return Objects.equals(name, ((AuthorityDbDTO) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                "}";
    }

}
