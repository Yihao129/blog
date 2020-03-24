package com.ascending.repository;

import com.ascending.model.Comment;
import com.ascending.util.HibernateUtil;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.nio.channels.SelectableChannel;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Comment save(Comment comment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(comment);
            transaction.commit();
            session.close();
            return comment;
        }catch (Exception e){
            logger.error("fail to insert\n",e);
            session.close();
            return null;
        }
    }

    @Override
    public int deleteAll() {
        String hql = "delete from Comment";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = (Query) session.createQuery(hql);
            int r = query.executeUpdate();
            transaction.commit();
            return r;
        }catch (Exception e){
            logger.error("fail to delete all\n",e);
            session.close();
            return -1;
        }
    }

    @Override
    public Comment getFirstRecord() {
        String hql = "from Comment";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Query query = session.createQuery(hql);
            query.setMaxResults(1);
            return (Comment) query.getSingleResult();
        }catch (Exception e){
            logger.error("fail to get first record\n",e);
            session.close();
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        String hql = "delete from Comment where id=:id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            int r = query.executeUpdate();
            transaction.commit();
            return r;
        }catch (Exception e){
            logger.error("fail to delete by id\n",e);
            session.close();
            return -1;
        }
    }

    @Override
    public List<Comment> get() {
        String hql = "from Comment";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(hql);
        List<Comment> comments = query.list();
        return comments;
    }

    @Override
    public int updateById(int id, Comment comment) {
        String hql = "update Comment set content=:content where id=:id2";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("content",comment.getContent());
            query.setParameter("id2",id);
            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            logger.error("fail to update\n",e);
            session.close();
            return -1;
        }
    }


}
