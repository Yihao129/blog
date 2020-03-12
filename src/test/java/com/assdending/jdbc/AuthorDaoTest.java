package com.assdending.jdbc;

import com.assending.jdbc.AuthorDao;
import com.assending.model.Author;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;

public class AuthorDaoTest {

    AuthorDao ad=null;
    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Before
    public void init() throws SQLException {
        ad=new AuthorDao();
        ad.deleteAllRecord();
        logger.debug("before test");
    }

    @Test
    public void getAuthorTest(){
        ad.deleteAllRecord();
        Assert.assertEquals(ad.getAuthor().size(),0);
    }

    @Test
    public void insertAuthorTest(){
        ad.deleteAllRecord();
        Author t=new Author(0,"Bob","111@gmail.com", Date.valueOf("2000-1-1"));
        int r = ad.insertAuthor(t);
        logger.debug(String.format("Insert table with return value %d",r));
        Assert.assertEquals(r,1);
    }

    @Test
    public void updateAuthorByNameTest(){
        ad.deleteAllRecord();
        Author t=new Author(0,"Bob","111@gmail.com", Date.valueOf("2000-1-1"));
        int r = ad.insertAuthor(t);

        t=new Author(0,"Tom","111@gmail.com", Date.valueOf("2000-1-1"));
        r = ad.updateAuthorByName("Bob",t);
        Assert.assertEquals(r,1);
    }

    @Test
    public void deleteByNameTest(){
        ad.deleteAllRecord();
        Author t=new Author(0,"Bob","111@gmail.com", Date.valueOf("2000-1-1"));
        ad.insertAuthor(t);

        int r = ad.deleteByName("Bob");
        logger.debug(String.format("Delete table with return value %d",r));
        Assert.assertEquals(r,1);
    }

    @Test
    public void getIDByNameTest(){
        AuthorDao ad=new AuthorDao();
        Author t=new Author(0,"Bob","111@gmail.com", Date.valueOf("2000-1-1"));

        ad.deleteAllRecord();
        ad.insertAuthor(t);

        int r = ad.getIDByName("Bob");
        Assert.assertTrue(r!=-1);
    }

    @After
    public void after(){
        logger.debug("after test");
    }





}
