package io.scplanner.providers;

import io.scplanner.entities.Subject;

import java.util.Arrays;
import java.util.List;

public class SubjectsTestProvider {

    public static final Subject english = new Subject(1L, "English", 120, 60, 270, 3);
    public static final Subject maths = new Subject(2L, "Maths", 120, 60, 270, 3);
    public static final Subject historyGeography = new Subject(3L, "History and Geography", 60, 60, 180, 3);
    public static final Subject french = new Subject(4L, "French", 120, 60, 240, 4);
    public static final Subject lifeEarthSciences = new Subject(5L, "Life Earth Sciences", 60, 60, 60, 1);
    public static final Subject physics = new Subject(6L, "Physics", 60, 60, 60, 1);
    public static final Subject technology = new Subject(7L, "Technology", 120, 60, 120, 1);
    public static final Subject sport = new Subject(8L, "Sport", 120, 120, 240, 2);
    public static final Subject arts = new Subject(9L, "Arts", 60, 60, 60, 1);
    public static final Subject music = new Subject(10L, "Music", 60, 60, 60, 1);

    public static List<Subject> subjects() {
        return Arrays.asList(english, maths, historyGeography, french, lifeEarthSciences, physics, technology, sport, arts, music);
    }
}
