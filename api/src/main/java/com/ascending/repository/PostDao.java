package com.ascending.repository;

import com.ascending.model.Post;

import java.util.List;

public interface PostDao {
    public List<Post> get();
    public int deleteAll();
    public Post save(Post post);
    public Post getFirstRecord();
    public int deleteById(int id);
    public Post getById(int id);
    public int updateById(int id, Post post);
    public Post getByIdEager(int id);

}
