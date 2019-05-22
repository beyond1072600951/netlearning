package com.school.netlearning.pojo;


import java.util.Date;

public class Statictics {
    private String name;
    private String courseBaseName;
    private String cooursePlanName;
    private Date recordTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseBaseName() {
        return courseBaseName;
    }

    public void setCourseBaseName(String courseBaseName) {
        this.courseBaseName = courseBaseName;
    }

    public String getCooursePlanName() {
        return cooursePlanName;
    }

    public void setCooursePlanName(String cooursePlanName) {
        this.cooursePlanName = cooursePlanName;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
