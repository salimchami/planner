package io.edukativ.myskoolin.infrastructure.timetabling;

public class HardSoftScoreVO {

    private int hardScore;
    private int softScore;

    public HardSoftScoreVO() {
    }

    public HardSoftScoreVO(int hardScore, int softScore) {
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
}
