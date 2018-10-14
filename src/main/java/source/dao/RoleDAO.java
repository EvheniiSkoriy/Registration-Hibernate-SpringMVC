package source.dao;

import source.entity.Role;

public interface RoleDAO {
   Role findRoleByName(String roleName);
}
