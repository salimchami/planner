package io.edukativ.myskoolin.domain.sanctions;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Created by salim on 24/06/2016.
 */
public class Sanction implements Serializable {

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
        Sanction sanction = (Sanction) o;
        return date.equals(sanction.date) &&
                content.equals(sanction.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, content);
    }

    @Override
    public String toString() {
        return "Sanction{" +
                "date=" + date +
                ", content='" + content + '\'' +
                '}';
    }
}
