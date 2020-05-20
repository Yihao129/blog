package com.ascending.service;

import com.ascending.model.Resource;
import com.ascending.repository.ResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    ResourceDao rd;

    public List<Resource> getByUserId(Long id){
        return rd.getByUserId(id);
    }

    public Resource getByUserIdAndFileName(Long id, String fileName){
        return rd.getByUserIdAndFileName(id,fileName);
    }

    public Resource save(Resource res){
        return rd.save(res);
    }

    public int deleteByUserId(Long id){
        return rd.deleteByUserId(id);
    }


}
