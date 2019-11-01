package dao;

import entity.Role;
import hibernateUtils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl  {
    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    public List<Role> getRoleById(Integer id) {
        List<Role> list = null;
        Transaction t = null;
        try (Session s = sessionFactory.openSession()) {
            t = s.beginTransaction();
            Query query = s.createQuery("from Role where id =:id");
            query.setInteger("id", id);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            if (t != null)
                t.rollback();
        }
        return list;
    }

    public List<Role> setRoles(Integer... args) {
        List<Role> roleList = new ArrayList<>();
        for (Integer v : args) {
            roleList.add(getRoleById(v).get(0));
        }
        return roleList;
    }



}
