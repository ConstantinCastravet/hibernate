package dao;

import entity.Discipline;
import entity.Employer;
import entity.Role;
import hibernateUtils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public interface CallDataBase<E> {

    List<Employer> getUserById(Integer id);
    List<Role> getRoleById(Integer id);
    List<Discipline> getDisciplineById(Integer id);
    List<Discipline> getDisciplineByName(String name);
    public void updateDiscipline(Integer disId, Employer employer);
    public List<Integer> getEmployeeByRole(Integer role_Id);



    default void toDatabase(Collection<E> list) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session s = null;
        Transaction t = null;

        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            for (E c : list)
                s.save(c);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
    }

    default List<E> allFomDatabase(E e) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session s = null;
        Transaction t = null;
        List<E> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query = s.createQuery("from " + e.getClass().getCanonicalName());
            list = query.list();
            t.commit();
        } catch (Exception ex) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }
}
