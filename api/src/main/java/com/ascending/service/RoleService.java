package com.ascending.service;

import com.ascending.model.Role;
import com.ascending.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao rd;

    public List<Role> get(){
        return rd.get();
    };

    public Role getById(long id){
        return rd.getById(id);
    };

    public Role save(Role role){
        return rd.save(role);
    };

    public int deleteById(long id){
        return rd.deleteById(id);
    };

    public int update(Role role){
        return rd.update(role);
    };

}
