package com.ascending.repository;

import com.ascending.model.User;
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
public class UserDaoImpl implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public User save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction=session.beginTransaction();
            long r = (long) session.save(user);
            transaction.commit();
            session.close();
            return  user;
        }catch (Exception e){
            session.close();
            logger.error("Fail to save user\n",e);
            return null;
        }
    }

    @Override
    public int deleteById(long id){
        String hql = "delete from User where id=:id";
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = (Query) session.createQuery(hql);
            query.setParameter("id",id);
            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            session.close();
            logger.error("Fail to delete by id\n",e);
            return -1;
        }
    }

    @Override
    public int deleteAll(){
        String hql = "delete from User";
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = (Query) session.createQuery(hql);
            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            session.close();
            logger.error("Fail to delete all\n",e);
            return -1;
        }
    }

    @Override
    public List<User> get(){
        Session session = sessionFactory.openSession();
        List<User> r = session.createQuery("from User").list();
        return r;
    }

    @Override
    public User getById(long id){
        String hql = "from User where id=:id";
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            User r = (User) query.getSingleResult();
            session.close();
            return r;
        }catch (Exception e){
            session.close();
            logger.error("Fail to get by id\n",e);
            return null;
        }
    }

    @Override
    public int updateById(long id, User user){
        String hql = "update User set name=:name, " +
                "password=:password, secretKey = :secretKey, firstName = :firstName," +
                "lastName = :lastName, email = :email";
        Session session =sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            return 1;
        }catch (Exception e){
            session.close();
            logger.error("Fail to update\n",e);
            return -1;
        }
    }

    @Override
    public User getUserByCredential(String emailOrName) {
        String hql = "from User as U left join fetch U.roles where U.name=:name or email=:email";
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("name",emailOrName);
            query.setParameter("email",emailOrName);
            List<User> r = query.list();
            session.close();
            return r.size()==0?null:r.get(0);
        }catch (Exception e){
            session.close();
            logger.error("Fail to get credential by id\n",e);
            return null;
        }
    }

    @Override
    public User getUserByIdEager(long id){
        String hql = "from User as U left join fetch U.roles where U.id=:id";
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            User r = (User) query.getSingleResult();
            session.close();
            return r;
        }catch (Exception e){
            session.close();
            logger.error("Fail to get eager by id\n",e);
            return null;
        }


    }


}

