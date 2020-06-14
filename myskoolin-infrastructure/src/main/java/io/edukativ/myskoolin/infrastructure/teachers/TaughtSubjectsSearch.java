package io.edukativ.myskoolin.infrastructure.teachers;

import java.util.List;

public class TaughtSubjectsSearch {

    private List<String> gradesIds;
    private List<String> subjectsIds;

    public List<String> getGradesIds() {
        return gradesIds;
    }

    public void setGradesIds(List<String> gradesIds) {
        this.gradesIds = gradesIds;
    }

    public List<String> getSubjectsIds() {
        return subjectsIds;
    }

    public void setSubjectsIds(List<String> subjectsIds) {
        this.subjectsIds = subjectsIds;
    }
}
