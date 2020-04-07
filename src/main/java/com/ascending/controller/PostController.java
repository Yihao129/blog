package com.ascending.controller;

import com.ascending.model.Comment;
import com.ascending.model.Post;
import com.ascending.model.view.ModelView;
import com.ascending.service.CommentService;
import com.ascending.service.PostService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/posts","/post"})
public class PostController {

    @Autowired
    private PostService ps;

    // {prefix}/posts GET
    @JsonView({ModelView.PostEager.class})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Post> get(){
        return ps.get();
    }

    // {prefix}/posts/{id} GET
    @JsonView({ModelView.PostLazy.class})
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Post getById(@PathVariable("id") int id){
        System.out.println(id+"--------------------");
        return ps.getById(id);
    }

    // {prefix}/posts/{id}/eager GET
    @JsonView({ModelView.PostEager.class})
    @RequestMapping(value = "{id}/eager", method = RequestMethod.GET)
    public Post getByIdEager(@PathVariable("id") int id){
        System.out.println(id+"--------------------");
        return ps.getByIdEager(id);
    }

    // {prefix}/posts POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Post save(@RequestBody Post post){
        return ps.save(post);
    }

    // {prefix}/posts/{id} DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteById(@PathVariable("id") int id){
        return ps.deleteById(id);
    }

    // {prefix}/posts/{id}?content=__ PATCH
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public int updateContentById(@PathVariable("id") int id, @RequestParam("content") String content){
        Post r = ps.getById(id);
        if(r==null) return 0;
        r.setContent(content);
        return ps.updateById(id,r);
    }
}
