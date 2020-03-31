package com.ascending.repository;

import com.ascending.model.Author;
import com.ascending.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sun.security.ssl.HandshakeInStream;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public Author save(Author author) {
        Transaction trans=null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            session.save(author);
            trans.commit();
            session.close();
            return author;
        }catch (Exception e){
            if(trans!=null) trans.rollback();
            logger.error("fail to insert ",e);
            return null;
        }

    }

    @Override
    public List<Author> getAuthor() {
        List<Author> authors = new ArrayList<Author>();
        String sql="FROM Author";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Query<Author> query = session.createQuery(sql);
            authors = query.list();
            session.close();
            return authors;
        }catch (Exception e){
            logger.error("failure to retrive data record",e);
            session.close();
            return authors;
        }

    }

    @Override
    public int deleteAll() {
        String hql="delete from Author";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            logger.error("Fail to delete all ",e);
            transaction.rollback();
            session.close();
            return -1;
        }
    }

    @Override
    public Author getAuthorByName(String name) {
        String hql = "from Author as A where A.name=:name";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction;
        Author author = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("name",name);
            author = (Author) query.uniqueResult();
            return author;
        }catch (Exception e){
            logger.error("select by name failed\n",e);
            return null;
        }

    }

    @Override
    public int deleteByName(String name) {
        String hql = "delete from Author where name=:name";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("name",name);
            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            session.close();
            logger.error("fail to delete by name\n",e);
            return -1;
        }
    }

    @Override
    public int updateByName(String name, Author author) {
        String hql = "update Author set name=:name, email=:email, register_date=:register_date where name=:anchor_name";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("name",author.getName());
            query.setParameter("email",author.getEmail());
            query.setParameter("register_date",author.getRegister_date());
            query.setParameter("anchor_name",name);

            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            logger.error("fail to update by name\n",e);
            session.close();
            return -1;
        }
    }

    @Override
    public Author getAuthorByNameEager(String name){
        String hql = "from Author as A left join fetch A.posts where A.name=:name";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Query query = session.createQuery(hql);
            query.setParameter("name",name);
            Author author = (Author) query.getSingleResult();
            session.close();
            return author;
        }catch (Exception e){
            logger.error("fail to get author by name eager\n",e);
            session.close();
            return null;
        }
    }

    @Override
    public List<Author> getByEager(){
        String hql = "from Author as A left join fetch A.posts";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Query query = session.createQuery(hql);
            List<Author> r = query.list();
            session.close();
            return r;
        }catch (Exception e){
            session.close();
            logger.error("Fail to get by eager\n",e);
            return null;
        }
    }
}
