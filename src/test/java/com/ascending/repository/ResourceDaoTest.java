package com.ascending.repository;

import com.ascending.init.AppInit;
import com.ascending.model.Resource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class ResourceDaoTest {

    @Autowired
    private ResourceDao rd;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        Resource res = new Resource(Long.valueOf(1),"1.txt","ahsjsahdk", LocalDateTime.now());
        rd.save(res);

        res = new Resource(Long.valueOf(1),"2.txt","ahsjsahsada", LocalDateTime.now());
        rd.save(res);
    }
    @After
    public void tearDown(){
        rd.deleteByUserId(Long.valueOf(1));
    }

    @Test
    public void getByUserIdTest(){
        List<Resource> res = rd.getByUserId(Long.valueOf(1));
        Assert.assertEquals(res.size(),2);
    }

    @Test
    public void getByUserIdAndFileNameTest(){
        Resource res = rd.getByUserIdAndFileName(Long.valueOf(1),"2.txt");
        Assert.assertEquals(res.getFileName(),"2.txt");
    }


}
