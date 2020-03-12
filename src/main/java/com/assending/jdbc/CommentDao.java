package com.assending.jdbc;

import com.assending.model.Author;
import com.assending.model.Comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDao {

    Connection conn=null;
    Statement stmt=null;

    public CommentDao(){
        try{
            conn = DriverManager.getConnection(PostJDBCDao.DBURL,PostJDBCDao.USER,PostJDBCDao.PASS);
            stmt = conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Comment> getComment() {
        List<Comment> comments= new ArrayList<>();

        try{
            String sql="select * from comment";
            ResultSet rs = stmt.executeQuery(sql);

            int id;
            String content;
            int post_id;

            while(rs.next()){
                Comment t=new Comment();
                t.setId(rs.getInt("id"));
                t.setContent(rs.getString("content"));
                t.setPost_id(rs.getInt("post_id"));
                comments.add(t);
            }


        }
        catch (Exception e){

        }
        finally {

        }



        return comments;

    }

    public int deleteAll(){
        String sql="delete from comment";
        try{
            stmt.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public int insert(Comment comment){
        String sql=String.format("insert into comment(content,post_id) values('%s',%d)",comment.getContent(),comment.getPost_id());
        try{
            stmt.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }


    public static void main(String[] args) {
        CommentDao cd=new CommentDao();
        System.out.println(cd.getComment());
    }


}
