package com.ascending.service;

import com.ascending.model.User;
import com.ascending.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao ud;

    public User save(User user){
     return ud.save(user);
    }

    public int deleteById(long id){
        return ud.deleteById(id);
    }

    public int deleteAll(){
        return ud.deleteAll();
    }

    public List<User> get(){
        return ud.get();
    }

    public User getById(long id){
        return ud.getById(id);
    }

    public int updateById(long id, User user){
        return ud.updateById(id,user);
    }

}
