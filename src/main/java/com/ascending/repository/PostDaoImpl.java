package com.ascending.repository;

import com.ascending.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDaoImpl implements PostDao{
    @Autowired
    private SessionFactory sessionFactory;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public List<Post> get(){
        Session session = sessionFactory.openSession();
        return session.createQuery("from Post").list();
    }

    @Override
    public int deleteAll() {
        String hql = "delete from Post";
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            session.close();
            logger.error("fail to delete all\n",e);
            return -1;
        }
    }

    @Override
    public Post save(Post post) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
            session.close();
            return post;
        }catch (Exception e){
            logger.error("fail to insert\n",e);
            session.close();
            return null;
        }
    }

    @Override
    public Post getFirstRecord() {
        String hql="from Post";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        Post post = (Post) query.getSingleResult();
        return post;
    }

    @Override
    public int deleteById(int id) {
        String hql="delete from Post where id=:id";
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            logger.error("fail to delete by id\n",e);
            session.close();
            return -1;
        }
    }

    @Override
    public Post getById(int id) {
        String hql = "from Post where id=:id";
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            Post post = (Post) query.getSingleResult();
            session.close();
            return post;
        }catch (Exception e){
            session.close();
            logger.error("fail to get by id\n",e);
            return null;
        }
    }

    @Override
    public int updateById(int id, Post post) {
        String hql = "update Post set author=:author, content=:content, date=:date where id=:id2";
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("author",post.getAuthor());
            query.setParameter("content",post.getContent());
            query.setParameter("date",post.getDate());
            query.setParameter("id2",id);
            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            logger.error("fail to update by id\n",e);
            session.close();
            return -1;
        }
    }

    @Override
    public Post getByIdEager(int id){
        String hql = "from Post as P left join fetch P.comments left join fetch P.writer where P.id=:id";
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            Post post = (Post) query.getSingleResult();
            session.close();
            return post;
        }catch (Exception e){
            logger.error("fail to get by id eager\n",e);
            session.close();
            return null;
        }
    };

}
