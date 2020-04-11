package com.ascending.service;

import com.ascending.init.AppInit;
import com.ascending.model.User;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class JWTServiceTest {

    @Autowired
    private JWTService jwt;

    @Before
    public void init(){

    }

    @Test
    public void generateTokenTest(){
        User user = new User();
        user.setName("wang yang");
        user.setId(01);
        String token = jwt.generateToken(user);

        Assert.assertNotEquals(token.split(".").length,3);
    }

    @Test
    public void decodeTokenTest(){
        User user = new User();
        user.setName("wang yang");
        user.setId(01);
        String token = jwt.generateToken(user);
        Claims claims = jwt.decodeToken(token);
        Assert.assertEquals(claims.getSubject(),"wang yang");
    }


}
