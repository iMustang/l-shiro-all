package c06realm.service;

import c06realm.entity.Role;

public interface RoleService {

	Role createRole(Role role);

	void deleteRole(Long roleId);

	void correlationPermissions(Long roleId, Long... permissionIds);

	void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
