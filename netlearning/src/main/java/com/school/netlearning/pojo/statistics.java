package com.school.netlearning.pojo;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
public class statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name ="user+id")
    private Integer userId;

    @Column(name = "courseplan_id")
    private Integer courseplanId;

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

    public Integer getCourseplanId() {
        return courseplanId;
    }

    public void setCourseplanId(Integer courseplanId) {
        this.courseplanId = courseplanId;
    }
}
