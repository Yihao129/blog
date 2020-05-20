package com.ascending.jdbc;

import com.ascending.model.Author;
import com.ascending.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostJDBCDao {
    static final String DBURL = System.getProperty("database.url");
    static final String USER = System.getProperty("database.user");
    static final String PASS = System.getProperty("database.password");
    Logger logger= LoggerFactory.getLogger(this.getClass());
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public PostJDBCDao(){
        try{
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);
            //STEP 3: Execute a query
            logger.debug("Creating statement...");
            stmt = conn.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }

    };


    public List<Post> getPosts(){
        List<Post> posts = new ArrayList<Post>();
        try {
            String sql;
            sql = "select * from post";
            rs = stmt.executeQuery(sql);
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                int id  = rs.getInt("id");
                String author = rs.getString("author");
                String content = rs.getString("content");
                LocalDateTime date = ((Timestamp) rs.getObject("date")).toLocalDateTime();
                int author_id  = rs.getInt("author_id");

                //Fill the object
                Post p = new Post();
                p.setId(id);
                p.setAuthor(author);
                p.setContent(content);
                p.setDate(date);
                posts.add(p);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
//                if(rs != null) rs.close();
//                if(stmt != null) stmt.close();
//                if(conn != null) conn.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    public int deleteAll(){
        String sql="delete from post";
        try{
            stmt.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }


    public int insertPosts(Post post){

        try{
            String sql=String.format("insert into post(author,content,date,author_id) values('%s','%s','%s','%d')",
                    post.getAuthor(),post.getContent(),post.getDate().toString(),post.getWriter().getId());
            logger.debug(sql);
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
            return 0;
        }
        finally {

        }
        return 1;
    }

    public int updatePostsByID(int id, Post post){

        try{
            String sql=String.format("update post set author='%s',content='%s',date='%s',author_id=%d where id=%d",
                    post.getAuthor(),post.getContent(),post.getDate().toString(),post.getWriter().getId(),id);
            logger.debug(sql);
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        finally {

        }
        return 1;
    }

    public int deletePostByID(int id){
        try{
            String sql=String.format("delete from post where id=%d",id);
            logger.debug(sql);
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        finally {

        }
        return 1;
    }





}
