package com.ascending.repository;

import com.ascending.model.Comment;

import java.util.List;

public interface CommentDao {
    public Comment save(Comment comment);
    public int deleteAll();
    public Comment getFirstRecord();
    public int deleteById(int id);
    public List<Comment> get();
    public int updateById(int id, Comment comment);
}
