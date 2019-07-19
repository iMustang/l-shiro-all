package c06realm.dao;

import c06realm.entity.Permission;

public interface PermissionDao {

	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);

}
