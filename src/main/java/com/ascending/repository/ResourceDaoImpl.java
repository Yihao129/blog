package com.ascending.repository;

import com.ascending.model.Resource;
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
public class ResourceDaoImpl implements ResourceDao{

    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Resource> getByUserId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = (Query) session.createQuery("from Resource where userId=:id");
        query.setParameter("id",id);
        List<Resource> r =  query.list();
        session.close();
        return r;
    }

    @Override
    public Resource getByUserIdAndFileName(Long id, String fileName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = (Query) session.createQuery("from Resource where userId=:id and fileName=:fileName");
        query.setParameter("id",id);
        query.setParameter("fileName",fileName);
        Resource r = (Resource) query.getSingleResult();
        session.close();
        return r;
    }

    @Override
    public Resource save(Resource res) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction=session.beginTransaction();
            long r = (long) session.save(res);
            transaction.commit();
            session.close();
            return  res;
        }catch (Exception e){
            session.close();
            logger.error("Fail to save role\n",e);
            return null;
        }
    }

    @Override
    public int deleteByUserId(Long id) {
        String hql = "delete from Resource where user_id=:id";
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
}
