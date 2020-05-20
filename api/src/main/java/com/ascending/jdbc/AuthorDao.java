package com.ascending.jdbc;

import com.ascending.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthorDao(){
        try{
            conn = DriverManager.getConnection(PostJDBCDao.DBURL,PostJDBCDao.USER,PostJDBCDao.PASS);
            stmt = conn.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public List<Author> getAuthor() {
        List<Author> authors= new ArrayList<>();

        try{

            String sql="select * from author";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Author t=new Author();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setEmail(rs.getString("email"));
                t.setRegister_date(((Timestamp)rs.getObject("register_date")).toLocalDateTime());
                authors.add(t);
            }
        }
        catch (Exception e){
        }
        finally {
        }

        return authors;

    }



    public int insertAuthor(Author author){

        String sql=String.format("insert into author(name,email,register_date) values('%s','%s','%s')",
                author.getName(),
                author.getEmail(),
                author.getRegister_date().toString());

        logger.debug(sql);

        try{
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
            return 0;
        }
        finally {

        }

        return 1;
    }

    public int updateAuthorByName(String name, Author author){

        String sql=String.format("update author set name='%s',email='%s',register_date='%s' where name='%s'",
                author.getName(),
                author.getEmail(),
                author.getRegister_date().toString(),
                name);

        logger.debug(sql);

        try{
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
            return 0;
        }
        return 1;
    }

    public int deleteByName(String name){

        String sql=String.format("delete from author where name='%s'", name);

        logger.debug(sql);

        try{
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

    public int deleteAllRecord(){
        String sql=String.format("delete from author");
        logger.debug(sql);
        try{
            stmt.executeUpdate(sql);
        }
        catch (Exception e){

            return 0;
        }
        finally {

        }
        return 1;
    }

    public int getIDByName(String name){
        String sql=String.format("select id from author where name='%s'",name);
        logger.debug(sql);
        try{
            rs=stmt.executeQuery(sql);
            if(rs.next()) return rs.getInt("id");
            else return -1;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) throws SQLException {
//        Author t=new Author(0,"Bob","111@gmail.com", LocalDateTime.now());
//        AuthorDao ad=new AuthorDao();
//
//        int r = ad.getIDByName("Bob");
//        System.out.println(r);

    }

}
