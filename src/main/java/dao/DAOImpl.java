package dao;

import entity.Discipline;
import entity.Employer;
import entity.Role;
import hibernateUtils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DAOImpl implements CallDataBase {

    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();


    public List<Employer> getUserById(Integer id) {
        List<Employer> list = null;
        Transaction t = null;
        try (Session s = sessionFactory.openSession()) {
            t = s.beginTransaction();
            Query query = s.createQuery("from Employer where id =:id");
            query.setInteger("id", id);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            if (t != null)
                t.rollback();
        }
        return list;
    }

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

    public List<Discipline> getDisciplineById(Integer id) {
        List<Discipline> list = null;
        Transaction t = null;
        try (Session s = sessionFactory.openSession()) {
            t = s.beginTransaction();
            Query query = s.createQuery("from Discipline where id =:id");
            query.setInteger("id", id);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            if (t != null)
                t.rollback();
        }
        return list;
    }

    public List<Discipline> getDisciplineByName(String name) {

        List<Discipline> list = null;
        Transaction t = null;
        try (Session s = sessionFactory.openSession()) {
            t = s.beginTransaction();
            Query query = s.createQuery("from Discipline where name =:name");
            query.setString("name", name);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            if (t != null)
                t.rollback();
        }
        return list;
    }

    //To improve
    public void updateDiscipline(Integer disId, Employer employer) {

        Transaction t = null;
        try (Session s = sessionFactory.openSession()) {
            t = s.beginTransaction();

            Discipline dis = s.get(Discipline.class, disId);
            dis.setHeadOfDiscipline(employer);
            s.update(dis);

            t.commit();
        } catch (Exception e) {
            if (t != null)
                t.rollback();
        }
    }

    public List<Object> getEmployeeByRole(Integer role_Id) {
        List<Object> list = null;
        return list;
    }
}
