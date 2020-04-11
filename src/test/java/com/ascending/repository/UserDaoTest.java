package com.ascending.repository;

import com.ascending.init.AppInit;
import com.ascending.model.User;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class UserDaoTest {

    @Autowired
    private UserDao ud;
    private long saved_id;
    private User saved_user = null;

    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        saved_user = new User(01L,"Wang","123","22","Wang","Yang","111@gmail.com",null);
        saved_user = ud.save(saved_user);
        saved_id = saved_user.getId();
        logger.info(String.valueOf(saved_id));
    }

    @After
    public void tear_down(){
        ud.deleteById(saved_id);
    }

    @Test
    public void saveTest(){
        User user = new User(01L,"Bob","123","22","Wang","Yang","111@gmail.com",null);
        Assert.assertEquals(user.getName(),"Bob");
        ud.deleteById(user.getId());
    }

    @Test
    public void deleteByIdTest(){
        Assert.assertEquals(ud.deleteById(saved_id),1);
    }

    @Test
    public void deleteAllTest(){
        Assert.assertEquals(ud.deleteAll(),1);
    }

    @Test
    public void getTest(){
        Assert.assertEquals(ud.get().size(),1);
    }

    @Test
    public void getByIdTest(){
        Assert.assertEquals(ud.getById(saved_id).getName(),"Wang");
    }

    @Test
    public void updateByIdTest(){
        saved_user.setName("new name");
        int r = ud.updateById(saved_id,saved_user);
        Assert.assertEquals(ud.getById(saved_id).getName(),"new name");
    }

    @Test
    public void getUserByCredentialTest(){
        User user1 = ud.getUserByCredential("Wang");
        User user2 = ud.getUserByCredential("111@gmail.com");
        Assert.assertEquals(user1.getName(),user2.getName());
        Assert.assertNotNull(user1.getRoles());
    }

    @Test
    public void getByIdEagerTest(){
        User user1 = ud.getUserByIdEager(saved_id);
        Assert.assertEquals(user1.getName(),saved_user.getName());
        Assert.assertNotNull(user1.getRoles());

    }

}
