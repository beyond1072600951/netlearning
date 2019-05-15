package com.school.netlearning.pojo;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //用户名、登录账号
    @NotNull
    @Column(name = "username")
    private String userName;

    //登录密码
    @NotNull
    @Column(name = "password")
    @JsonIgnore
    private String passWord;

    //手机号
    @Column(name = "phone")
    private String phone;

    //    @Null
    //当前用户状态（0：启用(默认)；1：禁用；2：删除）
    @Column(name = "state")
    @JsonIgnore
    private Integer state;

    //    @Null
    @Column(name = "registime")
    private Date registime;

    @Column(name = "name")
    private String name;

    @Column(name = "education")
    private String education;

    @Column(name = "learningage")
    private String learningage;

    @Column(name = "ispost")
    private String ispost;

    @Column(name = "isreply")
    private String isreply;


    //    @Null
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roleList;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<News> newsList;  //该用户发布的新闻通知列表

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getRegistime() {
        return registime;
    }

    public void setRegistime(Date registime) {
        this.registime = registime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLearningage() {
        return learningage;
    }

    public void setLearningage(String learningage) {
        this.learningage = learningage;
    }

    public String getIspost() {
        return ispost;
    }

    public void setIspost(String ispost) {
        this.ispost = ispost;
    }

    public String getIsreply() {
        return isreply;
    }

    public void setIsreply(String isreply) {
        this.isreply = isreply;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }


//        public List<News> getNewsList() {
//        return newsList;
//    }
//
//    public void setNewsList(List<News> newsList) {
//        this.newsList = newsList;
//    }
}
