package com.example.springboot_vue_backend.dao;

import com.example.springboot_vue_backend.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {

}
