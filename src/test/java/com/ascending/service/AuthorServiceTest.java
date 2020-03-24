package com.ascending.service;

import com.ascending.init.AppInit;
import com.ascending.model.Author;
import com.ascending.model.Post;
import com.ascending.repository.AuthorDao;
import com.ascending.repository.PostDao;
import org.junit.After;
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
public class AuthorServiceTest {
    @Autowired
    private AuthorService as;

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
        Author r = as.save(a1);

    };

}
