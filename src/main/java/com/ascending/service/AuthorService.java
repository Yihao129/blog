package com.ascending.service;

import com.ascending.model.Author;
import com.ascending.repository.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorDao ad;

    public Author save(Author aut){
        Author r = ad.save(aut);
        return r;
    }

}
