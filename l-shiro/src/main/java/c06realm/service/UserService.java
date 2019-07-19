package c06realm.service;

import c06realm.entity.User;

import java.util.Set;

public interface UserService {

	User createUser(User user);

	void changePassword(Long userId, String newPassword);

	void correlationRoles(Long userId, Long... roleIds);

	void uncorrelationRoles(Long userId, Long... roleIds);

	User findByUsername(String username);

	Set<String> findRoles(String username);

	Set<String> findPermissions(String username);
}
