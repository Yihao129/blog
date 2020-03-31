package com.ascending.service;

import com.ascending.init.AppInit;
import com.ascending.model.Comment;
import com.ascending.model.Post;
import com.ascending.repository.CommentDao;
import com.ascending.repository.PostDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class CommentServiceTest {
    @Autowired
    private CommentService cs;

    @Autowired
    private CommentDao cd;

    @Autowired
    private PostDao pd;

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
        Comment r = cs.save(new Comment(01,"good one",null));
        assertEquals(r.getContent(),"good one");
    };

    @Test
    public void deleteAllTest(){
        int r = cs.deleteAll();
        assertEquals(r,2);
    };

    @Test
    public void getFirstRecordTest(){
        Comment comment = cs.getFirstRecord();
        assertEquals(comment.getContent(),"good one");
    };

    @Test
    public void deleteByIdTest(){
        Comment comment = cs.getFirstRecord();
        int r = cs.deleteById(comment.getId());
        assertEquals(r,1);
    };

    @Test
    public void getTest(){
        assertEquals(cs.get().size(),2);
    };

    @Test
    public void updateByIdTest(){
        Comment comment = cs.getFirstRecord();
        int r = cs.updateById(comment.getId(),new Comment(01,"bad!",null));
        assertEquals(r,1);
    };

}
