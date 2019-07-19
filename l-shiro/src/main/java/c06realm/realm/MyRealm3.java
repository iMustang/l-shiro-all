package c06realm.realm;

import c06realm.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm3 implements Realm {

	@Override
	public String getName() {
		return "c";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		User user = new User("zhang", "123");
		return new SimpleAuthenticationInfo(
				user,
				"123",
				getName()
		);
	}
}
