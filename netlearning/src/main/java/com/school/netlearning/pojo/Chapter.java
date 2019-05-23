package com.school.netlearning.pojo;

import javax.persistence.*;

@Entity
@Table(name = "chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "chapter_num")
    private String chapterNum;

    @Column(name = "chapter_value")
    private Integer chapterValue;

    @Column(name = "level")
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(String chapterNum) {
        this.chapterNum = chapterNum;
    }

    public Integer getChapterValue() {
        return chapterValue;
    }

    public void setChapterValue(Integer chapterValue) {
        this.chapterValue = chapterValue;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
