package com.ascending.repository;

import com.ascending.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleDao {
    public List<Role> get();
    public Role getById(long id);
    public Role save(Role role);
    public int deleteById(long id);
    public int update(Role role);

}
