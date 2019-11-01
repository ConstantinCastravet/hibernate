import dao.CallDataBase;
import dao.DAOImpl;
import entity.Discipline;
import entity.Employer;
import entity.Role;
import entity.Task;
import enums.Status;

import java.util.*;

public class Main {

    private static CallDataBase callDB = new DAOImpl();

    private static final Employer employeeOut = new Employer();

    public static void main(String[] args) {
        List<Employer> employers = new ArrayList<>();

        List<Task> tasks = new ArrayList<>();
        Set<Discipline> disciplines = addDiscipline();
        callDB.toDatabase(disciplines);

        List<Role> roles = addRoles("User", "Admin", "Owner");
        callDB.toDatabase(roles);


        employers.add(addEmployee("Johny", "JohnyBravo",
                "Johny@gmail", 2, true, 1, 2, 3));

        employers.add(addEmployee("Mike", "MikeTank",
                "AMigo@gmail", 3, true, 2, 3));

        employers.add(addEmployee("Jostea", "JS",
                "Jstream@gmail", 1, true, 2, 1));

        employers.add(addEmployee("Adolf", "CC",
                "churchill@yahoo.com", 1, true, 1));

        employers.add(addEmployee("Rudolf", "RUf911",
                "rufic@yahoo.com", 1, false, 1));


        callDB.toDatabase(employers);

        setEmployeeForTask(addTask("Exceptions", "Create your own Exception", Status.DONE),
                "Jostea");

        setEmployeeForTask(addTask("Generics", "Create your own Generic", Status.DONE),
                "Jostea");

        setEmployeeForTask(addTask("String", "ReversString", Status.PROGRESS),
                "Mike");

        setEmployeeForTask(addTask("Stream API", "Improve app", Status.TODO),
                "Adolf");

        /*It's return user with roles example*/
        //        List<Employer> emp = callDB.getUserById(2);
//        System.out.println(emp.get(0).getRoles());
//
//        System.out.println(callDB.getEmployersByRole("User"));
//        System.out.println(callDB.getEmployersByDiscipline("AM"));
//
//        callDB.updateDiscipline(1, callDB.getEmployeeByName("Jostea"));
//        callDB.updateDiscipline(2, callDB.getEmployeeByName("Mike"));
//        callDB.updateDiscipline(3, callDB.getEmployeeByName("Adolf"));
//
//        callDB.deleteEmployee(4);
//        System.out.println(callDB.allFomDatabase(employeeOut));
//        System.out.println(getListOfDisciplineLowAmountOfEmployers());
    }


    public static List<Task> addTask(String nameOfTask, String description, Status status) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(nameOfTask, description, status));
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

    public static Employer addEmployee(String name, String username, String email, Integer discipline, Boolean enabled, Integer... role) {
        Employer employee = new Employer();
        employee.setEnabled(enabled);
        employee.setName(name);
        employee.setUserName(username);
        employee.setEmail(email);
//       employee.setDiscipline(findDiscipline(discipline, callDB.allFomDatabase(new Discipline())));
        employee.setDiscipline((Discipline) callDB.getDisciplineById(discipline).get(0));
        employee.setRoles((List<Role>) callDB.setRoles(role));

        return employee;
    }

    public static void setEmployeeForTask(List<Task> tasks, String nameOfEmployee) {

        for (Task val : tasks) {
            val.setEmployer(callDB.getEmployeeByName(nameOfEmployee));
        }


//        tasks.get(0).setEmployer(callDB.getEmployeeByName("Jostea"));
//        tasks.get(1).setEmployer(callDB.getEmployeeByName("Adolf"));
//        tasks.get(2).setEmployer(callDB.getEmployeeByName("Mike"));


        callDB.toDatabase(tasks);
    }

    public static List<Discipline> getListOfDisciplineLowAmountOfEmployers() {
        List<Discipline> disciplines = new ArrayList<>();
        for (Discipline val : (List<Discipline>) callDB.allFomDatabase(new Discipline())) {
            if (val.getMembers().size() < 2) {
                disciplines.add(val);
            }
        }
        return disciplines;
    }
}
