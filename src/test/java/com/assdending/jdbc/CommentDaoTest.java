package com.assdending.jdbc;

import com.assending.jdbc.AuthorDao;
import com.assending.jdbc.CommentDao;
import com.assending.jdbc.PostJDBCDao;
import com.assending.model.Author;
import com.assending.model.Comment;
import com.assending.model.Post;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class CommentDaoTest {

    @Test
    public void getCommentTest(){
        CommentDao cd=new CommentDao();
        Assert.assertTrue(cd.getComment().size()==0);
    }

    @Test
    public void deleteAllTest(){
        CommentDao cd=new CommentDao();
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

        Author author=new Author(0,"Bob","111@gmail.com", Date.valueOf("2000-3-1"));
        ad.insertAuthor(author);

        Post post=new Post(0,"Bob","This is the first post.",Date.valueOf("2000-1-2"),ad.getIDByName("Bob"));
        pjd.insertPosts(post);

        int post_id=pjd.getPosts().get(0).getId();
        Comment comment=new Comment(0,"good one!",post_id);
        int r=cd.insert(comment);
        Assert.assertTrue(r==1);
    }



}
