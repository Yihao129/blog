package com.ascending.jdbc;

import com.ascending.model.Author;
import com.ascending.model.Post;
import net.bytebuddy.asm.Advice;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class PostJDBCDaoTest {
    Logger logger= LoggerFactory.getLogger(this.getClass());


    @Before
    public void init(){
        logger.debug("debug info...........");
//        System.out.println("before test");

    }

    @Test
    public void getPostTest(){
        PostJDBCDao pjd=new PostJDBCDao();
        List<Post> posts=pjd.getPosts();

        Assert.assertEquals(posts.size(),0);
    }

    @Test
    public void insertPostsTest(){
//        PostJDBCDao pjd=new PostJDBCDao();
//
//        AuthorDao ad=new AuthorDao();
//        Author author=new Author(0,"Bob","111@gmail.com", LocalDateTime.now());
//        ad.deleteAllRecord();
//        ad.insertAuthor(author);
//        logger.debug(new Integer(ad.getIDByName("Bob")).toString());
//
//        Post post=new Post(0,"Bob","This is the first post.",LocalDateTime.now(),ad.getIDByName("Bob"));
//        pjd.deleteAll();
//        int r=pjd.insertPosts(post);
//        logger.debug("return: "+String.valueOf(r));
//        Assert.assertTrue(r==1);
    }

    @Test
    public void updatePostsByIDTest(){
//        PostJDBCDao pjd=new PostJDBCDao();
//
//        AuthorDao ad=new AuthorDao();
//        Author author=new Author(0,"Bob","111@gmail.com", LocalDateTime.now());
//        ad.deleteAllRecord();
//        ad.insertAuthor(author);
//        logger.debug(new Integer(ad.getIDByName("Bob")).toString());
//
//        Post post=new Post(0,"Bob","This is the first post.", LocalDateTime.now(),ad.getIDByName("Bob"));
//        pjd.deleteAll();
//        int r=pjd.insertPosts(post);
//
//        Post p = pjd.getPosts().get(0);
//        Post post_new=new Post(0,"Bob","This is the updated post.",LocalDateTime.now(),ad.getIDByName("Bob"));
//
//        r = pjd.updatePostsByID(p.getId(),post_new);
//        logger.debug(String.valueOf(p.getId()));
//
//        Assert.assertTrue(r==1);
    }

    @Test
    public void deletePostByIDTest(){
//        PostJDBCDao pjd=new PostJDBCDao();
//
//        AuthorDao ad=new AuthorDao();
//        Author author=new Author(0,"Bob","111@gmail.com", LocalDateTime.now());
//        ad.deleteAllRecord();
//        ad.insertAuthor(author);
//        logger.debug(new Integer(ad.getIDByName("Bob")).toString());
//
//        Post post=new Post(0,"Bob","This is the first post.",LocalDateTime.now(),ad.getIDByName("Bob"));
//        pjd.deleteAll();
//        int r=pjd.insertPosts(post);
//
//        Post p = pjd.getPosts().get(0);
//
//        r = pjd.deletePostByID(p.getId());
//        logger.debug(String.valueOf(p.getId()));
//
//        Assert.assertTrue(r==1);
    }


    @After
    public void destroy(){
        logger.debug("after test");
    }

}
