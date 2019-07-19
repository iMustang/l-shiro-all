package c06realm.service;

import c06realm.entity.Permission;

public interface PermissionService {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
