package com.example.springboot_vue_backend.controller;

import com.example.springboot_vue_backend.pojo.User;
import com.example.springboot_vue_backend.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/api/updatePassword")
    public void updatePassword(@RequestBody User user) {
        userService.add(user);
    }

    @PostMapping("/api/addUser")
    public void addUser(@RequestBody User user) {
        userService.add(user);
    }

    @DeleteMapping("/api/{id}")
    public void deleteUser(@PathVariable Integer id){

    }

    @GetMapping("/api/{username}")
    public User userCheck(@PathVariable String username) {
        return userService.getByName(username);
    }
}
