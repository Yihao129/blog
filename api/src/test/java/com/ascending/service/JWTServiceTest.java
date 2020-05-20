package com.ascending.service;

import com.ascending.init.AppInit;
import com.ascending.model.Role;
import com.ascending.model.User;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class JWTServiceTest {

    @Autowired
    private JWTService jwt;

    @Autowired
    private UserService userService;

    @Before
    public void init(){

    }

    @Test
    public void generateTokenTest(){
        User user = new User();
        user.setId(1);
        user.setName("Tom");
        List<Role> roles = new LinkedList<Role>();
        Role role = new Role();
        System.out.println(role);
        role.setAllowedResource("/files,/auth");
        role.setAllowedCreate(true);
        role.setAllowedRead(true);
        roles.add(role);
        user.setRoles(roles);

        String token = jwt.generateToken(user);
        Assert.assertTrue(token.matches("[^.]+\\.[^.]+\\.[^.]+"));
    }

    @Test
    public void decodeTokenTest(){
        User user = new User();
        user.setId(1);
        user.setName("Tom");
        List<Role> roles = new LinkedList<Role>();
        Role role = new Role();
        System.out.println(role);
        role.setAllowedResource("/files,/auth");
        role.setAllowedCreate(true);
        role.setAllowedRead(true);
        roles.add(role);
        user.setRoles(roles);

        String token = jwt.generateToken(user);
        Claims claims = jwt.decodeToken(token);
        Assert.assertEquals(claims.getId(),"1");
    }


}
