package com.example.springboot_vue_backend.dao;

import com.example.springboot_vue_backend.pojo.Book;
import com.example.springboot_vue_backend.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Integer> {
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByTitleLikeOrAuthorLike(String Keyword1,String Keyword2);
}
