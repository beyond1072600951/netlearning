package com.school.netlearning.pojo;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
public class statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


}
