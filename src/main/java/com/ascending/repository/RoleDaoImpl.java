package com.ascending.repository;

import com.ascending.model.Role;
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
public class RoleDaoImpl implements RoleDao{
    @Autowired
    private SessionFactory sessionFactory;
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Role> get() {
        Session session = sessionFactory.openSession();
        List<Role> r = session.createQuery("from Role").list();
        session.close();
        return r;
    }

    @Override
    public Role getById(long id) {
        Session session = sessionFactory.openSession();
        Query query = (Query) session.createQuery("from Role where id=:id");
        query.setParameter("id",id);
        Role r = (Role) query.getSingleResult();
        session.close();
        return r;
    }

    @Override
    public Role save(Role role) {
        Session session = sessionFactory.openSession();
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
    public int update(Role role) {
        Session session =sessionFactory.openSession();
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
