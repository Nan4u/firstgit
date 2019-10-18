package cn.rui0.core.model.vo.common;

import lombok.Data;

@Data
public class ScoreVo {
    private String name;
    private String collage;
    private String code;
    private int score;
    private int hearing;
    private int reading;
    private int writing;

    public ScoreVo(String name, String collage, String code, int score, int hearing, int reading, int writing) {
        this.name = name;
        this.collage = collage;
        this.code = code;
        this.score = score;
        this.hearing = hearing;
        this.reading = reading;
        this.writing = writing;
    }
}
