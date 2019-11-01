package dao;

import entity.Role;

import java.util.List;

public interface RoleDAO extends CallDataBase {
    List<Role> getRoleById(Integer id);

    List<Role> setRoles(Integer... args);
}
