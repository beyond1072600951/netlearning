package com.school.netlearning.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_record")
public class CourseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "course_base_id")
    private Integer courseBaseId;

    @Column(name = "course_plan_id")
    private Integer coursePlanId;

    @Column(name = "record_time")
    private Date recordTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseBaseId() {
        return courseBaseId;
    }

    public void setCourseBaseId(Integer courseBaseId) {
        this.courseBaseId = courseBaseId;
    }

    public Integer getCoursePlanId() {
        return coursePlanId;
    }

    public void setCoursePlanId(Integer coursePlanId) {
        this.coursePlanId = coursePlanId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
