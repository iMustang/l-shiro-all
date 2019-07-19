package c06realm.service;

import c06realm.dao.PermissionDao;
import c06realm.dao.PermissionDaoImpl;
import c06realm.entity.Permission;

public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
