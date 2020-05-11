package com.ascending.repository;

import com.ascending.init.AppInit;
import com.ascending.model.Author;
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
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class AuthorDaoTest {
    @Autowired
    private AuthorDao ad;

    @Autowired
    private PostDao pd;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        Author a1 = new Author(02, "Tom", "11@gmail.com", LocalDateTime.now(),null);
        ad.save(a1);

        Post p1 = new Post(01,"Tom","post 1",LocalDateTime.now(),a1,null);
        Post p2 = new Post(02,"Tom","post 2",LocalDateTime.now(),a1,null);
        pd.save(p1);
        pd.save(p2);
    }

    @After
    public void tear_down(){
        ad.deleteAll();
    }


    @Test
    public void saveTest(){
        Author a1 = new Author(02, "Timor", "11@gmail.com", LocalDateTime.now(),null);
        Author r = ad.save(a1);
        Assert.assertEquals(r.getName(),a1.getName());
    }

    @Test
    public void deleteByNameTest(){
        Author a1 = new Author(02, "Timor", "11@gmail.com", LocalDateTime.now(),null);
        ad.save(a1);

        int r = ad.deleteByName("Timor");
        Assert.assertEquals(r,1);
    }

    @Test
    public void getAuthorTest(){
        List<Author> re = ad.getAuthor();
        logger.info(String.valueOf(re.size()));
        Assert.assertTrue(re.size()==1);
    }

    @Test
    public void deleteAllTest(){
        int r = ad.deleteAll();
        Assert.assertEquals(1,r);
    }

    @Test
    public void getAuthorByNameTest(){
        Author author = ad.getAuthorByName("Tom");
        Assert.assertEquals(author.getName(),"Tom");
    }

    @Test
    public void updateByNameTest(){
        int r = ad.updateByName("Tom",new Author(02,"Solomon","222@gmail.com",LocalDateTime.now(),null));
        Assert.assertEquals(r,1);
        logger.info(ad.getAuthorByName("Solomon").toString());
    }

    @Test
    public void getAuthorByNameEagerTest(){
        Author author = ad.getAuthorByNameEager("Tom");
        Assert.assertEquals(author.getName(),"Tom");
        Assert.assertEquals(author.getPosts().size(),2);
        author.getPosts().forEach(e->logger.info(e.toString()));
    }
    
    
}
