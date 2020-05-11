package com.ascending.repository;

import com.ascending.init.AppInit;
import com.ascending.model.Resource;
import com.ascending.model.Role;
import com.ascending.model.User;
import com.ascending.service.RoleService;
import com.ascending.service.UserService;
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
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class ResourceDaoTest {

    @Autowired
    private ResourceDao rd;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private User testUser = null;

    @Before
    public void init(){
        testUser = new User();
        testUser.setId(10);
        testUser.setName("Bob");
        testUser.setEmail("123@gmail.com");
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleService.getById(3));
        testUser.setRoles(roles);
        userService.save(testUser);

        Resource res = new Resource(testUser,"1.txt","ahsjsahdk", LocalDateTime.now());
        rd.save(res);

        res = new Resource(testUser,"2.txt","ahsjsahsada", LocalDateTime.now());
        rd.save(res);
    }
    @After
    public void tearDown(){
        rd.deleteByUserId(Long.valueOf(testUser.getId()));
        userService.deleteById(testUser.getId());
    }

    @Test
    public void getByUserIdTest(){
        List<Resource> res = rd.getByUserId(Long.valueOf(testUser.getId()));
        Assert.assertEquals(res.size(),2);
    }

    @Test
    public void getByUserIdAndFileNameTest(){
        Resource res = rd.getByUserIdAndFileName(Long.valueOf(testUser.getId()),"2.txt");
        Assert.assertEquals(res.getFileName(),"2.txt");
    }


}
