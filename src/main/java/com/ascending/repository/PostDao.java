package com.ascending.repository;

import com.ascending.model.Post;

public interface PostDao {
    public int deleteAll();
    public Post save(Post post);
    public Post getFirstRecord();
    public int deleteById(int id);
    public Post getById(int id);
    public int updateById(int id, Post post);
    public Post getByIdEager(int id);

}
