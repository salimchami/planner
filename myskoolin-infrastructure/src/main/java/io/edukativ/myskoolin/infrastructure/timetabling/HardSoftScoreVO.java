package io.edukativ.myskoolin.infrastructure.timetabling;

import java.io.Serializable;

public class HardSoftScoreVO implements Serializable {

    private int initScore;
    private int hardScore;
    private int softScore;

    public HardSoftScoreVO() {
    }

    public HardSoftScoreVO(int initScore, int hardScore, int softScore) {
        this.initScore = initScore;
        this.hardScore = hardScore;
        this.softScore = softScore;
    }

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

    public int getInitScore() {
        return initScore;
    }

    public void setInitScore(int initScore) {
        this.initScore = initScore;
    }
}
