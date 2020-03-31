package com.ascending.service;

import com.ascending.model.Post;
import com.ascending.repository.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostDao pd;

    public int deleteAll(){
        return pd.deleteAll();
    };

    public Post save(Post post){
        return pd.save(post);
    };

    public Post getFirstRecord(){
        return pd.getFirstRecord();
    };

    public int deleteById(int id){
        return pd.deleteById(id);
    };

    public Post getById(int id){
        return pd.getById(id);
    };

    public int updateById(int id, Post post){
        return pd.updateById(id,post);
    };

    public Post getByIdEager(int id){
        return pd.getByIdEager(id);
    };
}
