package com.ascending.repository;

import com.ascending.model.User;

import java.util.List;

public interface UserDao {
    public User save(User user);
    public int deleteById(long id);
    public int deleteAll();
    public List<User> get();
    public User getById(long id);
    public int updateById(long id, User user);
    public User getUserByCredential(String emailOrName);
    public User getUserByIdEager(long id);
}
