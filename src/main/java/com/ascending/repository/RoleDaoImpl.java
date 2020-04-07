package com.ascending.repository;

import com.ascending.model.Role;
import com.ascending.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Role> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Role").list();
    }

    @Override
    public Role getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = (Query) session.createQuery("from Role where id=:id");
        query.setParameter("id",id);
        return (Role) query.getSingleResult();
    }

    @Override
    public Role save(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction=session.beginTransaction();
            long r = (long) session.save(role);
            transaction.commit();
            session.close();
            return  role;
        }catch (Exception e){
            session.close();
            logger.error("Fail to save role\n",e);
            return null;
        }
    }

    @Override
    public int deleteById(long id) {
        String hql = "delete from Role where id=:id";
        Session session = HibernateUtil.getSessionFactory().openSession();
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
    public int update(Role role) {
        Session session =HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
            session.close();
            return 1;
        }catch (Exception e){
            session.close();
            logger.error("Fail to update\n",e);
            return -1;
        }
    }
}
