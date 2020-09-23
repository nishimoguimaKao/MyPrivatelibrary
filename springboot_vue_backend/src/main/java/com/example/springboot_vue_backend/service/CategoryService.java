package com.example.springboot_vue_backend.service;

import com.example.springboot_vue_backend.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> list();
    Category get(int id);
}
