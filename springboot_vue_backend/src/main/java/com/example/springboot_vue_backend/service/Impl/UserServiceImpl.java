package com.example.springboot_vue_backend.service.Impl;

import com.example.springboot_vue_backend.dao.UserDao;
import com.example.springboot_vue_backend.pojo.User;
import com.example.springboot_vue_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public boolean isExist(String username){
        User user=getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDao.findByUsername(username);
    }
    
    public User get(String username,String password){
        return userDao.getByUsernameAndPassword(username,password);
    }

    public void add(User user){
        userDao.save(user);
    }

    public boolean delete(Integer id) {

        return false;
    }
}
