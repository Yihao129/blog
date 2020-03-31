package com.ascending.service;

import com.ascending.repository.CommentDao;
import com.ascending.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao cd;

    public Comment save(Comment comment){
        return cd.save(comment);
    };

    public int deleteAll(){
        return cd.deleteAll();
    };

    public Comment getFirstRecord(){
        return cd.getFirstRecord();
    };

    public int deleteById(int id){
        return cd.deleteById(id);
    };

    public List<Comment> get(){
        return cd.get();
    };

    public int updateById(int id, Comment comment){
        return cd.updateById(id,comment);
    };


}
