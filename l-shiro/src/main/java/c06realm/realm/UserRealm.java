package c06realm.realm;

import c06realm.entity.User;
import c06realm.service.UserService;
import c06realm.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class UserRealm extends AuthorizingRealm {

	private UserService userService = new UserServiceImpl();

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 直接调用 getPrimaryPrincipal 得到之前传入的用户名即可，
		// 然后根据用户名调用 UserService 接口获取角色及权限信息。
		authorizationInfo.setRoles(userService.findRoles(username));
		authorizationInfo.setStringPermissions(userService.findPermissions(username));

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.findByUsername(username);

		if (user == null) {
			//没找到帐号
			throw new UnknownAccountException();
		}

		if (Boolean.TRUE.equals(user.getLocked())) {
			//帐号锁定
			throw new LockedAccountException();
		}

		//生成 AuthenticationInfo 信息，交给间接父类 AuthenticatingRealm 使用 CredentialsMatcher 进行密码匹配
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUsername(), //用户名
				user.getPassword(), //密码
				ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
				getName()  //realm name
		);
		return authenticationInfo;
	}
}
