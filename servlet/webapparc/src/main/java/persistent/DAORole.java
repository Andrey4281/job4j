package persistent;

import model.Role;

import java.util.List;

public interface DAORole {
    Role findRoleById(int id);
    Role findRoleByName(String roleName);
    List<Role> findAllRoles();
}
