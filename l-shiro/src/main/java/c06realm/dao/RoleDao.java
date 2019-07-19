package c06realm.dao;

import c06realm.entity.Role;

public interface RoleDao {

	Role createRole(Role role);

	void deleteRole(Long roleId);

	void correlationPermissions(Long roleId, Long... permissionIds);

	void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
