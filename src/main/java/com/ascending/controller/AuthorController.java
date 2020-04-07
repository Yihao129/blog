package com.ascending.controller;

import com.ascending.model.Author;
import com.ascending.model.view.ModelView;
import com.ascending.service.AuthorService;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = {"/authors","/author"})
public class AuthorController {

    @Autowired
    private AuthorService as;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // {prefix}/authors GET
    @JsonView(ModelView.AuthorLazy.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Author> getAuthor(){
//        as.save(new Author(01,"Tom","111@gmail.com", LocalDateTime.now(),null));
        List<Author> r = as.getAuthor();
        return r;
    }

    // {prefix}/authors?name=Tom GET
    @JsonView(ModelView.AuthorLazy.class)
    @RequestMapping(value = "", method = RequestMethod.GET, params = "name")
    public Author getAuthorByName(@RequestParam("name") String name){
//        as.save(new Author(01,"Tom","111@gmail.com", LocalDateTime.now(),null));
        Author author = as.getAuthorByName(name);
//        as.deleteAll();
        return author;
    }

    // {prefix}/authors/eager?name=Tom GET
    @JsonView(ModelView.AuthorEager.class)
    @RequestMapping(value = "/eager", method = RequestMethod.GET, params = "name")
    public Author getAuthorByNameEager(@RequestParam("name") String name){
//        as.save(new Author(01,"Tom","111@gmail.com", LocalDateTime.now(),null));
        Author author = as.getAuthorByNameEager(name);
//        as.deleteAll();
        return author;
    }

    // {prefix}/authors POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Author save(@RequestBody Author author){
        System.out.println(author);
        logger.info(author.toString());
        Author r = as.save(author);
        return r;
    }

    // {prefix}/authors DELETE
    @RequestMapping(value = "", method = RequestMethod.DELETE, params = {"name"})
    public int deleteByName(@RequestParam("name") String name){
        System.out.println(name);
        int r = as.deleteByName(name);
        return r;
    }

    // {prefix}/authors?name=__&email=__ PUT
    @RequestMapping(value = "", method = RequestMethod.PATCH, params = {"name","email"})
    public int updateEmailByName(@RequestParam("name") String name, @RequestParam("email") String email){
        Author r = as.getAuthorByName(name);
        r.setEmail(email);
        int re = as.updateByName(name,r);
        return re;
    }
}
