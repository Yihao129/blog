package com.ascending.repository;

import com.ascending.init.AppInit;
import com.ascending.model.Author;
import com.ascending.model.Comment;
import com.ascending.model.Post;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class PostDaoTest {
    @Autowired
    private PostDao pd;

    @Autowired
    private AuthorDao ad;

    @Autowired
    private CommentDao cd;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void  init(){
        Author a1 = new Author(01,"Bob","111@gmail.com",LocalDateTime.now(),null);
        ad.save(a1);

        Post p1 = new Post(01,"Bob","This is a post content.", LocalDateTime.now(),a1,null);
        pd.save(p1);

        cd.save(new Comment(01,"good one.",p1));
        cd.save(new Comment(01,"Excellent.",p1));
    }

    @After
    public void tear_down(){
        ad.deleteAll();
        pd.deleteAll();
    }

    @Test
    public void deleteAllTest(){
        int r = pd.deleteAll();
        Assert.assertEquals(r,1);
    }

    @Test
    public void saveTest(){
        Post post = pd.save(new Post(01,"Bob","This is a post content.", LocalDateTime.now(),null,null));
        Assert.assertEquals(post.getAuthor(),"Bob");
    }

    @Test
    public  void getFirstRecordTest(){
        Post post = pd.getFirstRecord();
        Assert.assertEquals(post.getAuthor(),"Bob");
    }

    @Test
    public void deleteByIdTest(){
        int id = pd.getFirstRecord().getId();
        int r = pd.deleteById(id);
        Assert.assertEquals(r,1);
    }

    @Test
    public void getByIdTest(){
         int id = pd.getFirstRecord().getId();
         Post post = pd.getById(id);
         Assert.assertEquals(post.getAuthor(),"Bob");
    }

    @Test
    public void updateByIdTest(){
        int id = pd.getFirstRecord().getId();
        int r = pd.updateById(id,new Post(01,"Solomon","This is an updated post.",LocalDateTime.now(),null,null));
        Assert.assertEquals(r,1);
    }

    @Test
    public void getByIdEagerTest(){
        int id = pd.getFirstRecord().getId();
        Post post = pd.getByIdEager(id);
        Assert.assertEquals(post.getAuthor(),"Bob");
        Assert.assertEquals(post.getComments().size(),2);
    };

}
