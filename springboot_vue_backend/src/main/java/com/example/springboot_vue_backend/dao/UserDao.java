package com.example.springboot_vue_backend.dao;

import com.example.springboot_vue_backend.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    /*
     * 这里关键的地方在于方法的名字。由于使用了JPA，无需手动构建SQL语句，而只需要
     * 按照规范提供方法的名字即可实现对数据库的增删改查。
     * 如findByUsername，就是通过username字段查询到对应的行，并返回给User类。
     * 这里我们构建了两个方法，一个是通过用户名查询，一个是通过用户名及密码查询。
     */
    User findByUsername(String username);
    User getByUsernameAndPassword(String username,String password);
}
