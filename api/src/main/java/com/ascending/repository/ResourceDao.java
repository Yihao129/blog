package com.ascending.repository;

import com.ascending.model.Post;
import com.ascending.model.Resource;

import java.util.List;

public interface ResourceDao {
    public List<Resource> getByUserId(Long id);
    public Resource getByUserIdAndFileName(Long id, String fileName);
    public Resource save(Resource res);
    public int deleteByUserId(Long id);
}
