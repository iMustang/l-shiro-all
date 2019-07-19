package c06realm.service;

import c06realm.dao.RoleDao;
import c06realm.dao.RoleDaoImpl;
import c06realm.entity.Role;

public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao = new RoleDaoImpl();

	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}

	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);
	}

	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);
	}

	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds);
	}
}
