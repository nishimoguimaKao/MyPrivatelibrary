package com.example.springboot_vue_backend.service.Impl;

import com.example.springboot_vue_backend.dao.BookDao;
import com.example.springboot_vue_backend.pojo.Book;
import com.example.springboot_vue_backend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl {
    @Autowired
    BookDao bookDao;
    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    public List<Book> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return bookDao.findAll(sort);
    }

    public void addOrUpdate(Book book) {
        bookDao.save(book);
    }

    public void deleteById(int id) {
        bookDao.deleteById(id);
    }

    public List<Book> listByCategory(int cid) {
        Category category = categoryServiceImpl.get(cid);
        return bookDao.findAllByCategory(category);
    }

    public List<Book> Search(String keywords) {
        return bookDao.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}