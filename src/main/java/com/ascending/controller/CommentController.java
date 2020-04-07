package com.ascending.controller;

import com.ascending.model.Author;
import com.ascending.model.Comment;
import com.ascending.model.view.ModelView;
import com.ascending.service.CommentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/comments","/comment"})
public class CommentController {

    @Autowired
    private CommentService cs;

    // {prefix}/comments GET
    @JsonView(ModelView.CommentLazy.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Comment> get(){
        return cs.get();
    }

    // {prefix}/comments/{id} GET
    @JsonView(ModelView.CommentLazy.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Comment getById(@PathVariable("id") int id){
        return cs.getById(id);
    }

    // {prefix}/comments/{id}/eager GET
    @JsonView(ModelView.CommentEager.class)
    @RequestMapping(value = "/{id}/eager", method = RequestMethod.GET)
    public Comment getByIdEager(@PathVariable("id") int id){
        return cs.getByIdEager(id);
    }

    // {prefix}/comments POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Comment save(@RequestBody Comment comment){
        return cs.save(comment);
    }

    // {prefix}/comments DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteById(@PathVariable("id") int id){
        return cs.deleteById(id);
    }

    // {prefix}/comments/{id}?content=__ PATCH
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public int updateContentById(@PathVariable("id") int id, @RequestParam("content") String content){
        Comment comment = cs.getById(id);
        comment.setContent(content);
        int r = cs.updateById(comment.getId(), comment);
        return r;
    }


}
