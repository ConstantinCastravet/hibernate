package dao;

import entity.Discipline;
import entity.Employer;
import entity.Role;
import enums.Status;
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

    List<Role> setRoles(Integer... args);

    List<Discipline> getDisciplineById(Integer id);

    void updateDiscipline(Integer disId, Employer employer);

    List<Discipline> getDisciplineByName(String name);



    List<Employer> getEmployersByRole(String nameOfRole);

    List<Employer> getEmployersByDiscipline(String nameOfDiscipline);

    Employer getEmployeeByName(String name);

    List<Employer> getAllEmployersByStatus(Status status);

    void deleteEmployee(Integer id);

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

        if (!(e instanceof Employer))
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

        if (e instanceof Employer)
            try {
                s = sessionFactory.openSession();
                t = s.beginTransaction();
                Query query = s.createQuery("from " + e.getClass().getCanonicalName() + " where enabled = true");
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
