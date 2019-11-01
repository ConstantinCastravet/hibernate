package dao;

import entity.Employer;
import entity.Role;
import entity.Task;
import enums.Status;
import hibernateUtils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl  {
    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

//    public List<Employer> getUserById(Integer id) {
//        List<Employer> list = null;
//        Transaction t = null;
//        try (Session s = sessionFactory.openSession()) {
//            t = s.beginTransaction();
//            Query query = s.createQuery("from Employer where id =:id AND enabled=true");
//            query.setInteger("id", id);
//            list = query.list();
//            t.commit();
//        } catch (Exception e) {
//            if (t != null)
//                t.rollback();
//        }
//        return list;
//    }
//
//    public List<Employer> getEmployersByRole(String nameOfRole) {
//        List<Employer> list = new ArrayList<>();
//        List<Employer> em = allFomDatabase(new Employer());
//        for (Employer val : em) {
//            for (Role role : val.getRoles()) {
//                if (role.getName().equals(nameOfRole))
//                    list.add(val);
//            }
//        }
//
//        return list;
//    }
//
//    public List<Employer> getEmployersByDiscipline(String nameOfDiscipline) {
//        List<Employer> list = new ArrayList<>();
//        List<Employer> em = allFomDatabase(new Employer());
//        for (Employer val : em) {
//            if (val.getDiscipline().getName().equals(nameOfDiscipline))
//                list.add(val);
//        }
//        return list;
//    }
//
//    public Employer getEmployeeByName(String name) {
//        List<Employer> list = null;
//        Transaction t = null;
//        try (Session s = sessionFactory.openSession()) {
//            t = s.beginTransaction();
//            Query query = s.createQuery("from Employer where name =:name AND enabled=true");
//            query.setString("name", name);
//            list = query.list();
//            t.commit();
//        } catch (Exception e) {
//            if (t != null)
//                t.rollback();
//        }
//        return list.get(0);
//    }
//
//    public List<Employer> getAllEmployersByStatus(Status status) {
//
//        List<Task> taskList = allFomDatabase(new Task());
//        List<Employer> list = new ArrayList<>();
//
//        for (Task val : taskList) {
//            if (val.getStatus() == status)
//                list.add(val.getEmployer());
//        }
//        return list;
//    }

    public void deleteEmployee(Integer id){
        Transaction t = null;
        try (Session s = sessionFactory.openSession()) {
            t = s.beginTransaction();


            Employer employee=s.get(Employer.class,id);
            employee.setEnabled(false);
            s.update(employee);

            t.commit();
        } catch (Exception e) {
            if (t != null)
                t.rollback();
        }
    }


}
