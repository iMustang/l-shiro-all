package c05encoder.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

	private PasswordService passwordService;

	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 为了方便，直接注入一个 passwordService 来加密密码，实际使用时需要在 Service 层使用
		// passwordService 加密密码并存到数据库。
		return new SimpleAuthenticationInfo(
				"wu",
				passwordService.encryptPassword("123"),
				getName());
	}
}
