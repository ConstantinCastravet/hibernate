import dao.CallDataBase;
import dao.DAOImpl;
import entity.Discipline;
import entity.Employer;
import entity.Role;
import entity.Task;

import java.util.*;

public class Main {

    private static CallDataBase callDB = new DAOImpl();
    private static Set<Discipline> disciplines;
    private static List<Role> roles;
    private static List<Task> tasks;
    private static List<Employer> employers = new ArrayList<>();

    public static void main(String[] args) {


        disciplines = addDiscipline();
        callDB.toDatabase(disciplines);

        roles = addRoles("User", "Admin", "Owner");
        callDB.toDatabase(roles);


        tasks = addTasks();
        callDB.toDatabase(tasks);

        employers.add(addEmployee("Johny", "JohnyBravo",
                "Johny@gmail", 2, 2));

        employers.add(addEmployee("Mike", "MikeTank",
                "AMigo@gmail", 3, 1));

        employers.add(addEmployee("Jostea", "JS",
                "Jstream@gmail", 1, 3));

        callDB.toDatabase(employers);

//        List<Discipline> disciplines1 = callDB.getDisciplineByName("AM");
//        callDB.updateDiscipline(disciplines1.get(0).getId(), (Employer) callDB.getUserById(1).get(0));
//
//        callDB.toDatabase(disciplines);

//        List<Integer> list=callDB.getEmployeeByRole(1);


//        List<Employer> emp=callDB.getUserById(1);
//        List<Role> roles1 = emp.get(0).getRoles();
//
//        for (Role val:roles1) {
//            System.out.println(val.getName());
//        }


//        System.out.println(list.get(0));

    }


    public static Discipline findDiscipline(String name, List<Discipline> disciplines) {
        Discipline discipline = new Discipline();
        for (Discipline val : disciplines) {
            if (val.getName().equalsIgnoreCase(name))
                return val;

        }
        return null;
    }

    public static List<Task> addTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Exceptions", "Create your own Exception"));
        tasks.add(new Task("Generics", "Create your Generic class"));
        tasks.add(new Task("String", "Revers strings"));
        return tasks;
    }

    public static List<Role> addRoles(String... args) {
        List<Role> roles = new ArrayList<>();
        for (String val : args) {
            roles.add(new Role(val));
        }
        return roles;
    }

    public static Set<Discipline> addDiscipline() {
        Set<Discipline> disciplines = new TreeSet<>(Comparator.comparing(Discipline::getName));
        disciplines.add(new Discipline("AM"));
        disciplines.add(new Discipline("Developer"));
        disciplines.add(new Discipline("Tester"));

        return disciplines;
    }

    public static Employer addEmployee(String name, String username, String email, Integer discipline, Integer role) {
        Employer employee = new Employer();
        employee.setName(name);
        employee.setUserName(username);
        employee.setEmail(email);
//       employee.setDiscipline(findDiscipline(discipline, callDB.allFomDatabase(new Discipline())));
        employee.setDiscipline((Discipline) callDB.getDisciplineById(discipline).get(0));
        employee.setRoles(callDB.getRoleById(role));
        employee.setTask(tasks);
        return employee;
    }
}
