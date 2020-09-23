package com.example.springboot_vue_backend.service.Impl;

import com.example.springboot_vue_backend.dao.CategoryDao;
import com.example.springboot_vue_backend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl {
    @Autowired
    CategoryDao categoryDAO;

    public List<Category> list() {
        Sort sort =Sort.by(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    public Category get(int id) {
        Category c= categoryDAO.findById(id).orElse(null);
        return c;
    }
}