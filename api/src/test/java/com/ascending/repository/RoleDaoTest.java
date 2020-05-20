package com.ascending.repository;

import com.ascending.init.AppInit;
import com.ascending.model.Role;
import com.ascending.model.User;
import com.ascending.repository.RoleDao;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class RoleDaoTest {

    @Autowired
    private RoleDao rd;
    private long saved_id;
    private Role saved_role = null;

    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        saved_role = new Role(01,"manager","/comment",true,true,true,true,null);
        saved_role = rd.save(saved_role);
        saved_id = saved_role.getId();
        logger.info(String.valueOf(saved_id));
    }

    @After
    public void tear_down(){
        rd.deleteById(saved_id);
    }

    @Test
    public void getTest(){
        Assert.assertEquals(rd.get().size(),3);
    }

    @Test
    public void getByIdTest(){
        Assert.assertEquals(rd.getById(saved_id).getName(),saved_role.getName());
    }

    @Test
    public void saveTest(){
        Role role = new Role(01,"manager","/comment",true,true,true,true,null);
        Assert.assertEquals(role.getName(),"manager");
        rd.deleteById(role.getId());
    }

    @Test
    public void deleteByIdTest(){
        Assert.assertEquals(rd.deleteById(saved_id),1);
    }

    @Test
    public void updateTest(){
        saved_role.setName("super");
        rd.update(saved_role);
        Assert.assertEquals(rd.getById(saved_id).getName(),"super");
    }
}
