package io.edukativ.myskoolin.infrastructure.sanctions;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Created by salim on 24/06/2016.
 */
public class SanctionDbVO implements Serializable {

    private ZonedDateTime date;
    private String content;

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SanctionDbVO sanction = (SanctionDbVO) o;
        return date.equals(sanction.date) &&
                content.equals(sanction.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, content);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Sanction{" +
                    "date=" + date +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
