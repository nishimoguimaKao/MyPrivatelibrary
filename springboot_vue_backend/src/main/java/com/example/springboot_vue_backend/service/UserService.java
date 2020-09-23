package com.example.springboot_vue_backend.service;

import com.example.springboot_vue_backend.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean isExist(String username);
    User getByName(String username);
    User get(String username,String password);
    void add(User user);
    boolean delete(Integer id);
}
