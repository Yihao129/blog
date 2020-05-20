package com.ascending.jdbc;

import com.ascending.model.Author;
import com.ascending.model.Comment;
import com.ascending.model.Post;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDateTime;

public class CommentDaoTest {

    @Test
    public void getCommentTest(){
        CommentDao cd=new CommentDao();
        Assert.assertTrue(cd.getComment().size()==0);
    }

    @Test
    public void deleteAllTest(){
//        CommentDao cd=new CommentDao();
//        Assert.assertTrue(cd.deleteAll()==1);
    }

    @Test
    public void insertTest(){
//        AuthorDao ad=new AuthorDao();
//        PostJDBCDao pjd=new PostJDBCDao();
//        CommentDao cd=new CommentDao();
//
//        cd.deleteAll();
//        pjd.deleteAll();
//        ad.deleteAllRecord();
//
//        Author author=new Author(0,"Bob","111@gmail.com", LocalDateTime.now());
//        ad.insertAuthor(author);
//
//        Post post=new Post(0,"Bob","This is the first post.",LocalDateTime.now(),ad.getIDByName("Bob"));
//        pjd.insertPosts(post);
//
//        int post_id=pjd.getPosts().get(0).getId();
//        Comment comment=new Comment(0,"good one!",post_id);
//        int r=cd.insert(comment);
//        Assert.assertTrue(r==1);
    }



}
