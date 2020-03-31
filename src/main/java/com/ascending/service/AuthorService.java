package com.ascending.service;

import com.ascending.model.Author;
import com.ascending.repository.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorDao ad;

    public Author save(Author aut){
        Author r = ad.save(aut);
        return r;
    }

    public List<Author> getAuthor(){
        return ad.getAuthor();
    }

    public Author getAuthorByName(String name){
        return ad.getAuthorByName(name);
    }

    public int deleteAll(){
        return ad.deleteAll();
    }

    public List<Author> getByEager(){
        return ad.getByEager();
    }

    public int deleteByName(String name){
        return ad.deleteByName(name);
    }

    public int updateByName(String name, Author author){
        return ad.updateByName(name,author);
    }

    public Author getAuthorByNameEager(String name){
        return ad.getAuthorByNameEager(name);
    }

}
