package com.example.springboot_vue_backend.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="user")
/*
 * 为了简化对数据库的操作，我们使用了 Java Persistence API（JPA），
 * 对于 @JsonIgnoreProperties({ “handler”,“hibernateLazyInitializer” })，
 * 解释如下：
 * 因为是做前后端分离，而前后端数据交互用的是 json 格式。
 * 那么User对象就会被转换为json数据。而本项目使用jpa来做实体类的持久化，jpa默认会使用hibernate,
 * 在jpa工作过程中，就会创造代理类来继承User，并添加handler和hibernateLazyInitializer这两个无须json化的属性，
 * 所以这里需要用JsonIgnoreProperties把这两个属性忽略掉。
 *
 */
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
