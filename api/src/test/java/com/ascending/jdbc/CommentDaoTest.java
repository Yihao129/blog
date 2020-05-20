package com.ascending.jdbc;

import com.ascending.model.Author;
import com.ascending.model.Comment;
import com.ascending.model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDateTime;

public class CommentDaoTest {

    private CommentDao cd;

    @Before
    public void init(){
        cd = new CommentDao();
        cd.deleteAll();
    }

    @Test
    public void getCommentTest(){
        Assert.assertTrue(cd.getComment().size()==0);
    }

    @Test
    public void deleteAllTest(){
        Assert.assertTrue(cd.deleteAll()==1);
    }

    @Test
    public void insertTest(){
        AuthorDao ad=new AuthorDao();
        PostJDBCDao pjd=new PostJDBCDao();
        CommentDao cd=new CommentDao();

        cd.deleteAll();
        pjd.deleteAll();
        ad.deleteAllRecord();

        Author author=new Author(0,"Bob","111@gmail.com", LocalDateTime.now(), null);
        ad.insertAuthor(author);

        Post post=new Post(0,"Bob","This is the first post.",LocalDateTime.now(), ad.getAuthor().get(0), null);
        pjd.insertPosts(post);

        Comment comment=new Comment(0,"good one!", pjd.getPosts().get(0));
        int r=cd.insert(comment);
        Assert.assertTrue(r==1);
    }



}
