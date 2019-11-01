package dao;

import entity.Employer;
import entity.Role;
import enums.Status;

import java.util.List;

public interface EmployeeDAO extends CallDataBase {

    List<Employer> getUserById(Integer id);

    List<Employer> getEmployersByRole(String nameOfRole);

    List<Employer> getEmployersByDiscipline(String nameOfDiscipline);

    Employer getEmployeeByName(String name);

    List<Employer> getAllEmployersByStatus(Status status);

    void deleteEmployee(Integer id);


}
