package com.ascending.repository;

import com.ascending.model.Author;

import java.util.List;

public interface AuthorDao {
    Author save(Author author);
    List<Author> getAuthor();
    Author getAuthorByName(String name);
    int deleteAll();
    int deleteByName(String name);
    int updateByName(String name, Author author);
    Author getAuthorByNameEager(String name);
    List<Author> getByEager();


}
