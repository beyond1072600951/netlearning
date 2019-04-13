package com.school.netlearning.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Long id;

    //登录账号
    @NotNull
    @Column(name = "login_name")
    private String loginName;

    //用户名
    @NotNull
    @Column(name = "user_name")
    private String userName;

    //登录密码
    @NotNull
    @Column(name = "pass_word")
    @JsonIgnore
    private String passWord;

    //年龄
    @Column(name = "age")
    private Byte age;

    //性别（0：未知(默认)；1：男；2：女）
    @Column(name = "sex")
    private Byte sex;

    //手机号
    @Column(name = "phone")
    private String phone;

    //    @Null
    //当前用户状态（0：启用(默认)；1：禁用；2：删除）
    @Column(name = "state")
    @JsonIgnore
    private Byte state;

    //    @Null
    //最后修改密码时间
    @Column(name = "last_password_reset_date")
    private Date lastPasswordResetDate;

    //    @Null
    @Column(name = "create_date")
    private Date createDate;

    //    @Null
    @Column(name = "update_date")
    private Date updateDate;

    //    @Null
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
