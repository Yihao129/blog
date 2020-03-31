package com.ascending.controller;

import com.ascending.model.Author;
import com.ascending.service.AuthorService;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = {"/authors","/author"})
public class AuthorController {

    @Autowired
    private AuthorService as;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // {prefix}/authors GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Author> getAuthor(){
//        as.save(new Author(01,"Tom","111@gmail.com", LocalDateTime.now(),null));
        List<Author> r = as.getAuthor();
        return r;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Author getAuthorByName(@PathVariable("name") String name){
//        as.save(new Author(01,"Tom","111@gmail.com", LocalDateTime.now(),null));
        logger.error(name);
        Author author = as.getAuthorByNameEager(name);
        logger.error(String.valueOf(author));
//        as.deleteAll();
        System.out.println(author.getPosts());
        return author;
    }


}
