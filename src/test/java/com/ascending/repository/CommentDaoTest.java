package com.ascending.repository;

import com.ascending.init.AppInit;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class CommentDaoTest {
    @Autowired
    private CommentDao cd;

    @Autowired
    private PostDao pd;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        Post p1 = new Post(01,"Bob","post1", LocalDateTime.now(),null,null);
        pd.save(p1);

        cd.save(new Comment(01,"good one",p1));
        cd.save(new Comment(01,"nice job!",p1));
    }

    @After
    public void tear_down(){
        cd.deleteAll();
        pd.deleteAll();
    }

    @Test
    public void saveTest(){
        Comment comment = cd.save(new Comment(01,"good one",null));
        Assert.assertNotNull(comment);
    }

    @Test
    public void deleteAllTest(){
        int r = cd.deleteAll();
        Assert.assertEquals(r,2);
    }

    @Test
    public void getFirstRecordTest(){
        Comment comment = cd.getFirstRecord();
        Assert.assertEquals(comment.getContent(),"good one");
    }

    @Test
    public void deleteByIdTest(){
        int id = cd.getFirstRecord().getId();
        int r = cd.deleteById(id);
        Assert.assertEquals(r,1);
    }

    @Test
    public void getTest(){
        List<Comment> comments = cd.get();
        Assert.assertEquals(2,comments.size());
    }

    @Test
    public void updateByIdTest(){
        int id = cd.getFirstRecord().getId();
        int r = cd.updateById(id,new Comment(100,"updated comment.",null));
        Assert.assertEquals(r,1);
    }

}
