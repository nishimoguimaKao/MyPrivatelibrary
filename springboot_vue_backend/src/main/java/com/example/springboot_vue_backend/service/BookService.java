package com.example.springboot_vue_backend.service;

import com.example.springboot_vue_backend.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> list();
    void addOrUpdate(Book book);
    void deleteById(int id);
    List<Book> listByCategory(int cid);
}
