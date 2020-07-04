package io.edukativ.myskoolin.infrastructure.timetabling;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public class HardSoftScoreDbVO implements Serializable {

    private static final String MONGO_FIELD_HARD_SCORE = "hard_score";
    private static final String MONGO_FIELD_SOFT_SCORE = "soft_score";

    @Field(MONGO_FIELD_HARD_SCORE)
    private int hardScore;
    @Field(MONGO_FIELD_SOFT_SCORE)
    private int softScore;

    public int getHardScore() {
        return hardScore;
    }

    public void setHardScore(int hardScore) {
        this.hardScore = hardScore;
    }

    public int getSoftScore() {
        return softScore;
    }

    public void setSoftScore(int softScore) {
        this.softScore = softScore;
    }
}
