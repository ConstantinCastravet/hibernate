package dao;

import entity.Discipline;
import entity.Employer;

import java.util.List;

public interface DisciplineDAO extends CallDataBase {

    List<Discipline> getDisciplineById(Integer id);

    void updateDiscipline(Integer disId, Employer employer);

    List<Discipline> getDisciplineByName(String name);
}
